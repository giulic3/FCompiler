package ast;

import org.antlr.v4.runtime.ParserRuleContext;
import utils.Environment;
import utils.SemanticError;
import utils.SymbolTableEntry;

import java.util.ArrayList;

/* method call */
public class ClassMethodNode implements Node {
	
	private Node obj;
	private Node id;
	
	private ArrayList<Node> args;
	private SymbolTableEntry entry = null;
	private int callNestingLevel;
	private boolean isExp;
	private ParserRuleContext ctx;

	public ClassMethodNode(Node obj, Node ID, ArrayList<Node> args, boolean isExp, ParserRuleContext ctx){
		this.ctx = ctx;
		this.obj = obj;
		this.id = ID;
		this.args = args;
		this.isExp = isExp;
	}
	
	public String toPrint(String s) {
		return s+"Class Method Node:\n" + s + "\t\tObject:\n" + this.obj.toPrint(s+"\t\t\t") + "\n" + s + "\t\tMethod:\n" + this.id.toPrint(s+"\t\t\t");
	}
	
	@Override
	public Node typeCheck() {
		return null;
	}
	
	public String codeGeneration() {
		// TODO: da implementare
		return null;
	}
	
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		
		/*
		 *  spudoratamente copiato dalla funexp ma in questo caso è più complesso.
		 *  Va cercata la definizione dell'oggetto, la classe con cui è definito
		 *  e va cercato il metodo all'interno della classe in esame
		 */
		
		ArrayList<SemanticError> res = new ArrayList<SemanticError>();
		
		SymbolTableEntry entry = env.getActiveDec(obj.getID());
		if (entry == null)
			res.add(new SemanticError("Object " + obj.getID() + " not declared\n"));
		else {
			SymbolTableEntry classEntry = env.getActiveDec("Class$"+entry.getType().getID());
			BlockClassDecNode classDef = (BlockClassDecNode) classEntry.getType();
			ArrayList<Node> methods = classDef.getMethods();
			
			int i = 0;
			FunDecNode foundMethod = null;
			while (foundMethod == null && i<methods.size()) {
				FunDecNode m = (FunDecNode)methods.get(i);
				if (m.getID().equals(id.getID())) foundMethod = m;
				i++;
			}
			
			if (foundMethod == null) {
				res.add(new SemanticError("Method " + id.getID() + " is not defined in class " + classDef.getID() + " at line "
						+ ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n"));
			}
			
			this.entry = entry;
			this.callNestingLevel = env.getNestingLevel();
			
			for (Node arg : args)
				res.addAll(arg.checkSemantics(env));
		}
		
		return res;
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return id.getID();
	}
}
