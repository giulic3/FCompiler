package ast;

import ast.types.*;
import utils.*;
import java.util.HashSet;

public class TimesNode implements Node {

	private Node leftOperand;
	private Node rightOperand;

	public TimesNode (Node l, Node r) {
		leftOperand = l;
		rightOperand = r;
	}
	
	public String toPrint(String s) {
		return s+"Times Node\n"
				+ leftOperand.toPrint(s+"\t")
				+ "\n"
				+ rightOperand.toPrint(s+"\t") ;
	}

	public HashSet<String> checkSemantics(Environment env) {

		HashSet<String> res = new HashSet<String>();

		res.addAll(leftOperand.checkSemantics(env));
		res.addAll(rightOperand.checkSemantics(env));

		return res;
	}
	
	public Node typeCheck() throws Exception {
		
		if (! ( Helpers.subtypeOf(new IntType(), leftOperand.typeCheck()) &&
				Helpers.subtypeOf(new IntType(), rightOperand.typeCheck()) ) ) {
			throw new Exception("Non integers in multiplication");
		}
		
		return new IntType();
	}

	public String codeGeneration() {
		// TODO: da controllare
		return leftOperand.codeGeneration() + rightOperand.codeGeneration() + "mult\n";
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return null;
	}
}
