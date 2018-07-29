package ast.types;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import ast.Node;
import org.antlr.v4.runtime.ParserRuleContext;
import utils.Environment;
import utils.SymbolTableEntry;

public class ClassType implements Node {
	
	private String classID;
	private ClassType superType;
	
	private ArrayList<Node> methods;
	private ArrayList<Node> fields;
	
	private ParserRuleContext ctx;
	
	// campi TODO : arraylist? hashmap?
	// metodi
	
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
	
	// Getters
	public String getClassID() {
		return classID;
	}
	
	public ClassType getSuperType() {
		return superType;
	}
	
	public void setFields(ArrayList<Node> fields){
		this.fields = fields;
	}
	
	public void setMethods(ArrayList<Node> methods){
		this.methods = methods;
	}
	
	public ArrayList<Node> getFieldsList(boolean incInherited) {
		// TODO: risalire la gerarchia delle classi per includere anche i campi ereditati se incInherited = true (da verificare)
		ArrayList<Node> fieldsList = new ArrayList<>();
		
		if (incInherited && superType != null) {
			ArrayList<Node> superFieldsList = superType.getFieldsList(true);
			fieldsList.addAll(superFieldsList);
		}
		fieldsList.addAll(fields);
		
		return fieldsList;
	}
	
	public Set<String> getSuperList(boolean incInherited) {
		// TODO: risalire la gerarchia delle classi per
		Set<String> classList = new HashSet<>();
		
		if (incInherited && superType != null) {
			Set<String> superClassList = superType.getSuperList(true);
			classList.addAll(superClassList);
		}
		//if(superType!=null) {
		//	classList.add(superType.classID);
		//}
		//else
			classList.add(classID);
		return classList;
	}
	
	public ArrayList<Node> getMethodsList(boolean incInherited, boolean excludeCurrent) {
		// TODO: risalire la gerarchia delle classi per includere anche i metodi ereditati se incInherited = true (da verificare)
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
		return classID; // TODO: to be updated
	}
	
	public HashSet<String> checkSemantics(Environment env) {
		// TODO: non dovrebbe servire
		HashSet<String> res = new HashSet<String>();
		
		SymbolTableEntry entry = env.getClassEntry(classID); // DO NOT ADD Class$ (all class IDs in entries will be changed to Class$NameClass form)
		if (entry == null)
			res.add("Class " + classID + " not declared at line " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n");
		
		//this.superClassList=
		
		return res;
	}
	
	public Node typeCheck() {
		// TODO: da implementare
		return this;
	}
	
	public String codeGeneration() {
		// TODO: da implementare
		return null;
	}
	
	public String getID() {
		return classID;
	}
}