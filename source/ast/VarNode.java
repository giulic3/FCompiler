package ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import ast.types.ClassType;
import ast.types.VoidType;
import org.antlr.v4.runtime.ParserRuleContext;
import utils.Environment;
import utils.Helpers;
import utils.SymbolTableEntry;

public class VarNode implements Node {
	private ParserRuleContext ctx;
	private String id;
	private Node type;
	private Node exp;
	private String classID = null;
	private SymbolTableEntry entry;
	
	public VarNode(String i, Node t, ParserRuleContext ctx) {
		this.ctx=ctx;
		id = i;
		type = t;
		exp = null;
	}
	
	public VarNode(String i, Node t, ParserRuleContext ctx , Node v) {
		this.ctx=ctx;
		id = i;
		type = t;
		exp = v;
	}
	
	public SymbolTableEntry getSTEntry(){
		return this.entry;
	}
	
	public ParserRuleContext getCtx(){
		return ctx;
	}
	
	public void setInsideClass(String id) {
		this.classID = id;
	}
	
	public String getClassID() {
		return this.classID;
	}
	
	public String toPrint(String s){
		if (exp != null)
			return s + "Var Node: " + id + " (type: " + type.toPrint("") + ")\n" + exp.toPrint(s+"\t");
		else
			return s + "Var Node: " + id + " (type: " + type.toPrint("") + ")";
	}
	
	public Node typeCheck() throws Exception {
		if(exp!=null) {
			if (!Helpers.subtypeOf(exp.typeCheck(), type)) {
				throw new Exception("Var Node typeCheck exception");
			}
		}
		
		return new VoidType();
	}
	
	public Node getType(){
		return type;
	}
	
	public void setType(Node type) {
		this.type = type;
	}
	
	public String toPrint(String s){
		if (exp != null)
			return s + "Var Node: " + id + " (type: " + type.toPrint("") + ")\n" + exp.toPrint(s+"\t");
		else
			return s + "Var Node: " + id + " (type: " + type.toPrint("") + ")";
	}
	
	public HashSet<String> checkSemantics(Environment env){
		HashSet<String> res = new HashSet<String>();
		
		if (exp!=null) res.addAll(exp.checkSemantics(env));
		
		res.addAll(type.checkSemantics(env));
		
		//env.offset = -2;
		HashMap<String, SymbolTableEntry> hm = env.getSymTable().get(env.getNestingLevel());
		SymbolTableEntry entry = new SymbolTableEntry(env.getNestingLevel(), env.decrementOffset(), type); //separo introducendo "entry"
		
		String ID = (this.classID != null) ? "Class$" + this.classID + "$" + id : id;
		
		if (exp instanceof NewExpNode) {
			NewExpNode newNode = (NewExpNode) exp;
			SymbolTableEntry newEntry = newNode.getSTEntry();
			if (newEntry != null) {
				entry.setType(newEntry.getType());
				setType(newEntry.getType());
			}
		}
		
		if (hm.put(ID, entry) != null)
			res.add("Var or Par id " + id + " already declared at line " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n");
		else
			this.entry = entry;
		
		return res;
	}
	
	public Node typeCheck() throws Exception {
		if(exp!=null) {
			if (!Helpers.subtypeOf(exp.typeCheck(), type)) {
				throw new Exception("Assignment Node typeCheck exception");
			}
		}
		
		return new VoidType();
	}
	
	public String codeGeneration() {
		// TODO: da controllare
		if (exp != null)
			return exp.codeGeneration();
		
		return "";
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return id;
	}
}