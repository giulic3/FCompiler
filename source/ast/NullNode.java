package ast;

import utils.Environment;
import utils.SemanticError;

import java.util.ArrayList;

public class NullNode implements Node {
	
	public NullNode() {}
	
	public String toPrint(String indent) {
		return indent + "Null Node";
	}
	
	public Node typeCheck() {
		// TODO: da implementare
		return null;
	}
	
	public String codeGeneration() {
		// TODO: da implementare
		return null;
	}
	
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		// TODO: da controllare
		return new ArrayList<>();
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return null;
	}
}
