package ast.types;

import java.util.HashSet;
import ast.Node;
import utils.Environment;


public class BoolType implements Node {
	
	/**
	 *
	 * Nodo indicativo del tipo Booleano, è gestito come sottotipo di Integer in FOOL.
	 *
	 * */
	public BoolType() {
	}
	
	// TODO: prova
	public Node copyInstance() {
		return new BoolType();
	}
	
	public String toPrint(String indent) {
		return "BoolType";
	}
	
	public HashSet<String> checkSemantics(Environment env) {
		return new HashSet<>();
	}
	
	public Node typeCheck() {
		return new BoolType();
	}
	
	public String codeGeneration() {
		return null;
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID(){
		return null;
	}
}