package ast;

import ast.types.VoidType;
import utils.Environment;
import utils.Helpers;
import utils.SymbolTableEntry;

import java.util.HashSet;

public class AssignmentNode implements Node {
	
	private Node idVariableNode = null;
	private Node exp;
	private Node objFieldNode = null;
	private int nestingLevel = 0;
	
	public AssignmentNode(Node var, Node exp, boolean isClassField){
		if (isClassField) this.objFieldNode = var;
		else this.idVariableNode = var;
		
		this.exp = exp;
	}
	
	public String toPrint(String s){
		return s + "Assignment Node:\n" + (objFieldNode != null ? objFieldNode.toPrint(s+"\t\t") + "\n" : idVariableNode.toPrint(s+"\t") + "\n" ) + exp.toPrint(s+"\t\t");
	}
	
	public HashSet<String> checkSemantics(Environment env) {
		
		HashSet<String> res = new HashSet<String>();
		
		if (objFieldNode != null)
			res.addAll(objFieldNode.checkSemantics(env));
		else
			res.addAll(idVariableNode.checkSemantics(env));
		
		res.addAll(exp.checkSemantics(env));
		
		nestingLevel = env.getNestingLevel();
		
		return res;
	}
	
	public Node typeCheck() throws Exception{
		
		if(idVariableNode!=null) {
			if (!Helpers.subtypeOf(exp.typeCheck(), idVariableNode.typeCheck())) {
				throw new Exception("Assignment Node typeCheck exception");
			}
		}
		else{
			if (!Helpers.subtypeOf(exp.typeCheck(), objFieldNode.typeCheck())) {
				throw new Exception("Assignment Node Class field typeCheck exception");
			}
		}
		
		return new VoidType();
	}
	
	public String codeGeneration() {
		String getAR = "";
		
		// TODO: class field assignment MUST be done
		
		IdNode var = (IdNode)idVariableNode;
		SymbolTableEntry entry = var.getSTEntry();
		
		for (int i = 0; i < nestingLevel - entry.getNestingLevel(); i++) getAR += "lw\n";
		
		return  exp.codeGeneration() +
				"push " + entry.getOffset() + "\n" +     //metto offset sullo stack
				"lfp\n" +
				getAR +     //risalgo la catena statica
				"add\n" +
				"sw\n";     //carico sullo stack il valore all'indirizzo ottenuto
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return null;
	}
}
