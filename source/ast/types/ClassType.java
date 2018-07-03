package ast.types;

import java.util.ArrayList;

import ast.Node;
import utils.Environment;
import utils.SemanticError;
//import util.SemanticError;

public class ClassType implements Node {

	// tipo
	// supertipo
	// campi
	// metodi

	public ClassType() {
	}
	
	public String toPrint(String indent) {
		return "ClassType";
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
		return null;
	}

}