package ast;

import java.util.ArrayList;

import ast.types.BaseType;
import ast.types.BoolType;
import utils.Environment;
import utils.SemanticError;
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
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		//create the result
		ArrayList<SemanticError> res = new ArrayList<SemanticError>();

		//check semantics in the left and in the right exp

		res.addAll(left.checkSemantics(env));
		res.addAll(right.checkSemantics(env));

		return res;
	}

	public Node typeCheck() {
		/*
		BaseType l = left.typeCheck();
		BaseType r = right.typeCheck();
		if (! ( FOOLlib.isSubtype(l,r) || FOOLlib.isSubtype(r,l) ) ) {
			System.out.println("Incompatible types in equal");
			System.exit(0);
		}
		*/
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

}