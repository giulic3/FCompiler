package ast;

import ast.types.BaseType;
import ast.types.IntType;
import utils.Environment;
import utils.SemanticError;
import utils.SymbolTableEntry;

import java.util.ArrayList;

/* method call */
public class MethodNode implements Node {
	
	
	private String obj;
	private String id;
	private ArrayList<Node> args;
	private SymbolTableEntry entry = null;
	private int callNestingLevel;
	private boolean isExp;

	public MethodNode(String obj, String ID, ArrayList<Node> args, boolean isExp){
		this.obj = obj;
		this.id = ID;
		this.args = args;
		this.isExp = isExp;
	}
	
	
	public String toPrint(String s) {
		String msg = s+"Method call: " + this.obj+"."+this.id +" (";
		for (Node b:this.args) {
			msg += "\n "+ s+b.toPrint("\t");
		}
		msg += "\n"+s+")";
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

