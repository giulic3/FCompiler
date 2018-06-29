import grammar.FOOLLexer;
import grammar.FOOLParser;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import java.io.IOException;
import java.util.ArrayList;

public class main(){

private static Node lexicalAndSyntacticAnalysis(CharStream input) throws LexerException {
		FOOLLexer lexer = new FOOLLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		if (lexer.errors.size() > 0) {
		throw new LexerException(lexer.errors);
		}
		FOOLParser parser = new FOOLParser(tokens);
		FoolVisitorImpl visitor = new FoolVisitorImpl();
		return visitor.visit(parser.prog()); //generazione AST
		}


public static String run(CharStream input) {
		CodegenUtils.reset();
		String result = "";
		try {
		
		if (enableLogging) {
		System.out.println("Lexer & parser...");
		}
		
		Node ast = lexicalAndSyntacticAnalysis(input);
		
		
		return result;
		}

public static void main(String[] args) throws Exception{
		String filename="input.fool";
		CharStream input=CharStreams.fromFileName(filename);
		run(input);
		System.out.println(output);
		}
}