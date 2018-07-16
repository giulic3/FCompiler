package ast;

import ast.types.*;
import utils.*;
import java.util.HashSet;

public class IntValNode implements Node {
	
	private int value;
	
	public IntValNode(int n) {
		value = n;
	}
	
	public String toPrint(String s) {
		return s + "Integer value: " + value;
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
		return  "push "+value+"\n";
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return null;
	}
}
