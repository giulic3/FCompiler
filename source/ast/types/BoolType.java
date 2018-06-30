package ast.types;

import java.util.ArrayList;

import utils.Environment;

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