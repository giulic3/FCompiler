package ast;

import ast.types.BaseType;
import ast.types.IntType;
import utils.Environment;
import utils.SemanticError;

import java.util.ArrayList;

public class SubNode implements Node {
	
	private Node leftOperand;
	private Node rightOperand;
	
	public SubNode(Node l, Node r) {
		leftOperand = l;
		rightOperand = r;
	}
	
	public String toPrint(String s) {
		return s + "Sub Node:\n" + leftOperand.toPrint(s+"\t") + " - " + rightOperand.toPrint("");
	}
	
	@Override
	public Node typeCheck() {
		return new IntType();
	}
	
	public String codeGeneration() {
		// TODO: da implementare
		return null;
	}
	
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		// TODO: da implementare
		return null;
	}
}
