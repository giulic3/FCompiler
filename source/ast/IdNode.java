package ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import ast.types.ClassType;
import org.antlr.v4.runtime.ParserRuleContext;
import utils.Environment;
import utils.Helpers;
import utils.SymbolTableEntry;

/* corresponds to var */
public class IdNode implements Node {

	private String id;
	private String classID;
	private SymbolTableEntry entry;
	private int nestinglevel;
	private ParserRuleContext ctx;

	public IdNode (String i, ParserRuleContext ctx) {
		this.ctx=ctx;
		id = i;
		classID = null;
	}
	
	// TODO: prova
	public Node copyInstance() {
		ParserRuleContext ctx = new ParserRuleContext();
		ctx.copyFrom(this.ctx);
		IdNode copy = new IdNode(this.id, ctx);
		copy.classID = this.classID;
		copy.entry = SymbolTableEntry.copyInstance(this.entry);
		copy.nestinglevel = this.nestinglevel;
		return copy;
	}
	
	public String getID() {
		return id;
	}

	public String toPrint(String s) {

		return s + "ID Node: " + id + ", cur nestinglevel: " + nestinglevel + (entry != null ? entry.toPrint(", ") : "");
	}
	
	public SymbolTableEntry getSTEntry() {
		return this.entry;
	}
	
	public void setSTEntry(SymbolTableEntry entry) {
		this.entry=entry;
	}

	@Override
	public HashSet<String> checkSemantics(Environment env) {

		if (env.getDefiningClass() != null) classID = env.getDefiningClass();
		
		//discriminare se l'id node non appartiene ad una classe controllo classname != null
		
		//create result list
		HashSet<String> res = new HashSet<String>();
		
		nestinglevel = env.getNestingLevel();
		
		SymbolTableEntry fieldEntry = env.getActiveDec(id);
		if (fieldEntry == null) {
			HashMap<String, SymbolTableEntry> classContentHM = env.getSymTable().get(1);
			String identifier = classContentHM.keySet().iterator().next();
			
			fieldEntry = classContentHM.get(identifier);
			String classID = fieldEntry.getClassName();
			Node found = null;
			
			if (classID!=null) {
				SymbolTableEntry classEntry = env.getClassEntry(classID);
				ClassType classType = (ClassType)classEntry.getType();
				ArrayList<Node> fields = classType.getFieldsList(true);
				
				for (Node f: fields)
					if (f.getID().equals(id)) found = f;
			}
			
			// vanno salvate le informazioni della entry nell'oggetto
			
			if (found == null)
				res.add("Variable " + id + " not declared at line: " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n");
			else {
				this.entry = ((VarNode)found).getSTEntry();
			}
		}
		else
			this.entry = fieldEntry;
		
		return res;
	}

	public Node typeCheck () {
		return entry.getType();
	}

	public String codeGeneration() {
		return  "push " + entry.getOffset() + "\n" +     //metto offset sullo stack
				"lfp\n" +
				Helpers.getActivationRecordCode(nestinglevel, entry.getNestingLevel()) +     //risalgo la catena statica
				"add\n" +
				"lw\n";     //carico sullo stack il valore all'indirizzo ottenuto
	}
}