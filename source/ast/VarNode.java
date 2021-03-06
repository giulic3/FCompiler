package ast;

import java.util.HashMap;
import java.util.HashSet;

import ast.types.ClassType;
import ast.types.FunType;
import ast.types.VoidType;
import org.antlr.v4.runtime.ParserRuleContext;
import utils.Environment;
import utils.Helpers;
import utils.SymbolTableEntry;
import utils.TypeCheckException;

public class VarNode implements Node {
	
	private ParserRuleContext ctx;
	private String id;
	private Node type;
	private Node exp;
	private String classID = null;
	private SymbolTableEntry entry;
	private boolean isParam = false;
	
	// Constructor for vardec
	public VarNode(String i, Node t, ParserRuleContext ctx) {
		this.ctx=ctx;
		id = i;
		type = t;
		exp = null;
	}
	
	// Constructor for varasm
	public VarNode(String i, Node t, ParserRuleContext ctx , Node v) {
		this.ctx=ctx;
		id = i;
		type = t;
		exp = v;
	}
	
	public Node copyInstance() {
		Node expCopy = (this.exp != null) ? this.exp.copyInstance() : null;
		ParserRuleContext ctx = new ParserRuleContext();
		ctx.copyFrom(this.ctx);
		VarNode copy = new VarNode(this.id, this.type.copyInstance(), ctx, expCopy);
		copy.classID = this.classID;
		copy.entry = SymbolTableEntry.copyInstance(this.entry);
		copy.isParam = this.isParam;
		return copy;
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
	

	public Node getType(){
		return type;
	}
	
	public void setType(Node type) {
		this.type = type;
	}
	
	public Node getExp() {
		return this.exp;
	}
	
	public void setIsParam(boolean b) {
		isParam = b;
	}
	
	public String toPrint(String s){
		if (exp != null)
			return s + "Var Node: " + id + " (type: " + type.toPrint("") + ")\n" + exp.toPrint(s+"\t");
		else
			return s + "Var Node: " + id + " (type: " + type.toPrint("") + ")";
	}
	
	public HashSet<String> checkSemantics(Environment env){
		HashSet<String> res = new HashSet<>();
		
		if (exp!=null) res.addAll(exp.checkSemantics(env));
		
		res.addAll(type.checkSemantics(env));
		
		// necessary to set the correct class type as defined in the symbol table
		if (type instanceof ClassType) {
			SymbolTableEntry classDef = env.getClassEntry(type.getID());
			if (classDef != null) type = classDef.getType();
		}
		
		HashMap<String, SymbolTableEntry> hm = env.getSymTable().get(env.getNestingLevel());
		int FieldOrVarOffset = (classID != null || isParam) ? env.increaseOffset() : env.decreaseOffset();
		
		SymbolTableEntry entry = new SymbolTableEntry(env.getNestingLevel(), FieldOrVarOffset, type);
		entry.setStaticType(type);
		
		String ID = id;
		if (this.classID != null){
			entry.setClassName(classID);
			ID = "Class$" + this.classID + "$" + ID;
		}
		
		if (exp instanceof NewExpNode) {
			NewExpNode newNode = (NewExpNode) exp;
			SymbolTableEntry newEntry = newNode.getSTEntry();
			if (newEntry != null) {
				entry.setType(newEntry.getType());
				setType(newEntry.getType());
			}
		}
		
		if (exp instanceof IdNode) {
			IdNode expNode = (IdNode)exp;
			SymbolTableEntry expEntry = expNode.getSTEntry();
			if (expEntry != null && expEntry.getType() instanceof ClassType) {
				entry.setType(expEntry.getType());
				setType(expEntry.getType());
			}
		}
		
		if (exp instanceof FunExpNode) {
			FunExpNode funNode = (FunExpNode)exp;
			SymbolTableEntry funEntry = funNode.getSTEntry();
			if (funEntry != null && funEntry.getType() instanceof FunType && ((FunType)funEntry.getType()).getReturnType() instanceof ClassType) {
				FunType funType = (FunType)funEntry.getType();
				entry.setType(funType.getReturnType());
				setType(funType.getReturnType());
			}
		}
		
		if (hm.put(ID, entry) != null)
			res.add("Var or Par id " + id + " already declared at line " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n");
		else
			this.entry = entry;
		
		return res;
	}
	
	public Node typeCheck() throws Exception {
		if (exp != null) {
			Node expTypeCheck = exp.typeCheck();
			
			if (exp instanceof ClassType && !Helpers.subtypeOf(expTypeCheck, this.entry.getStaticType()))
				throw new TypeCheckException("Object Initialization", ctx.start.getLine(), ctx.start.getCharPositionInLine());
			
			if (!Helpers.subtypeOf(expTypeCheck, type))
				throw new TypeCheckException("Var", ctx.start.getLine(), ctx.start.getCharPositionInLine());
		}
		
		return new VoidType();
	}
	
	public String codeGeneration() {
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