package ast;

import ast.types.*;
import utils.*;

import java.util.ArrayList;

public class IntValNode implements Node {
	
	private int value;
	
	public IntValNode(int n) {
		value = n;
	}
	
	public String toPrint(String s) {
		return s + "Integer value: " + value;
	}
	@Override
	public Node typeCheck() {
		return new IntType();
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
