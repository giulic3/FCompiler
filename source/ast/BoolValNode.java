package ast;

import ast.types.BoolType;
import utils.Environment;
import java.util.HashSet;

public class BoolValNode implements Node {
	
	private boolean value;
	
	/**
	 *
	 * Rappresenta i valori booleani
	 *
	 * */
	public BoolValNode(boolean n) {
		value = n;
	}
	
	public String toPrint(String s) {
		return s + "Boolean value: " + value;
	}
	
	public HashSet<String> checkSemantics(Environment env) {
		return new HashSet<>();
	}
	
	public Node typeCheck() {
		return new BoolType();
	}
	
	public String codeGeneration() {
		return "push "+(value ? 1 : 0)+"\n";
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return null;
	}
}
