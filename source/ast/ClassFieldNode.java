package ast;

import ast.types.ClassType;
import ast.types.FunType;
import org.antlr.v4.runtime.ParserRuleContext;
import utils.Environment;
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
	
	public Node copyInstance() {
		ParserRuleContext ctx = new ParserRuleContext();
		ctx.copyFrom(this.ctx);
		ClassFieldNode copy = new ClassFieldNode(this.obj.copyInstance(), this.id.copyInstance(), this.isExp, ctx);
		copy.callNestingLevel = this.callNestingLevel;
		copy.entry = SymbolTableEntry.copyInstance(this.entry);
		return copy;
	}
	
	public String toPrint(String s) {
		return  s + "Class Field Node:\n" +
				s + "\tObject: " + this.obj.toPrint("") + "\n" +
				s + "\tField: " + this.id.toPrint("");
	}
	
	/**
	 *
	 * Controlla che l'oggetto sia stato istanziato e successivamente che il campo sia presente nella classe tipo dell'oggetto.
	 *
	 * */
	public HashSet<String> checkSemantics(Environment env) {
		
		HashSet<String> res = new HashSet<>();
		
		HashSet<String> objectErrors = obj.checkSemantics(env);
		if (objectErrors.size() > 0) {
			res.addAll(objectErrors);
			return res;
		}
		
		IdNode objIdNode = (IdNode)obj;
		SymbolTableEntry objEntry = objIdNode.getSTEntry();
		
		if (objEntry.getType() instanceof ClassType) {
			ClassType classDef = (ClassType)objEntry.getType();
			
			ArrayList<Node> fields = classDef.getFieldsList(true);
			
			int i = fields.size()-1;
			VarNode foundField = null;
			while (foundField == null && i>=0) {
				VarNode f = (VarNode)fields.get(i);
				if (f.getID().equals(id.getID())) foundField = f;
				i--;
			}
			
			if (foundField == null)
				res.add("Field " + id.getID() + " is not defined in class " + classDef.getID() + " at line " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n");
			else {
				((IdNode)id).setSTEntry(foundField.getSTEntry());
			}
			this.entry = objEntry;
			this.callNestingLevel = env.getNestingLevel();
		}
		else
			res.add("Wrong usage of class field on object at line " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n");
		
		return res;
	}
	
	/**
	 *
	 * Ritorna il tipo del campo della classe, ai fini della verifica sui tipi è sufficiente questa informazione.
	 * 
	 * */
	public Node typeCheck() throws Exception {
		return id.typeCheck();
	}
	
	public String codeGeneration() {
		SymbolTableEntry fieldEntry = ((IdNode)id).getSTEntry();
		
		return  obj.codeGeneration() +
				"push " + fieldEntry.getOffset() + "\n" +
				"add\n" +
				"lw\n";
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return null;
	}
	
	public void updateFieldType(SymbolTableEntry newEntry, boolean fromFunc) {
		if (id != null) {
			Node copy = id.copyInstance();
			IdNode idNode = (IdNode)copy;
			SymbolTableEntry idEntry = idNode.getSTEntry();
			if (fromFunc) {
				FunType funType = (FunType)newEntry.getType();
				idEntry.setType(funType.getReturnType());
			}
			else
				idEntry.setType(newEntry.getType());
			idNode.setSTEntry(idEntry);
			this.id = copy;
		}
	}
}
