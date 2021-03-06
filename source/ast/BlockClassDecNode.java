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
	
	public Node copyInstance() {
		ParserRuleContext ctx = new ParserRuleContext();
		ctx.copyFrom(this.ctx);
		ArrayList<Node> fieldsCopy = new ArrayList<>(this.fields);
		for (Node n: this.fields)
			fieldsCopy.add(n.copyInstance());
		ArrayList<Node> methCopy = new ArrayList<>(this.methods);
		for (Node n: this.methods)
			methCopy.add(n.copyInstance());
		BlockClassDecNode copy = new BlockClassDecNode(this.id, this.superClassID, fieldsCopy, methCopy, ctx);
		copy.type = (ClassType)this.type.copyInstance();
		return copy;
	}
	
	public String toPrint(String s){
		StringBuilder msg = new StringBuilder();
		msg.append("\n").append(s).append("Class Dec Node: ").append(this.id);
		
		if (superClassID != null) msg.append(" extends ").append(superClassID);
		
		msg.append(" {");
		
		if (fields != null) {
			msg.append("\n").append(s).append("\tFields:");
			for (Node b : fields)
				msg.append("\n").append(s).append(b.toPrint("\t\t"));
		}
		
		msg.append("\n").append(s).append("\tMethods:");
		for (Node b : methods)
			msg.append("\n").append(s).append(b.toPrint("\t\t"));
		
		msg.append("\n").append(s).append("}");
		return msg.toString();
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
		HashSet<String> res = new HashSet<>();
		HashSet<String> tmp = new HashSet<>();
		
		HashMap<String, SymbolTableEntry> classDecHM = env.getSymTable().get(env.getNestingLevel());
		
		ClassType classType = new ClassType(id, null, fields, methods, ctx);
		
		this.type = classType;
		
		SymbolTableEntry classEntry = new SymbolTableEntry(env.getNestingLevel(), env.increaseOffset(), classType);
		if(!env.getSecondCheck())
			if (classDecHM.put("Class$"+id, classEntry) != null)
				res.add("Class '" + id + "' already declared at line " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n");
				
		if (superClassID != null) {
			SymbolTableEntry superclassEntry = env.getClassEntry(superClassID);
			if (superclassEntry == null)
				res.add("Superclass '" + superClassID + "' not declared at line " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n");
			else {
				if (superClassID.equals(id))
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
		env.setOffset(1 + classType.getFieldsList(true).size() - fields.size());
		
		env.setDefiningClass(id);
		
		for (Node f : fields) {
			VarNode field = (VarNode) f;
			res.addAll(field.checkSemantics(env));
			
			if (field.getType() instanceof ClassType) {
				Node expNode = field.getExp();
				if (expNode instanceof NewExpNode) {
					SymbolTableEntry newEntry = ((NewExpNode) expNode).getSTEntry();
					String newClassID = newEntry.getType().getID();
					
					if (id.equals(newClassID))
						res.add("Object initialization can't have same type of defining class (use NULL or a subtype instead) at line " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n");
				} else if (!(expNode instanceof NullNode))
					res.add("Object as class field must be either initialized using NULL or a subtype at line " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n");
			}
		}
		
		ArrayList<Node> inheritedMethods = classType.getMethodsList(true, true);
		Set<String> uniqueIDs = new HashSet<>();
		for (Node method : inheritedMethods)
			if (!uniqueIDs.contains(method.getID())) uniqueIDs.add(method.getID());
		
		int methodBaseOffset = uniqueIDs.size();
		
		env.setOffset(methodBaseOffset);
		for (Node dec : methods) {
			tmp.addAll(dec.checkSemantics(env));
		}
		
		env.settingFunSecondCheck(true);
		
		env.setOffset(methodBaseOffset);
		for (Node dec : methods) {
			res.addAll(dec.checkSemantics(env));
		}
		
		HashSet<String> fin = new HashSet<>();
		for (String s : res) {
			if (tmp.contains(s)) {
				fin.add(s);
			}
		}
		res.removeAll(tmp);
		res.addAll(fin);
		
		env.settingFunSecondCheck(false);
		env.setDefiningClass(null);
		
		env.popScope();
		
		return res;
	}
	
	/**
	 *
	 * La funzione <strong>typeChecking</strong> avvia il controllo su campi e metodi della classe
	 *
	 * */
	public Node typeCheck() throws Exception {
		
		for(Node f : fields) f.typeCheck();
		for(Node m : methods) m.typeCheck();
		
		return type;
	}
	
	public String codeGeneration() {
		ArrayList<Node> methodsList = type.getMethodsList(false, false);
		ArrayList<String> dt = (superClassID != null) ? new ArrayList<>(Helpers.getDispatchTable(superClassID)) : new ArrayList<>();
		Helpers.addDispatchTable(id, dt);
		
		for (Node m: methodsList) {
			MethodDecNode method = (MethodDecNode)m;
			SymbolTableEntry entry = method.getSTEntry();
			int offset = entry.getOffset();
			
			String methodLabel = Helpers.newFuncLabel();
			
			if (dt.size() > 0 && offset < dt.size())
				dt.set(offset, methodLabel);
			else
				dt.add(offset, methodLabel);
		}
		
		for (Node m: methodsList) {
			MethodDecNode method = (MethodDecNode)m;
			SymbolTableEntry entry = method.getSTEntry();
			int offset = entry.getOffset();
			
			String methodCode = method.codeGeneration();
			dt.set(offset, methodCode);
		}
		
		return "";
	}
	
	public String getID() {
		return id;
	}
}

