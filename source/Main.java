import ast.*;
import grammars.FOOL.FOOLLexer;
import grammars.FOOL.FOOLParser;
import grammars.FOOL.FOOLVisitorImpl;
import org.antlr.v4.runtime.*;
import utils.Environment;
import java.io.File;
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
			System.out.println(type.toPrint("Type checking ok! Type of the program is: "));
		}
		
		return result;
	}

	public static void test(File file) {
		try {

			String output = "";
			CharStream input = CharStreams.fromFileName(file.getAbsolutePath());
			output += run(input);
		}
		catch (Exception e) {

		}
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