package ast;

import ast.types.BaseType;
import ast.types.IntType;
import utils.Environment;
import utils.SemanticError;
import utils.SymbolTableEntry;

import java.util.ArrayList;

public class FunExpNode implements Node {
	
	protected String id;
	protected ArrayList<Node> args;
	protected SymbolTableEntry entry = null;
	protected int callNestingLevel;
	
	public FunExpNode(String ID, ArrayList<Node> args){
		this.id = ID;
		this.args=args;
	}
	
	
	public String toPrint(String s) {
		String msg = s+"Function Call Node: " + this.id + "(";
		
		if (this.args != null && !this.args.isEmpty()) {
			for (Node b : this.args) {
				msg += "\n " + s + b.toPrint("\t");
			}
			msg += "\n" + s + ")";
		} else
			msg += ")";
		
		return msg;
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