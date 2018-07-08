package ast.types;
import java.util.ArrayList;

import ast.Node;
import utils.Environment;
import utils.SemanticError;

/* function type */
public class ArrowType implements Node {
	
	private ArrayList<Node> parTypesList;
	private Node ret;
	
	public ArrowType (ArrayList<Node> p, Node r) {
		parTypesList = p;
		ret = r;
	}
	
	// TODO: this toPrint() should be checked
	public String toPrint(String indent) {
		String parlstr = "";
		
		for (Node par:parTypesList)
			parlstr += par.toPrint("");
		
		return "Arrow Type\n" + parlstr;// + ret.toPrint(" ->") ;
	}
	
	public Node getRet() {
		return ret;
	}
	
	public ArrayList<Node> getParList () {
		return parTypesList;
	}
	
	public Node typeCheck() {
		return null;
	}
	
	public String codeGeneration() {
		return null;
	}
	
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		return new ArrayList<>();
	}

	public String getID() {
		return null;
	}
	
}  