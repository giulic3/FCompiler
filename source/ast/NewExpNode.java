package ast;

import org.antlr.v4.runtime.ParserRuleContext;
import utils.Environment;
import utils.SymbolTableEntry;

import java.util.ArrayList;
import java.util.HashSet;

public class NewExpNode implements Node {
	
	protected String id;
	protected SymbolTableEntry entry = null;
	protected int callNestingLevel;
	private ParserRuleContext ctx;
	
	public NewExpNode(String ID, ParserRuleContext ctx){
		this.id = ID;
		this.ctx = ctx;
	}
	
	public String toPrint(String s) {
		return s + "New Instance Node: " + this.id + "()";
	}
	
	public SymbolTableEntry getSTEntry() {
		return entry;
	}
	
	
	@Override
	public Node typeCheck() {
		return entry.getType();
	}
	
	public String codeGeneration() {
		// TODO: da implementare
		return null;
	}
	
	public HashSet<String> checkSemantics(Environment env) {
		// TODO: da implementare
		HashSet<String> res = new HashSet<String>();
		
		// TODO: handle offset
		// TODO: IMPORTANT: define unique key management for classes
		SymbolTableEntry entry = env.getClassEntry(id);
		if (entry == null)
			res.add("Class " + id + " not declared at line " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n");
		else
			this.entry = entry;
		
		return res;
	}
	
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return id;
	}
}