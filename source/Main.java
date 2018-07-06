import ast.*;
import grammars.FOOL.FOOLLexer;
import grammars.FOOL.FOOLLexer.*;
import grammars.FOOL.FOOLParser;
import grammars.FOOL.FOOLVisitorImpl;
import ast.BoolValNode;


import org.antlr.v4.runtime.*;
import utils.Environment;
import utils.SemanticError;

import java.io.File;
import java.util.ArrayList;

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
		ArrayList<SemanticError> err = ast.checkSemantics(env);
		
		if (err.size() > 0) {
			for(SemanticError e:err){
				System.out.print(e.toString());
			}
			//System.exit(2);
		}
		
		if(visualizeAST) {
			System.out.println("Abstract Syntax Tree:");
			System.out.println(ast.toPrint(""));
		}
		return ast; //type-checking bottom-up
	}
	
	
	public static String run(CharStream input) {
		String result = "";
		
		System.out.println("Lexer & parser...");
		
		
		Node ast = lexicalAndSyntacticAnalysis(input);
		
		System.out.println("Visualizing AST...");
		System.out.println(ast.toPrint(""));
		
		ast = semanticAnalysis(ast, true);
		
		return result;
	}
	
	public static void main(String[] args) throws Exception{
		
		File inputFile = new File("code/input.fool");
		CharStream input=CharStreams.fromFileName(inputFile.getAbsolutePath());
		String output = run(input);
		
		/*ast.typeCheck();
		
		Node boolNode = new BoolValNode(true);
		Node intNode = new IntValNode(2);
		
		Node prova = new AndNode(boolNode, intNode);
		prova.typeCheck();*/
		
	}
}