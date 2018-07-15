package ast.types;
import java.util.ArrayList;
import java.util.HashSet;

import ast.Node;
import utils.Environment;
;

/* function type */
public class FunType implements Node {
	
	private ArrayList<Node> parTypesList;
	private Node returnType;
	
	public FunType(ArrayList<Node> p, Node r) {
		parTypesList = p;
		returnType = r;
	}
	
	// TODO: this toPrint() should be checked
	public String toPrint(String indent) {
		String parlstr = "(";
		
		for (Node par:parTypesList)
			parlstr += par.toPrint(" ");

		parlstr += ")";

		return parlstr + " -> " + returnType.toPrint("") ;
	}
	
	public Node getReturnType() {
		return returnType;
	}
	
	public ArrayList<Node> getParList () {
		return parTypesList;
	}
	
	public Node typeCheck() {
		return returnType;
	}
	
	public String codeGeneration() {
		return null;
	}
	
	public HashSet<String> checkSemantics(Environment env) {
		return new HashSet<String>();
	}

	public String getID() {
		return null;
	}
	
}  