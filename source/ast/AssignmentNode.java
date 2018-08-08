package ast;

import ast.types.ClassType;
import ast.types.FunType;
import ast.types.NullType;
import ast.types.VoidType;
import org.antlr.v4.runtime.ParserRuleContext;
import utils.Environment;
import utils.Helpers;
import utils.SymbolTableEntry;
import utils.TypeCheckException;

import java.util.HashSet;

public class AssignmentNode implements Node {
	
	/**
	 *
	 * Nodo per la gestione dell'<strong>Assegnamento</strong> ad una variabile.
	 *
	 * */
	
	private Node idVariableNode = null;
	private Node exp;
	private Node objFieldNode = null;
	private int nestingLevel = 0;
	private String classID = null;
	private ParserRuleContext ctx;
	
	public AssignmentNode(Node var, Node exp, boolean isClassField, ParserRuleContext ctx){
		if (isClassField) this.objFieldNode = var;
		else this.idVariableNode = var;
		this.exp = exp;
		this.ctx = ctx;
	}
	
	public Node copyInstance() {
		ParserRuleContext ctx = new ParserRuleContext();
		ctx.copyFrom(this.ctx);
		boolean isClassField = this.objFieldNode != null;
		Node var = (isClassField) ? this.objFieldNode.copyInstance() : this.idVariableNode.copyInstance();
		AssignmentNode copy = new AssignmentNode(var, this.exp.copyInstance(), isClassField, ctx);
		copy.nestingLevel = this.nestingLevel;
		copy.classID = this.classID;
		return copy;
	}
	
	public void setClassID(String classID) {
		this.classID = classID;
	}
	
	public String toPrint(String s){
		return  s + "Assignment Node:\n" +
				(objFieldNode != null ? objFieldNode.toPrint(s + "\t\t") + "\n" : idVariableNode.toPrint(s+"\t") + "\n") +
				exp.toPrint(s + "\t\t");
	}
	
	/**
	 *
	 * Controlla che gli elementi a sinistra e destra dell'uguale siano stati definiti.
	 *
	 * */
	public HashSet<String> checkSemantics(Environment env) {
		
		HashSet<String> res = new HashSet<>();
		
		res.addAll(exp.checkSemantics(env));
		
		if (objFieldNode != null)
			res.addAll(objFieldNode.checkSemantics(env));
		else
			res.addAll(idVariableNode.checkSemantics(env));
		
		// Handles object type update after initialization
		if (exp instanceof NewExpNode) {
			NewExpNode newNode = (NewExpNode) exp;
			SymbolTableEntry newEntry = newNode.getSTEntry();
			if (newEntry != null) {
				if (idVariableNode != null) {
					IdNode curNode = (IdNode) idVariableNode;
					SymbolTableEntry varEntry = curNode.getSTEntry();
					varEntry.setType(newEntry.getType());
					curNode.setSTEntry(varEntry);
				}
				else {
					ClassFieldNode curNode = (ClassFieldNode)objFieldNode;
					curNode.updateFieldType(newEntry, false);
				}
			}
		}
		
		// Handles object type update after assignement
		if (exp instanceof IdNode) {
			IdNode expNode = (IdNode)exp;
			SymbolTableEntry expEntry = expNode.getSTEntry();
			if (expEntry != null && expEntry.getType() instanceof ClassType) {
				if (idVariableNode != null) {
					IdNode curNode = (IdNode) idVariableNode;
					SymbolTableEntry varEntry = curNode.getSTEntry();
					varEntry.setType(expEntry.getType());
					curNode.setSTEntry(varEntry);
				}
				else {
					ClassFieldNode curNode = (ClassFieldNode)objFieldNode;
					curNode.updateFieldType(expEntry, false);
				}
			}
		}
		
		// Handles object type update after assignment from fun call
		if (exp instanceof FunExpNode) {
			FunExpNode funNode = (FunExpNode)exp;
			SymbolTableEntry funEntry = funNode.getSTEntry();
			if (funEntry != null && funEntry.getType() instanceof FunType && ((FunType)funEntry.getType()).getReturnType() instanceof ClassType) {
				if (idVariableNode != null) {
					IdNode curNode = (IdNode) idVariableNode;
					SymbolTableEntry varEntry = curNode.getSTEntry();
					FunType funType = (FunType) funEntry.getType();
					varEntry.setType(funType.getReturnType());
					curNode.setSTEntry(varEntry);
				}
				else {
					ClassFieldNode curNode = (ClassFieldNode)objFieldNode;
					curNode.updateFieldType(funEntry, true);
				}
			}
		}
		
		nestingLevel = env.getNestingLevel();
		
		return res;
	}
	
	/**
	 *
	 * Controlla che gli elementi a sinistra e destra dell'uguale abbiano tipi compatibili,
	 * gestisce sia il caso in cui si stia assegnando ad una variabile sia il caso in cui si stia
	 * assegnando al campo di una classe (con la notazione oggetto.field=espressione).
	 *
	 * */
	public Node typeCheck() throws Exception{
		
		if (idVariableNode!=null) {
			IdNode var = (IdNode)idVariableNode;
			SymbolTableEntry entry = var.getSTEntry();
			
			Node idVarNodeTC = idVariableNode.typeCheck();
			Node expTC = exp.typeCheck();
			
			// to allow reinstantiation of a null object
			if (idVarNodeTC instanceof NullType && Helpers.subtypeOf(expTC, entry.getStaticType())) {
				entry.setType(expTC);
				var.setSTEntry(entry);
				return new VoidType();
			}
			else if (!Helpers.subtypeOf(expTC, idVarNodeTC) || !Helpers.subtypeOf(expTC, entry.getStaticType())) {
				throw new TypeCheckException("Assignment", ctx.start.getLine(), ctx.start.getCharPositionInLine());
			}
			else {
				if (idVarNodeTC instanceof ClassType) {
					entry.setType(expTC);
					var.setSTEntry(entry);
				}
			}
		}
		else{
			if (!Helpers.subtypeOf(exp.typeCheck(), objFieldNode.typeCheck())) {
				throw new TypeCheckException("Assignment", ctx.start.getLine(), ctx.start.getCharPositionInLine());
			}
		}
		
		return new VoidType();
	}
	
	public String codeGeneration() {
		if (objFieldNode != null) {
			ClassFieldNode fieldNode = (ClassFieldNode)objFieldNode;
			String fieldCode = fieldNode.codeGeneration();
			
			// code gen for field is similar to the one used for accessing it
			// the difference consists in replacing last LW with SW
			int lastLWPos = fieldCode.lastIndexOf("lw\n");
			return  exp.codeGeneration() +
					fieldCode.substring(0, lastLWPos) +
					"sw\n";
		}
		else {
			IdNode var = (IdNode) idVariableNode;
			SymbolTableEntry entry = var.getSTEntry();
			
			return  exp.codeGeneration() +
					"push " + entry.getOffset() + "\n" +    // pushes variable offset on the stack
					"lfp\n" +
					Helpers.getActivationRecordCode(nestingLevel, entry.getNestingLevel()) +    // climbs static chain (eventually)
					"add\n" +
					"sw\n";
		}
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return null;
	}
}
