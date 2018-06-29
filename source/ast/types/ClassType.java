package ast;

import java.util.ArrayList;

import util.Environment;
import util.SemanticError;

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