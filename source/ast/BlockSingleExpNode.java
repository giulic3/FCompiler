package ast;

import ast.types.BaseType;
import ast.types.IntType;
import utils.Environment;
import utils.SemanticError;

import java.util.ArrayList;

public class BlockSingleExpNode implements Node {
	
	private Node exp;

//	Serve avere il contesto come parametro del costruttore?
//	public IntNode(FOOLParser.IntValContext ctx, int val) {
//		super(ctx);
//		this.val = val;
//	}
	
	public BlockSingleExpNode(Node exp) {
		this.exp=exp;
	}
	
	public String toPrint(String s) {
		return s + "Block Single exp value: \n" + exp.toPrint(s+"\t");
	}
	@Override
	public BaseType typeCheck() {
		return null;
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