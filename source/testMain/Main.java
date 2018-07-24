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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.HashSet;

public class Main {
	
	private static Node lexicalAndSyntacticAnalysis(CharStream input) /*throws LexerException*/ {
		FOOLLexer lexer = new FOOLLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		if (lexer.lexicalErrors > 0) {
			System.out.println("Lexer errors");
			//throw new LexerException(lexer.errors);
		}
		FOOLParser parser = new FOOLParser(tokens);
		FOOLVisitorImpl visitor = new FOOLVisitorImpl();
		
		FOOLParser.ProgContext res = parser.prog();
		
		return visitor.visit(res); //generazione AST
	}
	
	private static Node semanticAnalysis(Node ast, boolean visualizeAST){
		Environment env = new Environment();
		HashSet<String> err = ast.checkSemantics(env);
		
		if (err.size() > 0) {
			for(String e:err){
				System.out.print(e);
			}
			 //System.exit(2);  //TODO temporary, only to allow testing of multiple files in a single run
			return null; // TODO maybe
		}
		
		if(visualizeAST) {
			System.out.println("Abstract Syntax Tree:");
			System.out.println(ast.toPrint(""));
		}
		return ast; //type-checking bottom-up
	}
	
	
	public static String run(CharStream input) throws Exception {
		String result = "";
		
		System.out.println("Lexer & parser...");
		
		Node ast = lexicalAndSyntacticAnalysis(input);
		
		System.out.println("Visualizing AST...");
		
		ast = semanticAnalysis(ast, true);
		if(ast!=null) {
			Node type = ast.typeCheck(); //type-checking bottom-up
			System.out.println(type.toPrint("\nType checking ok! Type of the program is: "));
			System.out.println();
			
			// Code Generation
			codeGen(ast);
		}
		
		return result;
	}
	
	public static void codeGen(Node ast) throws Exception {
		String assembly = ast.codeGeneration();
		System.out.println(assembly);
		
		BufferedWriter asmOutput = new BufferedWriter(new FileWriter("code/output.fool.asm"));
		asmOutput.write(assembly);
		asmOutput.close();
		
		File asmInputFile = new File("code/output.fool.asm");
		CharStream input = CharStreams.fromFileName(asmInputFile.getAbsolutePath());
		
		SVMLexer asmLexer = new SVMLexer(input);
		CommonTokenStream asmTokens = new CommonTokenStream(asmLexer);
		SVMParser asmParser = new SVMParser(asmTokens);
		SVMVisitorImpl asmVisitor = new SVMVisitorImpl();
		
		asmVisitor.visit(asmParser.assembly());
		
		System.out.println("Starting Virtual Machine...");
		ExecuteVM vm = new ExecuteVM(SVMParser.code);
		
		System.out.println("Code Array:");
		System.out.println(Arrays.toString(SVMParser.code));
		
		vm.cpu();
		
		if (!vm.errorBuffer.isEmpty()) {
			System.out.println("Some errors occurred during execution:");
			for (String e: vm.errorBuffer)
				System.out.println(e);
		}
		else {
			System.out.println("Virtual Machine execution result:");
			for (String res: vm.outputBuffer)
				System.out.println(res);
		}
	}

	public static void testWithThreads(File file) {
		try {

			String output = "";
			CharStream input = CharStreams.fromFileName(file.getAbsolutePath());
			output += run(input);
		}
		catch (Exception e) {
			// printStackTrace method
			// prints line numbers + call stack
			e.printStackTrace();

			// Prints what exception has been thrown
			System.out.println(e);
		}
	}

	public static String testWithYaml(String testID, CharStream input, String expectedResult, boolean enableLogging, boolean showAST, boolean noColors) {
		String actualResult = "";

		try {
			actualResult = run(input);
		} catch(Exception e) {
			e.printStackTrace();
		}
		StringBuilder output = new StringBuilder();
		output.append("-Expected: ").append(expectedResult).append("\n")
				.append("-Got: ").append(actualResult).append("\n");
		if (actualResult.trim().equals(expectedResult.trim())) {
			output.append("Test PASSED!");
		} else {
			output.append("Test FAILED!");
		}

		return output.toString();
	}
	
	public static void main(String[] args) throws  Exception{

		//try {
			File inputFile = new File("code/input.fool");
			CharStream input = CharStreams.fromFileName(inputFile.getAbsolutePath());
			String output = run(input);
		//}
		/*catch (Exception E) {
			System.out.println(E);
		}*/
		/*ast.typeCheck();
		
		Node boolNode = new BoolValNode(true);
		Node intNode = new IntValNode(2);
		
		Node prova = new AndNode(boolNode, intNode);
		prova.typeCheck();*/
		
	}
}