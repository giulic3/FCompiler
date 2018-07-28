package ast;

import ast.types.ClassType;
import ast.types.FunType;
import utils.Environment;
import utils.Helpers;
import utils.SymbolTableEntry;
import java.util.ArrayList;
import java.util.HashSet;

/* method call */
public class ClassMethodNode extends FunExpNode {
	
	private Node objectNode;
	private Node funNode;
	
	/**
	 *
	 * Nodo per la gestione dell'invocazione di un metodo della classe tipo di objectNode.
	 *
	 * */
	public ClassMethodNode(Node objectNode, FunExpNode funNode){
		super(funNode.getID(), funNode.args, funNode.isExp, funNode.ctx);
		this.objectNode = objectNode;
		this.funNode = funNode;
	}
	
	public String toPrint(String s) {
		return s+"Method Call Node:\n" + s + "\t\tObject:\n" + this.objectNode.toPrint(s+"\t\t\t") + "\n" + s
				+ "\t\tMethod:\n" + this.funNode.toPrint(s+"\t\t\t");
	}
	
	/**
	 *
	 * Controlla che l'oggetto sia definito, che il metodo sia definito ed invocato con il giusto numero di parametri
	 *
	 * */
	public HashSet<String> checkSemantics(Environment env) {
		
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
			
			ArrayList<Node> methods = classDef.getMethodsList(true, false);
			
			/*
				Il ciclo deve partire dalla fine perchè, se il metodo che stiamo cercando ha fatto overriding,
				ci interessa trovare quello della sottoclasse e non quello della superclasse (altrimenti avrebbe
				restituito il primo che trovava). Ciò serve per ottenere la corretta symbol table entry per
				prendere il giusto offset da usare nella dispatch table.
			 */
			int i = methods.size()-1;
			FunDecNode foundMethod = null;
			while (foundMethod == null && i>=0) {
				FunDecNode m = (FunDecNode)methods.get(i);
				if (m.getID().equals(funNode.getID())) foundMethod = m;
				i--;
			}
			
			if (foundMethod == null) {
				res.add("Method " + funNode.getID() + " is not defined in class " + classDef.getID() + " at line "
						+ ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n");
			}
			else {
				this.entry = foundMethod.getSTEntry();
				
				if (((FunType) entry.getType()).getParList().size() != args.size())
					res.add("Method " + this.id + " call with wrong number of parameters is not allowed at line "
							+ ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n");
				this.callNestingLevel = env.getNestingLevel();
			}
			for (Node arg : args)
				res.addAll(arg.checkSemantics(env));
		}
		else {
			res.add("Wrong usage of method on object at line " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n");
		}
		
		
		return res;
	}
	
	/**
	 *
	 * La funzione <strong>typeCheck</strong> viene ereditata dalla classe FunExpNode.
	 *
	 * */
	
	/**
	 *
	 * La funzione <strong>codeGeneration</strong>
	 *
	 * */
	public String codeGeneration() {
		String parAssembly = "";
		for (int i = args.size()-1; i >= 0; i--)
			parAssembly += args.get(i).codeGeneration();
		
		IdNode obj = (IdNode)objectNode;
		SymbolTableEntry objectEntry = obj.getSTEntry();
		
		return  "lfp\n" +
				parAssembly +
				//objectNode.codeGeneration() +
				"push " + objectEntry.getOffset() + "\n" +
				"lfp\n" +
				Helpers.getActivationRecordCode(callNestingLevel, objectEntry.getNestingLevel()) +
				"add\n" +
				"lw\n" +
				"cp\n" +
				"lw\n" +
				"push " + entry.getOffset() + "\n" +
				"add\n" +
				"jsmeth\n" +
				"js\n";
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return funNode.getID();
	}
	
	
	public Node typeCheck() throws Exception {
		return super.typeCheck();
	}
}


