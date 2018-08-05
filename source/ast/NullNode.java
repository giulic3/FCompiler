package ast;

import ast.types.NullType;
import utils.Environment;
import java.util.HashSet;

public class NullNode implements Node {
	
	public NullNode() {}
	
	// TODO: prova
	public Node copyInstance() {
		return new NullNode();
	}
	
	public String toPrint(String indent) {
		return indent + "Null Node";
	}
	
	public HashSet<String> checkSemantics(Environment env) {
		return new HashSet<>();
	}
	
	public Node typeCheck() {
		return new NullType();
	}
	
	public String codeGeneration() {
		return "push " + Integer.toString(Integer.MAX_VALUE) + "\n";
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return null;
	}
}
