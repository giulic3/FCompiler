package ast;

import java.util.HashSet;
import ast.types.BoolType;
import utils.Environment;
import utils.Helpers;

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

	public HashSet<String> checkSemantics(Environment env) {
		//create the result
		HashSet<String> res = new HashSet<String>();

		//check semantics in the left and in the right exp

		res.addAll(left.checkSemantics(env));
		res.addAll(right.checkSemantics(env));

		return res;
	}

	public Node typeCheck() throws Exception {
		if (!(Helpers.subtypeOf(new BoolType(), left.typeCheck()) && Helpers.subtypeOf(new BoolType(),right.typeCheck())))
			throw new Exception("And Node typeCheck exception");
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