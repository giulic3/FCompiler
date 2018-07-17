package ast;

import java.util.HashSet;
import ast.types.BoolType;
import utils.Environment;
import utils.Helpers;


public class AndNode implements Node {
	
	/**
	 *
	 * Nodo per la gestione dell'<strong>AND</strong> tra due Booleani.
	 *
	 * */

	private Node left;
	private Node right;

	public AndNode (Node l, Node r) {
		left = l;
		right = r;
	}

	public String toPrint(String s) {
		return s + "AND Node\n" + left.toPrint(s+"\t") + "\n"
				+ right.toPrint(s+"\t") ;
	}

	public HashSet<String> checkSemantics(Environment env) {
		//create the result
		HashSet<String> res = new HashSet<String>();

		//check semantics in the left and in the right exp

		res.addAll(left.checkSemantics(env));
		res.addAll(right.checkSemantics(env));

		return res;
	}
	
	/**
	 *
	 * Applica il controllo su le due espressioni a destra e sinistra dell'AND per verificare che siano entrambe di tipo Booleano.
	 *
	 * */
	public Node typeCheck() throws Exception {
		if (!(Helpers.subtypeOf(left.typeCheck(), new BoolType()) && Helpers.subtypeOf(right.typeCheck(), new BoolType())))
			throw new Exception("And Node typeCheck exception");
		return new BoolType();
	}

	public String codeGeneration() {
		String exitLabel = Helpers.newLabel();
		String equalValues = Helpers.newLabel();
		String bothTrue = Helpers.newLabel();
		
		String leftAssembly = left.codeGeneration();
		String rightAssembly = right.codeGeneration();
		
		return  leftAssembly +
				rightAssembly +
				"beq " + equalValues + "\n" +
				"push 0\n" +
				"b " + exitLabel + "\n" +
				equalValues + ":\n" +
				leftAssembly +
				"push 1\n" +
				"beq " + bothTrue + "\n" +
				"push 0\n" +
				"b " + exitLabel + "\n" +
				bothTrue + ":\n" +
				"push 1\n" +
				exitLabel + ":\n";
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return null;
	}
}