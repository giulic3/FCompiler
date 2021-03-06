package ast.types;

import java.util.HashSet;
import ast.Node;
import utils.Environment;

public class IntType implements Node {
	
	/**
	 *
	 * Nodo indicativo del tipo Intero
	 *
	 * */
	public IntType() {
	}
	
	public Node copyInstance() {
		return new IntType();
	}
	
	public String toPrint(String indent) {
		return "IntType";
	}
	
	public HashSet<String> checkSemantics(Environment env) {
		return new HashSet<>();
	}
	
	public Node typeCheck() {
		return new IntType();
	}
	
	public String codeGeneration() {
		return null;
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return null;
	}
}