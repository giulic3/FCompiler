package ast;

import ast.types.*;
import utils.*;

import java.util.ArrayList;

/* integer division */
public class DivNode implements Node {

	private Node left;
	private Node right;

	public DivNode (Node l, Node r) {
		left = l;
		right = r;
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {

		ArrayList<SemanticError> res = new ArrayList<SemanticError>();

		res.addAll(left.checkSemantics(env));
		res.addAll(right.checkSemantics(env));

		return res;
	}

	public String toPrint(String s) {
		return s+"Div Node\n" + left.toPrint(s+"\t")
				+ " / "
				+ right.toPrint("") ;
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


}
