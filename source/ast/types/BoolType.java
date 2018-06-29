package ast;

import java.util.ArrayList;

import util.Environment;
import util.SemanticError;

public class BoolType implements BaseType {
	
	public BoolType() {
	}
	
	public String toPrint() {
		return "BoolType";
	}

	public TypeEnum getType() {
		return TypeEnum.BOOL;
	}

	
	
}  