import ast.*;
import grammars.FOOL.FOOLLexer;
import grammars.FOOL.FOOLLexer.*;
import grammars.FOOL.FOOLParser;
import grammars.FOOL.FOOLVisitorImpl;


import org.antlr.v4.runtime.*;

import java.io.File;

public class main{
	
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
	
	
	public static String run(CharStream input) {
		String result = "";
		
		System.out.println("Lexer & parser...");
		
		
		Node ast = lexicalAndSyntacticAnalysis(input);
		
		System.out.println("Visualizing AST...");
		System.out.println(ast.toPrint(""));
		
		return result;
	}
	
	public static void main(String[] args) throws Exception{
		
		File inputFile = new File("code/input.fool");
		CharStream input=CharStreams.fromFileName(inputFile.getAbsolutePath());
		//String output = run(input);
		System.out.println("Lexer & parser...");
		
		
		Node ast = lexicalAndSyntacticAnalysis(input);
		
		System.out.println("Visualizing AST...");
		String res = ast.toPrint("");
		System.out.println(res);
		
	}
}