package ast;

import java.util.ArrayList;

import util.Environment;
import util.SemanticError;

public class IntType implements Node {
	
	public IntType() {
	}
	
	public String toPrint(String s) {
		return s + "IntType\n";
	}
	
	//non utilizzato
	public Node typeCheck() {
		return null;
	}
	
	//non utilizzato
	public String codeGeneration() {
		return "";
	}
	
	@Override
	public HashSet<String> checkSemantics(Environment env) {
		
		return new HashSet<String>();
	}
	
}  