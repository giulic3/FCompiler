package testMain;

import ast.*;
import grammars.FOOL.FOOLLexer;
import grammars.FOOL.FOOLParser;
import grammars.FOOL.FOOLVisitorImpl;
import grammars.SVM.ExecuteVM;
import grammars.SVM.SVMLexer;
import grammars.SVM.SVMParser;
import grammars.SVM.SVMVisitorImpl;
import org.antlr.v4.runtime.*;
import utils.Environment;
import utils.Helpers;
import utils.LexerParserErrorListener;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashSet;

public class Main {
	
	private static Node lexicalAndSyntacticAnalysis(CharStream input) {
		FOOLLexer lexer = new FOOLLexer(input);
		LexerParserErrorListener errorListener = new LexerParserErrorListener();
		lexer.removeErrorListeners();
		lexer.addErrorListener(errorListener);
		
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		
		FOOLParser parser = new FOOLParser(tokens);
		parser.removeErrorListeners();
		parser.addErrorListener(errorListener);
		
		FOOLVisitorImpl visitor = new FOOLVisitorImpl();
		
		FOOLParser.ProgContext res = parser.prog();
		
		return visitor.visit(res); //generazione AST
	}
	
	private static Node semanticAnalysis(Node ast, boolean visualizeAST) throws Exception {
		Environment env = new Environment();
		HashSet<String> err = ast.checkSemantics(env);
		
		if (err.size() > 0) {
			StringBuilder errors = new StringBuilder();
			for(String e: Helpers.getOrderedListOfErrors(err))
				errors.append(e);
			
			throw new Exception(errors.toString());
		}
		
		if(visualizeAST) {
			System.out.println("Abstract Syntax Tree:");
			System.out.println(ast.toPrint(""));
		}
		return ast; //type-checking bottom-up
	}
	
	private static String codeGen(Node ast, boolean verbose) throws Exception {
		StringBuilder result = new StringBuilder();
		
		String assembly = ast.codeGeneration();
		if (verbose) System.out.println(assembly);
		
		File asmInputFile = new File("output.fool.asm");
		if (asmInputFile.exists()) asmInputFile.delete();
		
		BufferedWriter asmOutput = new BufferedWriter(new FileWriter("output.fool.asm"));
		asmOutput.write(assembly);
		asmOutput.close();
		
		CharStream input = CharStreams.fromFileName(asmInputFile.getAbsolutePath());
		
		SVMLexer asmLexer = new SVMLexer(input);
		CommonTokenStream asmTokens = new CommonTokenStream(asmLexer);
		SVMParser asmParser = new SVMParser(asmTokens);
		SVMVisitorImpl asmVisitor = new SVMVisitorImpl();
		
		asmVisitor.visit(asmParser.assembly());
		
		if (verbose) System.out.println("Starting Virtual Machine...");
		ExecuteVM vm = new ExecuteVM(SVMParser.code);
		
		vm.cpu();
		
		if (!vm.errorBuffer.isEmpty()) {
			if (verbose) result.append("Some errors occurred during execution:\n");
			for (String e: vm.errorBuffer)
				result.append(e).append("\n");
		}
		else {
			if (verbose) result.append("Virtual Machine execution result:\n");
			if (vm.outputBuffer.size() == 0)
				result.append("Execution completed. No output shown.");
			else
				for (String res: vm.outputBuffer)
					result.append(res).append("\n");
		}
		
		return result.toString();
	}

	public static String test(CharStream input, String expectedResult) {
		String actualResult = "";

		try {
			actualResult = run(input, false, false);
		} catch(Exception e) {
			e.printStackTrace();
		}
		StringBuilder output = new StringBuilder();
		
		String newLine = "";
		
		if (expectedResult.contains("\n"))
			newLine = "\n";
		
		output.append("- Expected: ").append(newLine)
				.append(expectedResult).append("\n")
				.append("- Got: ").append(newLine)
				.append(actualResult).append("\n");
		
		if (actualResult.trim().equals(expectedResult.trim())) {
			output.append(Helpers.ANSI_GREEN).append("Test PASSED!\n");
		} else {
			output.append(Helpers.ANSI_RED).append("Test FAILED!\n");
		}

		return output.toString();
	}
	
	public static void execute(String filePath, boolean verbose, boolean astOnly) {
		try {
			File inputFile = new File(filePath);
			CharStream input = CharStreams.fromFileName(inputFile.getAbsolutePath());
			String output = run(input, verbose, astOnly);
			System.out.println(output);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static String run(CharStream input, boolean verbose, boolean astOnly) {
		String result = "";
		
		try {
			if (verbose) System.out.println("Lexer & parser...");
			
			Node ast = lexicalAndSyntacticAnalysis(input);
			
			if (verbose) System.out.println("Visualizing AST...");
			
			ast = semanticAnalysis(ast, astOnly || verbose);
			if (ast != null && !astOnly) {
				Node type = ast.typeCheck(); //type-checking bottom-up
				
				if (verbose) {
					System.out.println(type.toPrint("\nType checking ok! Type of the program is: "));
					System.out.println();
				}
				
				// Code Generation
				result = codeGen(ast, verbose);
			}
		}
		catch (Exception e) {
			result = e.getMessage();
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		execute("code/input.fool", true, false);
	}
}