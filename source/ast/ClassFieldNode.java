package ast;

import ast.types.ClassType;
import com.sun.tools.corba.se.idl.toJavaPortable.Helper;
import org.antlr.v4.runtime.ParserRuleContext;
import utils.Environment;
import utils.Helpers;
import utils.SymbolTableEntry;
import java.util.ArrayList;
import java.util.HashSet;

public class ClassFieldNode implements Node {
	
	private Node obj;
	private Node id;
	
	private SymbolTableEntry entry = null;
	private int callNestingLevel;
	private boolean isExp;
	private ParserRuleContext ctx;
	
	/**
	 *
	 * Nodo per la gestione di assegnamenti a campi di una classe.
	 *
	 * */
	public ClassFieldNode(Node obj, Node ID, boolean isExp, ParserRuleContext ctx) {
		this.ctx = ctx;
		this.obj = obj;
		this.id = ID;
		this.isExp = isExp;
	}
	
	public String toPrint(String s) {
		return s+"Class Field Node:\n" + s + "\tObject: " + this.obj.toPrint("") + "\n" + s + "\tField: " + this.id.toPrint("");
	}
	
	/**
	 *
	 * Controlla che l'oggetto sia stato istanziato e successivamente che il campo sia presente nella classe tipo dell'oggetto.
	 *
	 * */
	public HashSet<String> checkSemantics(Environment env) {
		
		HashSet<String> res = new HashSet<String>();
		
		res.addAll(obj.checkSemantics(env));
		
		SymbolTableEntry entry = env.getActiveDec(obj.getID());
		if (entry == null)
			res.add("Object " + obj.getID() + " not declared at line " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n");
		else {
			SymbolTableEntry classEntry = env.getClassEntry(entry.getType().getID());
			if (classEntry != null) {
				ClassType classDef = (ClassType) classEntry.getType();
				ArrayList<Node> fields = classDef.getFieldsList(true);
				
				int i = 0;
				VarNode foundField = null;
				while (foundField == null && i < fields.size()) {
					VarNode m = (VarNode) fields.get(i);
					if (m.getID().equals(id.getID())) foundField = m;
					i++;
				}
				
				if (foundField == null) {
					res.add("Class field " + id.getID() + " is not defined in class " + classDef.getID() + " at line " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n");
				}
				
				((IdNode)id).setSTEntry(foundField.getSTEntry());
				this.entry = entry;
				this.callNestingLevel = env.getNestingLevel();
			}
			else
				res.add("Type of object " + obj.getID() + " is not defined at line " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n");
		}
		
		return res;
	}
	
	/**
	 *
	 * Ritorna il tipo del campo della classe, ai fini della verifica sui tipi è sufficiente questa informazione.
	 * */
	public Node typeCheck() throws Exception {
		return id.typeCheck();
	}
	
	public String codeGeneration() {
		SymbolTableEntry fieldEntry = ((IdNode)id).getSTEntry();
		
		// gli offset dei campi partono da 0
		return  obj.codeGeneration() +
				"push " + fieldEntry.getOffset() + "\n" +
				"add\n" +
				"lw\n";
				//"push 0\n" +//"lhp\n" +
				//Helpers.getActivationRecordCode(callNestingLevel, fieldEntry.getNestingLevel()) +
				//"add\n" +
				//"push " + fieldEntry.getOffset() + "\n" +
				//"add\n" +//"sub\n" +
				//"lw\n";
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return null;
	}
}
