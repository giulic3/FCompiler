package ast;

import java.util.ArrayList;

import util.Environment;
import util.SemanticError;

public class BoolType implements Node {
	
	public BoolType() {
	}
	
	public String toPrint(String s) {
		return s + "BoolType\n";
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