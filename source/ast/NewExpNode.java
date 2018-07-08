package ast;

import utils.Environment;
import utils.SemanticError;
import utils.SymbolTableEntry;

import java.util.ArrayList;

public class NewExpNode implements Node {
	
	protected String id;
	protected ArrayList<Node> args;
	protected SymbolTableEntry entry = null;
	protected int callNestingLevel;
	
	public NewExpNode(String ID, ArrayList<Node> args){
		this.id = ID;
		this.args=args;
		
	}
	
	public String toPrint(String s) {
		String msg = s + "New Instance Node: " + this.id + "(";
		
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
		return null;
	}
	
	public String codeGeneration() {
		// TODO: da implementare
		return null;
	}
	
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		// TODO: da implementare
		ArrayList<SemanticError> res = new ArrayList<>();
		
		// TODO: handle offset
		// TODO: IMPORTANT: define unique key management for classes
		SymbolTableEntry entry = env.getActiveDec("Class$"+id);
		if (entry == null)
			res.add(new SemanticError("Class " + id + " not declared\n"));
		
		for (Node a: args)
			res.addAll(a.checkSemantics(env));
		
		return res;
	}
	
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return id;
	}
}