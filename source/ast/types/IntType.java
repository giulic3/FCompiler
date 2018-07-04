package ast.types;

import java.util.ArrayList;

import ast.Node;
import utils.Environment;
import utils.SemanticError;

public class IntType implements Node {
	
	public IntType() {
	}
	
	// TODO: da controllare
	public String toPrint(String indent) {
		return "IntType";
	}

	/*public TypeEnum getType() {
		return TypeEnum.INT;
	}*/
	
	public Node typeCheck() {
		return new IntType();
	}
	
	public String codeGeneration() {
		// TODO: da implementare
		return null;
	}
	
	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		// TODO: da controllare
		return new ArrayList<>();
	}
}