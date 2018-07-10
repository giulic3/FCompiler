package ast;

import ast.types.VoidType;
import utils.Environment;
;

import java.util.ArrayList;
import java.util.HashSet;

public class PrintNode implements Node {
	
	private ArrayList<Node> exps;
	
	public PrintNode(ArrayList<Node> exps) {
		this.exps = exps;
	}
	
	public String toPrint(String indent) {
		String printMsg = indent + "Print Node:";
		
		for (Node e:this.exps) {
			printMsg += "\n" + e.toPrint(indent + "\t\t");
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
	
	public HashSet<String> checkSemantics(Environment env) {
		// TODO: da controllare
		HashSet<String> res = new HashSet<String>();
		
		for (Node exp: exps)
			res.addAll(exp.checkSemantics(env));
		
		return res;
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return null;
	}
}
