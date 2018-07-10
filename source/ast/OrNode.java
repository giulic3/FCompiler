package ast;

import java.util.ArrayList;
import java.util.HashSet;

import ast.types.BoolType;
import utils.Environment;
;
//import lib.FOOLlib;

public class OrNode implements Node {

	private Node left;
	private Node right;

	public OrNode (Node l, Node r) {
		left = l;
		right = r;
	}

	public String toPrint(String s) {
		return s + "OR Node:\n" + left.toPrint(s+"\t") + "\n"
				+ right.toPrint(s+"\t") ;
	}

	@Override
	public HashSet<String> checkSemantics(Environment env) {
		//create the result
		HashSet<String> res = new HashSet<String>();

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