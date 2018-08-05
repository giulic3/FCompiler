package ast;

import ast.types.ClassType;
import org.antlr.v4.runtime.ParserRuleContext;
import utils.Environment;
import utils.Helpers;
import utils.SymbolTableEntry;
import java.util.ArrayList;
import java.util.HashSet;

public class NewExpNode implements Node {
	
	protected String id;
	protected SymbolTableEntry entry = null;
	private ParserRuleContext ctx;
	
	public NewExpNode(String ID, ParserRuleContext ctx){
		this.id = ID;
		this.ctx = ctx;
	}
	
	// TODO: prova
	public Node copyInstance() {
		ParserRuleContext ctx = new ParserRuleContext();
		ctx.copyFrom(this.ctx);
		NewExpNode copy = new NewExpNode(this.id, ctx);
		copy.entry = SymbolTableEntry.copyInstance(this.entry);
		return copy;
	}
	
	public String toPrint(String s) {
		return s + "New Instance Node: " + this.id + "()";
	}
	
	public SymbolTableEntry getSTEntry() {
		return entry;
	}
	
	public HashSet<String> checkSemantics(Environment env) {
		HashSet<String> res = new HashSet<String>();
		
		SymbolTableEntry entry = env.getClassEntry(id);
		if (entry == null)
			res.add("Class " + id + " not declared at line " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n");
		else
			this.entry = entry;
		
		return res;
	}
	
	public Node typeCheck() {
		return entry.getType();
	}
	
	public String codeGeneration() {
		ClassType classContent = (ClassType) entry.getType();
		ArrayList<Node> fields = classContent.getFieldsList(true);
		int i=fields.size()-1;
		StringBuilder argsCode = new StringBuilder();
		while(i>=0){
			argsCode.append(fields.get(i).codeGeneration());
			i--;
		}
		
		return argsCode + "push " + fields.size() + "\n" +
				"push " + Helpers.getDispatchTableLabelForClass(id) + "\n" +
				"new\n";
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return id;
	}
}