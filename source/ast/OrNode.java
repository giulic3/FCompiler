package ast;

import java.util.ArrayList;

import ast.types.BaseType;
import ast.types.BoolType;
import utils.Environment;
import utils.SemanticError;
//import lib.FOOLlib;

public class OrNode implements Node {

	private Node left;
	private Node right;

	public OrNode (Node l, Node r) {
		left = l;
		right = r;
	}

	public String toPrint(String s) {
		return s+"Or\n" + left.toPrint(s+"  ")
				+ right.toPrint(s+"  ") ;
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

}