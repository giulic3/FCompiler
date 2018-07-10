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
	public HashSet<String> checkSemantics(Environment env) {
		
		return new HashSet<String>();
	}
	
	//non utilizzato
	public String codeGeneration() {
		return "";
	}
	
	
}  