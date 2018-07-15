package ast;

import ast.types.ClassType;
import utils.Environment;
;
import utils.SymbolTableEntry;

import java.util.ArrayList;
import java.util.HashSet;

/* method call */
public class ClassMethodNode extends FunExpNode {
	
	private Node objectNode;
	private Node funNode;

	public ClassMethodNode(Node objectNode, FunExpNode funNode){
		super(funNode.getID(), funNode.args, funNode.isExp, funNode.ctx);
		this.objectNode = objectNode;
		this.funNode = funNode;
	}
	
	public String toPrint(String s) {
		return s+"Method Call Node:\n" + s + "\t\tObject:\n" + this.objectNode.toPrint(s+"\t\t\t") + "\n" + s
				+ "\t\tMethod:\n" + this.funNode.toPrint(s+"\t\t\t");
	}
	
	@Override
	public Node typeCheck() {
		return null;
	}
	
	public String codeGeneration() {
		// TODO: da implementare
		return null;
	}
	
	public HashSet<String> checkSemantics(Environment env) {
		
		/*
		 *  spudoratamente copiato dalla funexp ma in questo caso è più complesso.
		 *  Va cercata la definizione dell'oggetto, la classe con cui è definito
		 *  e va cercato il metodo all'interno della classe in esame
		 */
		
		HashSet<String> res = new HashSet<>();
		
		HashSet<String> objectErrors = objectNode.checkSemantics(env);
		if (objectErrors.size() > 0) {
			res.addAll(objectErrors);
			return res;
		}
		
		IdNode objIDNode = (IdNode) objectNode;
		SymbolTableEntry objEntry = objIDNode.getSTEntry();
		
		if (objEntry.getType() instanceof ClassType) {
			ClassType classDef = (ClassType)objEntry.getType();
			
			ArrayList<Node> methods = classDef.getMethodsList(true);
			
			int i = 0;
			FunDecNode foundMethod = null;
			while (foundMethod == null && i<methods.size()) {
				FunDecNode m = (FunDecNode)methods.get(i);
				if (m.getID().equals(funNode.getID())) foundMethod = m;
				i++;
			}
			
			if (foundMethod == null) {
				res.add("Method " + funNode.getID() + " is not defined in class " + classDef.getID() + " at line "
						+ ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n");
			}
			else
				this.entry = foundMethod.getSTEntry();
			
			this.callNestingLevel = env.getNestingLevel();
			
			for (Node arg : args)
				res.addAll(arg.checkSemantics(env));
		}
		else {
			res.add("Wrong usage of method on object at line " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n");
		}
		
		
		return res;
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return funNode.getID();
	}
}

