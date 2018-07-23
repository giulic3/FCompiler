package ast;

import ast.types.NullType;
import utils.Environment;
import java.util.HashSet;

public class NullNode implements Node {
	
	public NullNode() {}
	
	public String toPrint(String indent) {
		return indent + "Null Node";
	}
	
	public HashSet<String> checkSemantics(Environment env) {
		// TODO: da controllare
		return new HashSet<String>();
	}
	
	public Node typeCheck() {
		// TODO: da implementare
		return new NullType();
	}
	
	public String codeGeneration() {
		// TODO: da implementare
		return "push " + Integer.toString(Integer.MIN_VALUE) + "\n";
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return null;
	}
}
