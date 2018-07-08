package ast.types;

import java.util.ArrayList;

import ast.Node;
import utils.Environment;
import utils.SemanticError;
//import util.SemanticError;

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
	
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		// TODO: da implementare
		return new ArrayList<>();
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return id;
	}

}