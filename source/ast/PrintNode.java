package ast;

import ast.types.BaseType;
import ast.types.VoidType;
import utils.Environment;
import utils.SemanticError;

import java.util.ArrayList;

public class PrintNode implements Node {
	
	private Node exp;
	private ArrayList<Node> otherExps;
	
	PrintNode(Node exp) {
		this.exp = exp;
		this.otherExps = null;
	}
	
	PrintNode(Node exp, ArrayList<Node> otherExps) {
		this.exp = exp;
		this.otherExps = otherExps;
	}
	
	public String toPrint(String indent) {
		String printMsg = indent + "Print Node:\n" +
				exp.toPrint(indent + "\t");
		
		for (Node e:otherExps) {
			printMsg += "\n" + e.toPrint(indent + "\t");
		}
		
		return printMsg;
	}
	
	public Node typeCheck() {
		return new VoidType();
	}
	
	public String codeGeneration() {
		// TODO: da implementare
		return null;
	}
	
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		// TODO: da implementare
		return null;
	}
}
