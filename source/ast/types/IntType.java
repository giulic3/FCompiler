package ast.types;

import java.util.HashSet;
import ast.Node;
import utils.Environment;

public class IntType implements Node {
	
	private String classID = null;
	
	/**
	 *
	 * Nodo indicativo del tipo Intero
	 *
	 * */
	public IntType() {
	}
	
	// TODO: da controllare
	public String toPrint(String indent) {
		return "IntType";
	}
	
	public HashSet<String> checkSemantics(Environment env) {
		// TODO: da controllare
		return new HashSet<String>();
	}
	
	public Node typeCheck() {
		return new IntType();
	}
	
	public String codeGeneration() {
		// TODO: da implementare
		return null;
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return null;
	}
	
	public void setClassID(String id) {
		this.classID = id;
	}
}