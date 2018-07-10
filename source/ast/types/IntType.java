package ast.types;

import java.util.ArrayList;
import java.util.HashSet;

import ast.Node;
import utils.Environment;
;

public class IntType implements Node {
	
	public IntType() {
	}
	
	// TODO: da controllare
	public String toPrint(String indent) {
		return "IntType";
	}

	/*public TypeEnum getType() {
		return TypeEnum.INT;
	}*/
	
	public Node typeCheck() {
		return new IntType();
	}
	
	public String codeGeneration() {
		// TODO: da implementare
		return null;
	}
	
	@Override
	public HashSet<String> checkSemantics(Environment env) {
		// TODO: da controllare
		return new HashSet<String>();
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return null;
	}
}