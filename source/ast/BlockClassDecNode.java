package ast;

import ast.types.ClassType;
import org.antlr.v4.runtime.ParserRuleContext;
import sun.awt.Symbol;
import utils.Environment;
;
import utils.SymbolTableEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

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
	
	public ArrayList<Node> getInheritedMethods(String superclassID, Environment env) {
		ArrayList<Node> methods = new ArrayList<>();
		
		SymbolTableEntry superclassEntry = env.getActiveDec("Class$" + superclassID);
		if (superclassEntry != null) {
			BlockClassDecNode superclass = (BlockClassDecNode)superclassEntry.getType();
			methods.addAll(getInheritedMethods(superclass.getSuperclassID(), env));
			methods.addAll(superclass.getMethods());
		}
		
		return methods;
	}
	
	public ArrayList<Node> getInheritedFields(String superclassID, Environment env) {
		ArrayList<Node> fields = new ArrayList<>();
		
		SymbolTableEntry superclassEntry = env.getActiveDec("Class$"+superclassID);
		if (superclassEntry != null) {
			BlockClassDecNode superclass = (BlockClassDecNode)superclassEntry.getType();
			fields.addAll(getInheritedFields(superclass.getSuperclassID(), env));
			fields.addAll(superclass.getFields());
		}
		
		return fields;
	}
	
	public Node typeCheck(){return null;}
	
	public String codeGeneration(){return null;}
	
	public HashSet<String> checkSemantics(Environment env) {
		
		//create result list
		HashSet<String> res = new HashSet<String>();
		HashSet<String> tmp = new HashSet<String>();
		
		// Executing first check on class definitions
		if (!env.getSecondCheck()) {
			HashMap<String, SymbolTableEntry> classDecHM = env.getSymTable().get(env.getNestingLevel());
			//env.setOffset(env.getOffset()-1); TODO: to be handled in code gen
			
			ClassType classType = new ClassType(id, null, fields, methods, ctx);
			
			SymbolTableEntry classEntry = new SymbolTableEntry(env.getNestingLevel(), env.getOffset(), classType);
			
			if (classDecHM.put(id, classEntry) != null)
				res.add("Class '" + id + "' already declared at line " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n");
			
		}
		// Executing second check on class definitions and everything inside
		else {
			// Handling superclass declaration
			// TODO: check null!
			ClassType classType = (ClassType)env.getClassEntry(id).getType();
			
			if (ext != null) {
				SymbolTableEntry superclassEntry = env.getActiveDec(ext);
				if (superclassEntry == null)
					res.add("Superclass '" + ext + "' not declared at line " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n");
				else {
					if (ext.equals(id))
						res.add("Class '" + id + "' cannot extends itself at line " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n");
					else {
						ClassType superType = (ClassType)superclassEntry.getType();
						classType.setSuperType(superType);
						
						ArrayList<Node> inheritedFields = superType.getFieldsList(true);
						for (Node f:inheritedFields) {
							for (Node curF: fields) {
								if (f.getID().equals(curF.getID()))
									res.add("Class field '" + curF.getID() + "' is already declared in one of its superclasses at line "
											+ ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n");
							}
						}
					}
				}
			}
			env.pushScope();
			
			HashMap<String, SymbolTableEntry> classContentHM = env.getSymTable().get(env.getNestingLevel());
			ArrayList<Node> parTypes = new ArrayList<>();
			int parOffset=1;
			
			for (Node f: fields) {
				VarNode field = (VarNode)f;
				res.addAll(field.checkSemantics(env));
			}
			
			for (Node dec : methods) {
				env.setOffset(env.getOffset()-2);
				tmp.addAll(dec.checkSemantics(env));
			}
			
			env.settingFunSecondCheck(true);
			
			//if(tmp.size()>0) {
			for (Node dec : methods) {
				env.setOffset(env.getOffset() - 2);
				res.addAll(dec.checkSemantics(env));
			}
			//}
			
			res.addAll(tmp);
			
			env.settingFunSecondCheck(false);
			
			//env.updateClassEntry(classType);
			
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

