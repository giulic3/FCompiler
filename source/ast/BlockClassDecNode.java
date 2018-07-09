package ast;

import org.antlr.v4.runtime.ParserRuleContext;
import utils.Environment;
import utils.SemanticError;
import utils.SymbolTableEntry;

import java.util.ArrayList;
import java.util.HashMap;

public class BlockClassDecNode implements Node {
	private String id;
	private String ext;
	private ArrayList<Node> fields;
	private ArrayList<Node> methods;
	private ParserRuleContext ctx;
	
	
	/* se non ci sono parametri par sarà null, se non è estesa ext sarà null */
	
	public BlockClassDecNode(String id, String ext, ArrayList<Node> fields, ArrayList<Node> methods, ParserRuleContext ctx) {
		this.id=id;
		this.ext=ext;
		this.fields=fields;
		this.methods=methods;
		this.ctx=ctx;
	}
	
	public String toPrint(String s){
		String msg = "\n"+s+"Class Dec Node: " + this.id;
		if(ext!=null){
			msg+=" extends " + ext;
		}
		msg+=" {";
		if(fields!=null) {
			msg+="\n"+s+"\tParams:";
			for (Node b : fields) {
				msg += "\n " + s + b.toPrint("\t\t");
			}
		}
		msg += "\n"+s+"\tMethods:";
		for (Node b : methods) {
			msg += "\n " + s + b.toPrint("\t\t");
		}
		msg+="\n"+s+"}";
		return msg;
	}
	
	public ArrayList<Node> getMethods() {
		return methods;
	}
	
	public ArrayList<Node> getFields() {
		return fields;
	}
	
	private ArrayList<Node> getInheritedMethods(String superclassID, Environment env) {
		ArrayList<Node> methods = new ArrayList<>();
		
		SymbolTableEntry superclassEntry = env.getActiveDec("Class$" + superclassID);
		if (superclassEntry != null) {
			BlockClassDecNode superclass = (BlockClassDecNode)superclassEntry.getType();
			methods.addAll(getInheritedMethods(superclass.getSuperclassID(), env));
			methods.addAll(superclass.getMethods());
		}
		
		return methods;
	}
	
	public Node typeCheck(){return null;}
	
	public String codeGeneration(){return null;}
	
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		
		//create result list
		ArrayList<SemanticError> res = new ArrayList<>();
		
		// Executing first check on class definitions
		if (!env.getSecondCheck()) {
			HashMap<String, SymbolTableEntry> classDecHM = env.getSymTable().get(env.getNestingLevel());
			//env.setOffset(env.getOffset()-1); TODO: to be handled in code gen
			SymbolTableEntry classEntry = new SymbolTableEntry(env.getNestingLevel(), env.getOffset(), this);
			
			if (classDecHM.put("Class$" + id, classEntry) != null)
				res.add(new SemanticError("Class '" + id + "' already declared at line " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n"));
		}
		// Executing second check on class definitions and everything inside
		else {
			// Handling superclass declaration
			if (ext != null) {
				// Superclass not declared
				if (env.getActiveDec("Class$" + ext) == null)
					res.add(new SemanticError("Superclass '" + ext + "' not declared at line " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n"));
				
				// Climb back superclass declarations to collect list of inherited methods and fields
				// TODO: va fatto qua? oppure in ClassMethodNode? per i campi dovrebbe essere un discorso analogo
				// TODO: manca la gestion degli errori in caso di dichiarazioni multiple (che non sia overriding, nel caso dei metodi)
				ArrayList<Node> inheritedMethods = getInheritedMethods(ext, env);
				System.out.println("\nInherited method for class " + id);
				for (Node m:inheritedMethods) {
					FunDecNode method = (FunDecNode)m;
					System.out.println(method.toPrint(""));
				}
				System.out.println();
			}
			
			env.pushScope();
			
			HashMap<String, SymbolTableEntry> classContentHM = env.getSymTable().get(env.getNestingLevel());
			ArrayList<Node> parTypes = new ArrayList<>();
			int parOffset=1;
			
			for (Node f : fields) {
				VarNode field = (VarNode)f;
				parTypes.add(field.getType());
				SymbolTableEntry fieldEntry = new SymbolTableEntry(env.getNestingLevel(), parOffset++, field.getType());
				
				if (classContentHM.put("Class$" + id + "$" + field.getId(), fieldEntry) != null)
					res.add(new SemanticError("Class field " + field.getId() + " already declared at line: " + field.getCtx().start.getLine() + ":" + field.getCtx().start.getCharPositionInLine()+"\n"));
			}
			
			for (Node dec : methods) {
				env.setOffset(env.getOffset()-2);
				res.addAll(dec.checkSemantics(env));
			}
			
			env.popScope();
		}
		
		return res;
	}
	
	public String getID() {
		return id;
	}
	
	public String getSuperclassID() {
		return ext;
	}
}

