package ast;

import org.antlr.v4.runtime.ParserRuleContext;
import utils.Environment;
import utils.SemanticError;
import utils.SymbolTableEntry;

import java.util.ArrayList;

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
	
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		
		ArrayList<SemanticError> res = new ArrayList<SemanticError>();
		
		SymbolTableEntry entry = env.getActiveDec(obj.getID());
		if (entry == null)
			res.add(new SemanticError("Object " + obj.getID() + " not declared\n"));
		else {
			SymbolTableEntry classEntry = env.getActiveDec("Class$"+entry.getType().getID());
			BlockClassDecNode classDef = (BlockClassDecNode) classEntry.getType();
			ArrayList<Node> fields = classDef.getFields();
			
			int i = 0;
			VarNode foundField = null;
			while (foundField == null && i<fields.size()) {
				VarNode m = (VarNode)fields.get(i);
				if (m.getID().equals(id.getID())) foundField = m;
				i++;
			}
			
			if (foundField == null) {
				res.add(new SemanticError("Class field " + id.getID() + " is not defined in class " + classDef.getID() + " at line " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n"));
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