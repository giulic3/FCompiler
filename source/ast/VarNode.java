package ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import ast.types.ClassType;
import org.antlr.v4.runtime.ParserRuleContext;
import utils.Environment;
;
import utils.SymbolTableEntry;

public class VarNode implements Node {
	private ParserRuleContext ctx;
	private String id;
	private Node type;
	private Node exp;
	private String classID;
	
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
	
	public Node typeCheck(){return null;}
	
	public Node getType(){
		return type;
	}
	
	public String getId(){
		return id;
	}
	
	public String codeGeneration(){return null;}
	
	public HashSet<String> checkSemantics(Environment env){
		HashSet<String> res = new HashSet<String>();
		
		//env.offset = -2;
		HashMap<String, SymbolTableEntry> hm = env.getSymTable().get(env.getNestingLevel());
		env.setOffset(env.getOffset()-1);
		SymbolTableEntry entry = new SymbolTableEntry(env.getNestingLevel(),env.getOffset(),type); //separo introducendo "entry"
		
		String ID = (this.classID != null) ? "Class$" + this.classID + "$" + id : id;
		
		/*String classID = "Class$";
		if (type instanceof ClassType) {
			ClassType classType = (ClassType)type;
			classID += classType.getClassID() + "$" + id;
			entry.setClassName(classType.getClassID());
			if ( hm.put(classID,entry) != null )
				res.add("Class field "+id+" already declared at line: "+ctx.start.getLine()+":"+ctx.start.getCharPositionInLine()+"\n");
		}
		else {*/
			if ( hm.put(ID,entry) != null )
				res.add("Var or Par id "+id+" already declared at line: "+ctx.start.getLine()+":"+ctx.start.getCharPositionInLine()+"\n");
		//}
		
		res.addAll(type.checkSemantics(env));
		if(exp!=null) res.addAll(exp.checkSemantics(env));
		
		return res;
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return id;
	}
}