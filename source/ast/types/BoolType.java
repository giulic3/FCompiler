package ast.types;

import java.util.ArrayList;
import java.util.HashSet;

import ast.Node;
import utils.Environment;
;

public class BoolType implements Node {
	
	public BoolType() {
	}
	
	public String toPrint(String indent) {
		return "BoolType";
	}

	/*public TypeEnum getType() {
		return TypeEnum.BOOL;
	}*/
	
	public Node typeCheck() {
		return new BoolType(); // TODO: decidere se Bool <= Int
	}
	
	public String codeGeneration() {
		// TODO: da implementare
		return null;
	}
	
	public HashSet<String> checkSemantics(Environment env) {
		// TODO: da controllare
		return new HashSet<String>();
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID(){
		return null;
	}
}