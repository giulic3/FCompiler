package ast;

import ast.types.IntType;
import utils.Environment;
;

import java.util.ArrayList;
import java.util.HashSet;

public class SubNode implements Node {
	
	private Node leftOperand;
	private Node rightOperand;
	
	public SubNode(Node l, Node r) {
		leftOperand = l;
		rightOperand = r;
	}
	
	public String toPrint(String s) {
		return s + "Sub Node:\n" + leftOperand.toPrint(s+"\t") + "\n" + rightOperand.toPrint(s+"\t");
	}
	
	@Override
	public Node typeCheck() {
		return new IntType();
	}
	
	public String codeGeneration() {
		// TODO: da implementare
		return null;
	}
	
	public HashSet<String> checkSemantics(Environment env) {
		HashSet<String> res = new HashSet<String>();
		
		//check semantics in the left and in the right exp
		
		res.addAll(leftOperand.checkSemantics(env));
		res.addAll(rightOperand.checkSemantics(env));
		
		return res;
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return null;
	}
}
