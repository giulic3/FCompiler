package ast;

import java.util.ArrayList;
import java.util.HashSet;

import ast.types.BoolType;
import utils.Environment;
import utils.Helpers;
;
//import lib.FOOLlib;

public class EqNode implements Node {

	private Node left;
	private Node right;

	public EqNode (Node l, Node r) {
		left = l;
		right = r;
	}

	public String toPrint(String s) {
		return s + "Equal Node:\n" + left.toPrint(s+"\t") + "\n"
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

	public Node typeCheck() throws Exception {
		
		Node l = left.typeCheck();
		Node r = right.typeCheck();
		if (! ( Helpers.subtypeOf(l,r) || Helpers.subtypeOf(r,l))) {
			throw new Exception("Incompatible types in equal");
		}
		return new BoolType();
	}

	public String codeGeneration() {
		/*
		String l1 = FOOLlib.freshLabel();
		String l2 = FOOLlib.freshLabel();
		return left.codeGeneration()+
				right.codeGeneration()+
				"beq "+ l1 +"\n"+
				"push 0\n"+
				"b " + l2 + "\n" +
				l1 + ":\n"+
				"push 1\n" +
				l2 + ":\n";
		*/

		return "";
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return null;
	}
}