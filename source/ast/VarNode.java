package ast;

import java.util.ArrayList;
import java.util.HashMap;

import ast.types.BaseType;
import utils.Environment;
import utils.SemanticError;

public class VarNode implements Node {
	
	private String id;
	private Node type;
	private Node exp;
	
	public VarNode (String i, Node t, Node v) {
		id = i;
		type = t;
		exp = v;
	}
	
	public String toPrint(String s){
		return s+"Var:" + id +"\n"+s+"        "
				+type.toPrint(s+"  ")+"\n"
				+s+"  "+exp.toPrint(s+"    ");
	}
	
	public Node typeCheck(){return null;};
	
	public String codeGeneration(){return null;};
	
	public ArrayList<SemanticError> checkSemantics(Environment env){return null;};
	
}