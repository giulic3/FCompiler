package ast.types;

import java.util.ArrayList;
import java.util.HashSet;

import ast.Node;
import utils.Environment;
;
import utils.SymbolTableEntry;

public class ClassType implements Node {


	private String id;
	private ClassType supertype;
	// campi TODO : arraylist? hashmap?
	// metodi

	public ClassType(String id) {
		this.id = id;
	}
	
	public String toPrint(String indent) {
		return id;
	}

	/*public TypeEnum getType() {
		return TypeEnum.CLASS;
	}*/
	
	public Node typeCheck() {
		// TODO: da implementare
		return null;
	}
	
	public String codeGeneration() {
		// TODO: da implementare
		return null;
	}
	
	public HashSet<String> checkSemantics(Environment env) {
		// TODO: da implementare
		HashSet<String> res = new HashSet<String>();
		
		SymbolTableEntry entry = env.getActiveDec("Class$"+id);
		if (entry == null)
			res.add("Class " + id + " not declared\n");
		
		return res;
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return id;
	}
}