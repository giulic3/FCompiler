package ast;

import ast.types.*;
import utils.*;

import java.util.ArrayList;

public class TimesNode implements Node {

	private Node left;
	private Node right;

	public TimesNode (Node l, Node r) {
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
		return s+"Times\n" + left.toPrint(s+"  ")
				+ right.toPrint(s+"  ") ;
	}

	public BaseType typeCheck() {
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
