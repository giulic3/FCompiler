package ast;

import ast.types.BaseType;
import ast.types.IntType;
import utils.Environment;
import utils.SemanticError;
import utils.SymbolTableEntry;

import java.util.ArrayList;

public class MethodExpNode implements Node {
	
	
	protected String obj;
	protected String id;
	protected ArrayList<Node> args;
	protected SymbolTableEntry entry = null;
	protected int callNestingLevel;
	
	public MethodExpNode(String OBJ, String ID, ArrayList<Node> args){
		this.obj = OBJ;
		this.id = ID;
		this.args=args;
	}
	
	
	public String toPrint(String s) {
		String msg = s+"method call: " + this.obj+"."+this.id +" (";
		for (Node b:this.args) {
			msg += "\n "+ s+b.toPrint("\t");
		}
		msg += "\n"+s+")";
		return msg;
	}
	
	
	@Override
	public BaseType typeCheck() {
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

