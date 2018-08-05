package ast.types;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import ast.MethodDecNode;
import ast.Node;
import ast.VarNode;
import org.antlr.v4.runtime.ParserRuleContext;
import utils.Environment;
import utils.SymbolTableEntry;

public class ClassType implements Node {
	
	private String classID;
	private ClassType superType;
	
	private ArrayList<Node> methods;
	private ArrayList<Node> fields;
	
	private ParserRuleContext ctx;
	
	/**
	 *
	 * Nodo indicativo del tipo Classe, ogni classe è un istanza di questo tipo.
	 *
	 * */
	public ClassType(String classID, ParserRuleContext ctx) {
		this.classID = classID;
		this.ctx = ctx;
		this.superType = null;
		this.fields = new ArrayList<>();
		this.methods = new ArrayList<>();
	}

	public ClassType(String classID, ClassType superType, ArrayList<Node> fields, ArrayList<Node> methods, ParserRuleContext ctx) {
		this.classID = classID;
		this.superType = superType;
		this.fields = fields;
		this.methods = methods;
		this.ctx = ctx;
	}
	
//	public ClassType(ClassType c) {
//		this.classID = new String(c.classID);
//		this.superType = (c.superType != null) ? new ClassType(c.superType) : null;
//		this.fields = new ArrayList<>(c.fields);
//		for (Node n: c.fields) {
//			VarNode var = (VarNode)n;
//			this.fields.add(VarNode.copyInstance(var));
//		}
//		this.methods = new ArrayList<>(c.methods);
//		this.ctx = new ParserRuleContext();
//		this.ctx.copyFrom(c.ctx);
//	}
	
	// TODO: sperimentale
	public static ClassType copyInstance(ClassType c) {
		if (c == null) return null;
		
		ParserRuleContext ctx = new ParserRuleContext();
		ctx.copyFrom(c.ctx);
		ClassType copy = new ClassType(c.classID, ctx);
		copy.superType = ClassType.copyInstance(c.superType);
		copy.fields = new ArrayList<>(c.fields);
		for (Node n: c.fields) {
			VarNode var = (VarNode)n;
			copy.fields.add(VarNode.copyInstance(var));
		}
		copy.methods = new ArrayList<>(c.methods);
//		for (Node m: c.methods) {
//			MethodDecNode meth = (MethodDecNode)m;
//			copy.methods.add(MethodDecNode.copyInstance(meth));
//		}
		return copy;
	}
	
	// Getters
	public String getClassID() {
		return classID;
	}
	
	public ArrayList<Node> getFieldsList(boolean incInherited) {
		ArrayList<Node> fieldsList = new ArrayList<>();
		
		if (incInherited && superType != null) {
			ArrayList<Node> superFieldsList = superType.getFieldsList(true);
			fieldsList.addAll(superFieldsList);
		}
		fieldsList.addAll(fields);
		
		return fieldsList;
	}
	
	public Set<String> getSuperList(boolean incInherited) {
		Set<String> classList = new HashSet<>();
		
		if (incInherited && superType != null) {
			Set<String> superClassList = superType.getSuperList(true);
			classList.addAll(superClassList);
		}
		classList.add(classID);
		
		return classList;
	}
	
	public ArrayList<Node> getMethodsList(boolean incInherited, boolean excludeCurrent) {
		ArrayList<Node> methodsList = new ArrayList<>();
		
		if (incInherited && superType != null) {
			ArrayList<Node> superMethodsList = superType.getMethodsList(true, false);
			methodsList.addAll(superMethodsList);
		}
		if (!excludeCurrent) methodsList.addAll(methods);
		
		return methodsList;
	}
	
	// Setters
	public void setSuperType(ClassType type) {
		superType = type;
	}
	
	public String toPrint(String indent) {
		return classID + " type";
	}
	
	public HashSet<String> checkSemantics(Environment env) {
		HashSet<String> res = new HashSet<>();
		
		SymbolTableEntry entry = env.getClassEntry(classID);
		if (entry == null)
			res.add("Class " + classID + " not declared at line " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n");
		
		return res;
	}
	
	public Node typeCheck() {
		return this;
	}
	
	public String codeGeneration() {
		return null;
	}
	
	public String getID() {
		return classID;
	}
}