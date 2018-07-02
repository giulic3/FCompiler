package ast.types;

import java.util.ArrayList;

import ast.Node;
import com.sun.xml.internal.rngom.parse.host.Base;
import utils.Environment;
import utils.SemanticError;
//import utils.SemanticError;

public class IntType implements Node {
	
	public IntType() {
	}
	
	public String toPrint(String indent) {
		return "IntType";
	}

	/*public TypeEnum getType() {
		return TypeEnum.INT;
	}*/
	
	public BaseType typeCheck() {
		return null;
	}
	
	public String codeGeneration() {
		return null;
	}
	
	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		
		return null;
	}
}