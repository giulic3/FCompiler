package ast.types;


import java.util.ArrayList;

import utils.Environment;



public class VoidType implements BaseType {
	
	public VoidType() {
	}
	
	public String toPrint() {
		return "VoidType";
	}

	public TypeEnum getType() {
		return TypeEnum.VOID;
	}
	
}