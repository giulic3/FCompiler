package ast.types;
import java.util.ArrayList;

import ast.Node;
import utils.Environment;
import utils.SemanticError;

/* function type */
public class ArrowType implements Node {
	
	private ArrayList<Node> parList;
	private Node ret;
	
	public ArrowType (ArrayList<Node> p, Node r) {
		parList = p;
		ret = r;
	}
	
	// TODO: this toPrint() should be checked
	public String toPrint(String indent) {
		String parlstr = "";
		
		for (Node par:parList)
			parlstr += par.toPrint("");
		
		return "ArrowType\n" + parlstr;// + ret.toPrint(" ->") ;
	}
	
	public Node getRet() {
		return ret;
	}
	
	public ArrayList<Node> getParList () {
		return parList;
	}

/*
	public TypeEnum getType() {
		return TypeEnum.ARROW;
	}*/
	
	public Node typeCheck() {
		return null;
	}
	
	public String codeGeneration() {
		return null;
	}
	
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		return null;
	}

	
}  