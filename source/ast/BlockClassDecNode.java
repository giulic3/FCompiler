package ast;

import ast.types.ClassType;
import org.antlr.v4.runtime.ParserRuleContext;
import utils.Environment;
import utils.Helpers;
import utils.SymbolTableEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class BlockClassDecNode implements Node {
	
	private String id;
	private String superClassID;
	private ArrayList<Node> fields;
	private ArrayList<Node> methods;
	private ParserRuleContext ctx;
	private ClassType type;
	
	/**
	 *
	 * Nodo per la gestione della <strong>Dichiarazione</strong> di una classe.
	 *
	 * */
	public BlockClassDecNode(String id, String superClassID, ArrayList<Node> fields, ArrayList<Node> methods, ParserRuleContext ctx) {
		this.id=id;
		this.superClassID = superClassID;
		this.fields=fields;
		this.methods=methods;
		this.ctx=ctx;
	}
	
	public String toPrint(String s){
		String msg = "\n"+s+"Class Dec Node: " + this.id;
		if(superClassID !=null){
			msg+=" extends " + superClassID;
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
	
	/**
	 *
	 * Utility per il recupero dei metodi della classe.
	 *
	 * */
	public ArrayList<Node> getMethods() {
		return methods;
	}
	
	/**
	 *
	 * Utility per il recupero dei campi della classe.
	 *
	 * */
	public ArrayList<Node> getFields() {
		return fields;
	}
	
	/**
	 *
	 * La funzione <strong>checkSemantics</strong> per la dichiarazione di una classe si occupa di
	 * controllare che:
	 * <ul>
	 *     <li>La classe non sia già stata dichiarata</li>
	 *     <li>Un'eventuale superclasse esista, quindi che la superclasse sia dichiarata</li>
	 * 	   <li>Che la classe non estenda se stessa</li>
	 * 	   <li>Non ci sia overriding di campi (come da specifica)</li>
	 * </ul>
	 *
	 * */
	public HashSet<String> checkSemantics(Environment env) {
		
		//create result list
		HashSet<String> res = new HashSet<String>();
		HashSet<String> tmp = new HashSet<String>();
		
		// Executing first check on class definitions
		//if (!env.getSecondCheck()) {
		HashMap<String, SymbolTableEntry> classDecHM = env.getSymTable().get(env.getNestingLevel());
		//env.setOffset(env.getOffset()-1); TODO: to be handled in code gen
		
		ClassType classType = new ClassType(id, null, fields, methods, ctx);
		
		this.type = classType;
		
		SymbolTableEntry classEntry = new SymbolTableEntry(env.getNestingLevel(), env.increaseOffset(), classType);
		
		if (classDecHM.put("Class$"+id, classEntry) != null)
			res.add("Class '" + id + "' already declared at line " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n");
		
		//}
		// Executing second check on class definitions and everything inside
		//	else {
		// Handling superclass declaration
		// TODO: check null!
		
		if (superClassID != null) {
			SymbolTableEntry superclassEntry = env.getClassEntry(superClassID);
			if (superclassEntry == null)
				res.add("Superclass '" + superClassID + "' not declared at line " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n");
			else {
				if (superClassID.equals(id))
					res.add("Class '" + id + "' cannot extends itself at line " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n");
				else {
					//ClassType classType = (ClassType)env.getClassEntry(id).getType();
					
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
		//int parOffset=1;
		env.setOffset(1 + classType.getFieldsList(true).size() - fields.size());
		
		for (Node f: fields) {
			VarNode field = (VarNode)f;
			res.addAll(field.checkSemantics(env));
		}
		
		ArrayList<Node> inheritedMethods = classType.getMethodsList(true, true);
		Set<String> uniqueIDs = new HashSet<>();
		for (Node method: inheritedMethods)
			if (!uniqueIDs.contains(method.getID())) uniqueIDs.add(method.getID());
		
		int methodBaseOffset = uniqueIDs.size();
		
		env.setOffset(methodBaseOffset);
		for (Node dec : methods) {
			tmp.addAll(dec.checkSemantics(env));
		}
		
		env.settingFunSecondCheck(true);
		
		//if(tmp.size()>0) {
		env.setOffset(methodBaseOffset);
		for (Node dec : methods) {
			res.addAll(dec.checkSemantics(env));
		}
		//}
		System.out.println("tmp: "+tmp+"\n");
		
		System.out.println("res: "+res+"\n");
		
		HashSet<String> fin = new HashSet<>();
		
		for(String s : res){
			if (tmp.contains(s)){
				fin.add(s);
			}
		}
		
		System.out.println("fin: "+fin+"\n");
		
		res.removeAll(tmp);
		
		System.out.println("res: "+res+"\n");
		
		res.addAll(fin);
		
		System.out.println("res: "+res+"\n");
		
		//res.addAll(tmp);
		
		env.settingFunSecondCheck(false);
		
		//env.updateClassEntry(classType);
		
		env.popScope();
		//	}
		
		return res;
	}
	
	/**
	 *
	 * La funzione <strong>typeChecking</strong> avvia il controllo su campi e metodi della classe
	 *
	 * */
	public Node typeCheck() throws Exception{
		/*
		
			1 - controllare che la classe ne estenda un'altra superClassID!=null
			2 - per ogni metodo della classe devo controllare, nel caso in cui lo sovrascriva,
				che il tipo dell'ultimo metodo sia sottotipo di quello di cui fa overriding.
			3 - I parametri del nuovo metodo siano TUTTI sopratipo di quelli del metodo di cui si fa overriding.
		    
		    
		    N.B. il controllo sull'overriding di metodo è implementato nel nodo del metodo.
		 */
		/*if(superClassID!=null){
			if(!Helpers.subtypeOf(type, type.getSuperType())){
				throw new Exception("class: " + id + " is not subtype of class: " + superClassID);
			} //questo controllo dovrebbe essere superfluo.
		}*/
		
		for(Node f : fields){
			f.typeCheck();
		}
		for(Node m : methods){
			m.typeCheck();
		}
		return type;
	}
	
	public String codeGeneration(){
		ArrayList<Node> methodsList = type.getMethodsList(true, false);
		ArrayList<String> dt = new ArrayList<>();
		
		for (Node m: methodsList) {
			MethodDecNode method = (MethodDecNode)m;
			SymbolTableEntry entry = method.getSTEntry();
			int offset = entry.getOffset();
			dt.set(offset, Helpers.newFuncLabel());
		}
		
		Helpers.addDispatchTable(id, dt);
		
		return "";
	}
	
	public String getID() {
		return id;
	}
	
	public String getSuperclassID() {
		return superClassID;
	}
}

