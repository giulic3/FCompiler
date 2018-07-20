package ast;

import ast.types.ClassType;
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
	private String classID=null;
	private ParserRuleContext ctx;
	
	public AssignmentNode(Node var, Node exp, boolean isClassField, ParserRuleContext ctx){
		if (isClassField) this.objFieldNode = var;
		else this.idVariableNode = var;
		this.exp = exp;
		this.ctx = ctx;
	}
	
	public void setClassID(String classID) {
		this.classID = classID;
	}
	
	public String toPrint(String s){
		return s + "Assignment Node:\n" + (objFieldNode != null ? objFieldNode.toPrint(s+"\t\t") + "\n" : idVariableNode.toPrint(s+"\t") + "\n" ) + exp.toPrint(s+"\t\t");
	}
	
	/**
	 *
	 * Controlla che gli elementi a sinistra e destra dell'uguale siano stati definiti.
	 *
	 * */
	public HashSet<String> checkSemantics(Environment env) {
		
		HashSet<String> res = new HashSet<String>();
		
		if (objFieldNode != null)
			res.addAll(objFieldNode.checkSemantics(env));
		else{
			res.addAll(idVariableNode.checkSemantics(env));
		}
		
		res.addAll(exp.checkSemantics(env));
		
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
		
		if(idVariableNode!=null) {
			IdNode var = (IdNode)idVariableNode;
			SymbolTableEntry entry = var.getSTEntry();
			
			if (!Helpers.subtypeOf(exp.typeCheck(), idVariableNode.typeCheck()) || !Helpers.subtypeOf(exp.typeCheck(), entry.getStaticType())) {
				throw new TypeCheckException("Assignment", ctx.start.getLine(), ctx.start.getCharPositionInLine());
			}
			else {
				if (idVariableNode.typeCheck() instanceof ClassType) {
					entry.setType(exp.typeCheck());
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
			
			return exp.codeGeneration() +
					"push " + entry.getOffset() + "\n" +     //metto offset sullo stack
					"lfp\n" +
					Helpers.getActivationRecordCode(nestingLevel, entry.getNestingLevel()) +     //risalgo la catena statica
					"add\n" +
					"sw\n";     //carico sullo stack il valore all'indirizzo ottenuto
		}
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return null;
	}
}
