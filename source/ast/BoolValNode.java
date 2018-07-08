package ast;

import ast.types.BoolType;
import utils.Environment;
import utils.SemanticError;

import java.util.ArrayList;

public class BoolValNode implements Node {
	
	private boolean value;
	
	public BoolValNode(boolean n) {
		value = n;
	}
	
	public String toPrint(String s) {
		return s + "Boolean value: " + value;
	}
	@Override
	public Node typeCheck() {
		return new BoolType();
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
