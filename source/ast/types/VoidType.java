package ast.types;

import java.util.ArrayList;

import ast.Node;
import utils.Environment;
import utils.SemanticError;

public class VoidType implements Node {
	
	public VoidType() {
	}
	
	// TODO: da controllare
	public String toPrint(String indent) {
		return "VoidType";
	}

	/*public TypeEnum getType() {
		return TypeEnum.VOID;
	}*/
	
	public Node typeCheck() {
		return new VoidType(); // TODO: da controllare
	}
	
	public String codeGeneration() {
		// TODO: da implementare
		return null;
	}
	
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		// TODO: da implementare
		return null;
	}
	
}