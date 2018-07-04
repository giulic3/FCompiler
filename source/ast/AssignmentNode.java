package ast;

import ast.types.BaseType;
import ast.types.VoidType;
import utils.Environment;
import utils.SemanticError;

import java.util.ArrayList;

public class AssignmentNode implements Node {
	
	private String id;
	private Node exp;
	
	public AssignmentNode(String id, Node exp){
		this.id=id;
		this.exp=exp;
	}
	
	public String toPrint(String s){
		return s + "Assignment Node: " + id + " (type: '') = " + exp.toPrint("");
	};
	
	public Node typeCheck() {
		return new VoidType();
	}
	
	public String codeGeneration() {
		return null;
	}
	
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		return null;
	}
}
