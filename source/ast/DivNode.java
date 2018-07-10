package ast;

import ast.types.*;
import utils.*;

import java.util.ArrayList;
import java.util.HashSet;

/* integer division */
public class DivNode implements Node {

	private Node left;
	private Node right;

	public DivNode (Node l, Node r) {
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
		return s+"Div Node\n" + left.toPrint(s+"\t")
				+ "\n"
				+ right.toPrint(s+"\t") ;
	}

	public Node typeCheck() {
		/*
		if (! ( FOOLlib.isSubtype(left.typeCheck(),new IntType()) &&
				FOOLlib.isSubtype(right.typeCheck(),new IntType()) ) ) {
			System.out.println("Non integers in multiplication");
			System.exit(0);
		}
		*/
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
