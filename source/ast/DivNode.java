package ast;

import ast.types.*;
import utils.*;
import java.util.HashSet;

/* integer division */
public class DivNode implements Node {

	private Node leftOperand;
	private Node rightOperand;
	
	/**
	 *
	 * Gestisce gli operandi della Divisione.
	 *
	 */
	public DivNode (Node l, Node r) {
		leftOperand = l;
		rightOperand = r;
	}
	
	public String toPrint(String s) {
		return s+"Div Node\n" + leftOperand.toPrint(s+"\t")
				+ "\n"
				+ rightOperand.toPrint(s+"\t") ;
	}
	
	public HashSet<String> checkSemantics(Environment env) {

		HashSet<String> res = new HashSet<String>();

		res.addAll(leftOperand.checkSemantics(env));
		res.addAll(rightOperand.checkSemantics(env));

		return res;
	}
	
	/**
	 *
	 * La divisione viene effettuata solo su tipi interi e suoi sottotipi, ad esempio i booleani.
	 *
	 * */
	public Node typeCheck()throws Exception {
		
		if (! ( Helpers.subtypeOf(leftOperand.typeCheck(),new IntType()) &&
				Helpers.subtypeOf(rightOperand.typeCheck(),new IntType()) ) ) {
			throw new Exception("Non integers in division");
		}
		return new IntType();
	}

	public String codeGeneration() {
		// TODO: da controllare
		return leftOperand.codeGeneration() + rightOperand.codeGeneration() + "div\n";
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return null;
	}
}
