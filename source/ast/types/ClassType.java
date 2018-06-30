package ast.types;

import java.util.ArrayList;

import utils.Environment;
//import util.SemanticError;

public class ClassType implements BaseType {

	// tipo
	// supertipo
	// campi
	// metodi

	public ClassType() {
	}
	
	public String toPrint() {
		return "ClassType";
	}

	public TypeEnum getType() {
		return TypeEnum.CLASS;
	}



}