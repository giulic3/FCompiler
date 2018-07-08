package ast;

import java.util.ArrayList;

import ast.types.BoolType;
import utils.Environment;
import utils.SemanticError;
//import lib.FOOLlib;

public class GeqNode implements Node {

	private Node left;
	private Node right;

	public GeqNode (Node l, Node r) {
		left = l;
		right = r;
	}

	public String toPrint(String s) {
		return s + "Greater/Equal Node:\n" + left.toPrint(s+"\t") + "\n"
				+ right.toPrint(s+"\t") ;
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		//create the result
		ArrayList<SemanticError> res = new ArrayList<SemanticError>();

		//check semantics in the left and in the right exp

		res.addAll(left.checkSemantics(env));
		res.addAll(right.checkSemantics(env));

		return res;
	}

	public Node typeCheck() {

		return new BoolType();
	}

	public String codeGeneration() {

		return "";
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return null;
	}
}