package ast.types;

import java.util.ArrayList;

import ast.Node;
import utils.Environment;
import utils.SemanticError;

public class BoolType implements Node {
	
	public BoolType() {
	}
	
	public String toPrint(String indent) {
		return "BoolType";
	}

	/*public TypeEnum getType() {
		return TypeEnum.BOOL;
	}*/
	
	public Node typeCheck() {
		return new BoolType(); // TODO: decidere se Bool <= Int
	}
	
	public String codeGeneration() {
		// TODO: da implementare
		return null;
	}
	
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		// TODO: da controllare
		return new ArrayList<>();
	}
	
}  