package ast;


import ast.types.BaseType;
import ast.types.BoolType;
import grammars.FOOL.FOOLParser;
import utils.Environment;
import utils.SemanticError;

import java.util.ArrayList;

public class IfExpNode implements Node {
	
	private Node cond;
	private Node th;
	private Node el;
	
	public IfExpNode(Node cond, Node th) {
		this.cond = cond;
		this.th = th;
		this.el = null;
	}
	
	public IfExpNode(Node cond, Node th, Node el) {
		this.cond = cond;
		this.th = th;
		this.el = el;
	}
	
	
	@Override
	public String toPrint(String s) {
		String ifThen = s + "If Node:\n" +
				cond.toPrint(s+"\tCond: ") + "\n" +
				s + "\tThen Branch:\n" + th.toPrint(s+"\t\t");
		if (this.el != null)
			ifThen += "\n" + s + "\tElse Branch:\n" + el.toPrint(s+"\t\t");
		
		return ifThen;
	}
	
	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		//create the result
		ArrayList<SemanticError> res = new ArrayList<SemanticError>();
		
		
		
		return res;
	}
	
	@Override
	public BaseType typeCheck() {
		return new BoolType();
	}
	
	@Override
	public String codeGeneration() {
		return null;
	}
	
}
