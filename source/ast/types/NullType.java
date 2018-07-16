package ast.types;

import ast.Node;
import utils.Environment;

import java.util.HashSet;

public class NullType extends ClassType {
	//TODO: trovare soluzione migliore per il tipo null e controllare bene typechecking
	public NullType(){
		super(null, null, null,null, null);
	}
	
	public String toPrint(String indent) {
		return indent + "Null Type"; // TODO: to be updated
	}
	
	public HashSet<String> checkSemantics(Environment env) {
		// TODO: non dovrebbe servire
		return new HashSet<>();
	}
	
	public Node typeCheck() {
		// TODO: da implementare
		return this;
	}
	
	public String codeGeneration() {
		// TODO: da implementare
		return null;
	}
	
	public String getID() {
		return "Null Type";
	}
	
	
}
