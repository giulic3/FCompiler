package ast;

import java.util.ArrayList;
import java.util.HashMap;

import ast.types.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import utils.Environment;
import utils.SemanticError;
import utils.SymbolTableEntry;

public class VarNode implements Node {
	private ParserRuleContext ctx;
	private String id;
	private Node type;
	private Node exp;
	
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
	
	public ParserRuleContext getCtx(){
		return ctx;
	}
	
	public String toPrint(String s){
		if (exp != null)
			return s + "Var Node: " + id + " (type: " + type.toPrint("") + ")\n" + exp.toPrint(s+"\t\t");
		else
			return s + "Var Node: " + id + " (type: " + type.toPrint("") + ")";
	}
	
	public Node typeCheck(){return null;}
	
	public Node getType(){
		return type;
	}
	
	public String getId(){
		return id;
	}
	
	public String codeGeneration(){return null;}
	
	public ArrayList<SemanticError> checkSemantics(Environment env){
		ArrayList<SemanticError> res = new ArrayList<SemanticError>();
		
		//env.offset = -2;
		HashMap<String, SymbolTableEntry> hm = env.getSymTable().get(env.getNestingLevel());
		env.setOffset(env.getOffset()-1);
		SymbolTableEntry entry = new SymbolTableEntry(env.getNestingLevel(),env.getOffset(),type); //separo introducendo "entry"
		
		if ( hm.put(id,entry) != null )
			res.add(new SemanticError("Var or Par id "+id+" alredy declared at line: "+ctx.start.getLine()+":"+ctx.start.getCharPositionInLine()+"\n"));
		
		if(exp!=null) res.addAll(exp.checkSemantics(env));
		
		return res;
	}
	
}