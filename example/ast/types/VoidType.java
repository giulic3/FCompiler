package ast;

import java.util.ArrayList;

import util.Environment;
import util.SemanticError;

public class VoidType implements Node {
	
	public VoidType() {
	}
	
	public String toPrint(String s) {
		return s + "VoidType\n";
	}
	
	//non utilizzato
	public Node typeCheck() {
		return null;
	}
	
	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		
		return new ArrayList<SemanticError>();
	}
	
	//non utilizzato
	public String codeGeneration() {
		return "";
	}
	
	
}