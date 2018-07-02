package ast;

import ast.types.*;
import utils.*;

import java.util.ArrayList;

public class IntValNode implements Node {
	
	private Integer value;
	
	public IntValNode(Integer n) {
		value = n;
	}
	
	public String toPrint(String s) {
		return s + "Integer value: " + Integer.toString(value);
	}
	@Override
	public Node typeCheck() {
		return new IntType();
	}
	
	public String codeGeneration() {
		// TODO: da implementare
		return null;
	}
	
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		// TODO: da implementare
		return null;
	}

}
