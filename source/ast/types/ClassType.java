package ast;

import java.util.ArrayList;

import util.Environment;
import util.SemanticError;

public class ClassType implements BaseType {
	
	public ClassType() {
	}
	
	public String toPrint(String s) {
		return s + "ClassType\n";
	}
	
	
	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		
		return new ArrayList<SemanticError>();
	}
	
}