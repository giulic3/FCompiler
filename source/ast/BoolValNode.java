package ast;

import ast.types.BaseType;
import ast.types.IntType;
import utils.Environment;
import utils.SemanticError;

import java.util.ArrayList;

public class BoolValNode implements Node {
	
	private Boolean value;

//	Serve avere il contesto come parametro del costruttore?
//	public IntNode(FOOLParser.IntValContext ctx, int val) {
//		super(ctx);
//		this.val = val;
//	}
	
	public BoolValNode(Boolean n) {
		value = n;
	}
	
	public String toPrint(String s) {
		return s + "Boolean value: " + Boolean.toString(value);
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
