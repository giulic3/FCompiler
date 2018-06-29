package ast;

import java.util.ArrayList;

import util.Environment;
import util.SemanticError;

public class VoidType implements BaseType {
	
	public VoidType() {
	}
	
	public String toPrint(String s) {
		return s + "VoidType\n";
	}
	
	
	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		
		return new ArrayList<SemanticError>();
	}
	
	
	
}