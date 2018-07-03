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
		String msg = s+"Method Call Node:\n" + s + "\tObject: " + this.obj + "\n" + s + "\tMethod: " + this.id +"(";
		if (this.args != null && !this.args.isEmpty()) {
			for (Node p : this.args) {
				msg += "\n " + s + p.toPrint("\t\t");
			}
			msg += "\n" + s + "\t)";
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

