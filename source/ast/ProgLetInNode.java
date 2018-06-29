package ast;
import java.util.ArrayList;
import java.util.HashMap;


public class ProgLetInNode implements Node {
	
	public ProgLetInNode (ArrayList<Node> d, Node e) {
	
	}
	
	String toPrint(String indent);
	
	Node typeCheck();
	
	String codeGeneration();
	
	//ArrayList<SemanticError> checkSemantics(Environment env);
	
}