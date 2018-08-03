package ast.types;

import java.util.HashSet;
import ast.Node;
import utils.Environment;

public class VoidType implements Node {
	
	/**
	 *
	 * Nodo indicativo del tipo Void, relativo agli statement
	 *
	 * */
	public VoidType() {
	}
	
	public String toPrint(String indent) {
		return indent + " VoidType";
	}
	
	public HashSet<String> checkSemantics(Environment env) {
		return new HashSet<>();
	}
	
	public Node typeCheck() {
		return new VoidType();
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