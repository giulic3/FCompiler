package ast;
import java.util.ArrayList;
import java.util.HashMap;


public class InTNode implements Node {
	
	private int val;
	
	public IntNode(FOOLParser.IntValContext ctx, int val) {
		super(ctx);
		this.val = val;
	}
	
	String toPrint(String indent);
	
	Node typeCheck();
	
	String codeGeneration();
	
	ArrayList<SemanticError> checkSemantics(Environment env);
	
}