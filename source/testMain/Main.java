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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashSet;

public class Main {
	
	private static Node lexicalAndSyntacticAnalysis(CharStream input) throws Exception {
		FOOLLexer lexer = new FOOLLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		if (lexer.lexicalErrors > 0)
			throw new Exception("Lexer errors occurred");
		
		FOOLParser parser = new FOOLParser(tokens);
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
	
	public static String run(CharStream input, boolean testing) {
		String result = "";
		
		try {
			if (!testing) System.out.println("Lexer & parser...");
			
			Node ast = lexicalAndSyntacticAnalysis(input);
			
			if (!testing) System.out.println("Visualizing AST...");
			
			ast = semanticAnalysis(ast, !testing);
			if (ast != null) {
				Node type = ast.typeCheck(); //type-checking bottom-up
				
				if (!testing) {
					System.out.println(type.toPrint("\nType checking ok! Type of the program is: "));
					System.out.println();
				}
				
				// Code Generation
				result = codeGen(ast, testing);
			}
		}
		catch (Exception e) {
			result = e.getMessage();
		}
		
		return result;
	}
	
	public static String codeGen(Node ast, boolean testing) throws Exception {
		StringBuilder result = new StringBuilder();
		
		String assembly = ast.codeGeneration();
		if (!testing) System.out.println(assembly);
		
		File asmInputFile = new File("code/output.fool.asm");
		if (asmInputFile.exists()) asmInputFile.delete();
		
		BufferedWriter asmOutput = new BufferedWriter(new FileWriter("code/output.fool.asm"));
		asmOutput.write(assembly);
		asmOutput.close();
		
		CharStream input = CharStreams.fromFileName(asmInputFile.getAbsolutePath());
		
		SVMLexer asmLexer = new SVMLexer(input);
		CommonTokenStream asmTokens = new CommonTokenStream(asmLexer);
		SVMParser asmParser = new SVMParser(asmTokens);
		SVMVisitorImpl asmVisitor = new SVMVisitorImpl();
		
		asmVisitor.visit(asmParser.assembly());
		
		if (!testing) System.out.println("Starting Virtual Machine...");
		ExecuteVM vm = new ExecuteVM(SVMParser.code);
		
		vm.cpu();
		
		if (!vm.errorBuffer.isEmpty()) {
			if (!testing) result.append("Some errors occurred during execution:\n");
			for (String e: vm.errorBuffer)
				result.append(e).append("\n");
		}
		else {
			if (!testing) result.append("Virtual Machine execution result:\n");
			for (String res: vm.outputBuffer)
				result.append(res).append("\n");
		}
		
		return result.toString();
	}

	public static void testWithThreads(File file) {
		try {

			String output = "";
			CharStream input = CharStreams.fromFileName(file.getAbsolutePath());
			output += run(input, true);
		}
		catch (Exception e) {
			// printStackTrace method
			// prints line numbers + call stack
			e.printStackTrace();

			// Prints what exception has been thrown
			System.out.println(e);
		}
	}

	public static String test(CharStream input, String expectedResult) {
		String actualResult = "";

		try {
			actualResult = run(input, true);
		} catch(Exception e) {
			e.printStackTrace();
		}
		StringBuilder output = new StringBuilder();
		output.append("- Expected: ").append(expectedResult).append("\n")
				.append("- Got: ").append(actualResult).append("\n");
		if (actualResult.trim().equals(expectedResult.trim())) {
			output.append(Helpers.ANSI_GREEN).append("Test PASSED!\n");
		} else {
			output.append(Helpers.ANSI_RED).append("Test FAILED!\n");
		}

		return output.toString();
	}
	
	public static void main(String[] args) {
		try {
			File inputFile = new File("code/input.fool");
			CharStream input = CharStreams.fromFileName(inputFile.getAbsolutePath());
			String output = run(input, false);
			System.out.println(output);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}