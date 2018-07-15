package ast;

import ast.types.*;
import utils.*;

import java.util.ArrayList;
import java.util.HashSet;

public class TimesNode implements Node {

	private Node left;
	private Node right;

	public TimesNode (Node l, Node r) {
		left = l;
		right = r;
	}

	@Override
	public HashSet<String> checkSemantics(Environment env) {

		HashSet<String> res = new HashSet<String>();

		res.addAll(left.checkSemantics(env));
		res.addAll(right.checkSemantics(env));

		return res;
	}

	public String toPrint(String s) {
		return s+"Times Node\n"
				+ left.toPrint(s+"\t")
				+ "\n"
				+ right.toPrint(s+"\t") ;
	}
	
	public Node typeCheck() throws Exception {
		
		if (! ( Helpers.subtypeOf(left.typeCheck(),new IntType()) &&
				Helpers.subtypeOf(right.typeCheck(),new IntType()) ) ) {
			throw new Exception("Non integers in multiplication");
		}
		
		return new IntType();
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
