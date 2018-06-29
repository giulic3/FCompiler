package ast;

import java.util.ArrayList;

import util.Environment;
import util.SemanticError;

public class IntType implements BaseType {
	
	public IntType() {
	}
	
	public String toPrint() {
		return "IntType";
	}

	public TypeEnum getType() {
		return TypeEnum.INT;
	}
	
	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		
		return new ArrayList<SemanticError>();
	}
}  