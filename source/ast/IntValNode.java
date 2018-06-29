package ast;

import utils.Environment;
import utils.SymbolTableEntry;

public class IntValNode implements Node {
	
	private Integer value;

//	Serve avere il contesto come parametro del costruttore?
//	public IntNode(FOOLParser.IntValContext ctx, int val) {
//		super(ctx);
//		this.val = val;
//	}
	
	public IntValNode(Integer n) {
		value = n;
	}
	
	public String toPrint(String s) {
		return "Integer value: " + Integer.toString(value);
	}
	
	public Node typeCheck() {
		return new IntValNode();
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
