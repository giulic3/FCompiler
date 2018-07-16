package ast;

import ast.types.BoolType;
import ast.types.IntType;
import utils.Environment;
import utils.Helpers;
;

import java.util.ArrayList;
import java.util.HashSet;

public class PlusNode implements Node {
	
	private Node leftOperand;
	private Node rightOperand;
	
	public PlusNode(Node l, Node r) {
		leftOperand = l;
		rightOperand = r;
	}
	
	public String toPrint(String s) {
		return s + "Plus Node:\n" + leftOperand.toPrint(s+"\t") + "\n" + rightOperand.toPrint(s+"\t");
	}
	
	@Override
	public Node typeCheck() throws Exception {
		if (! ( Helpers.subtypeOf(new IntType(),leftOperand.typeCheck()) &&
				Helpers.subtypeOf(new IntType(),rightOperand.typeCheck()) ) ) {
			throw new Exception("Plus Node typeCheck exception");
		}
		return new IntType();
	}
	
	public String codeGeneration() {
		// TODO: da controllare
		return leftOperand.codeGeneration() + rightOperand.codeGeneration() + "add\n";
	}
	
	public HashSet<String> checkSemantics(Environment env) {
		// TODO: da implementare
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
