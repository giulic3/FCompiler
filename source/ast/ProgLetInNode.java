package ast;
import ast.types.BaseType;
import utils.Environment;
import utils.SemanticError;

import java.util.ArrayList;
import java.util.HashMap;


public class ProgLetInNode implements Node {
	
	public ProgLetInNode (ArrayList<Node> d, Node e) {
	
	}
	
	public String toPrint(String indent){return null;};
	
	public Node typeCheck(){return null;};
	
	public String codeGeneration(){return null;};
	
	public ArrayList<SemanticError> checkSemantics(Environment env){return null;};
	
}