package ast.types;

import java.util.ArrayList;

import utils.Environment;
//import utils.SemanticError;

public class IntType implements BaseType {
	
	public IntType() {
	}
	
	public String toPrint() {
		return "IntType";
	}

	public TypeEnum getType() {
		return TypeEnum.INT;
	}
	
	/*@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		
		return new ArrayList<SemanticError>();
	}*/
}