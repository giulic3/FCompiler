package ast;

import java.util.ArrayList;

import util.Environment;
import util.SemanticError;

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