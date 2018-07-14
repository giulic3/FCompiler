package ast;

import ast.types.ClassType;
import org.antlr.v4.runtime.ParserRuleContext;
import utils.Environment;
;
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
	
	public ClassFieldNode(Node obj, Node ID, boolean isExp, ParserRuleContext ctx) {
		this.ctx = ctx;
		this.obj = obj;
		this.id = ID;
		this.isExp = isExp;
	}
	
	public String toPrint(String s) {
		return s+"Class Field Node:\n" + s + "\t\tObject: " + this.obj.toPrint("") + "\n" + s + "\t\tMethod: " + this.id.toPrint("");
	}
	
	public Node typeCheck() {
		return null;
	}
	
	public String codeGeneration() {
		return null;
	}
	
	public HashSet<String> checkSemantics(Environment env) {
		
		HashSet<String> res = new HashSet<String>();
		
		SymbolTableEntry entry = env.getActiveDec(obj.getID());
		if (entry == null)
			res.add("Object " + obj.getID() + " not declared at line " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n");
		else {
			SymbolTableEntry classEntry = env.getClassEntry(entry.getType().getID());
			ClassType classDef = (ClassType) classEntry.getType();
			ArrayList<Node> fields = classDef.getFieldsList(true);
			
			int i = 0;
			VarNode foundField = null;
			while (foundField == null && i<fields.size()) {
				VarNode m = (VarNode)fields.get(i);
				if (m.getID().equals(id.getID())) foundField = m;
				i++;
			}
			
			if (foundField == null) {
				res.add("Class field " + id.getID() + " is not defined in class " + classDef.getID() + " at line " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n");
			}
			
			this.entry = entry;
			this.callNestingLevel = env.getNestingLevel();
		}
		
		return res;
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return null;
	}
}
