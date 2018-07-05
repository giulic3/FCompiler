package ast;

import ast.types.BaseType;
import utils.Environment;
import utils.SemanticError;

import java.util.ArrayList;

public class ProgNode  implements Node {

	private ArrayList<Node> blocks;
	
	public ProgNode (ArrayList<Node> d) {
		this.blocks=d;
	}
	
	public String toPrint(String indent){
		String msg = "Prog Node:";
		
		for (Node b:this.blocks) {
			msg += b.toPrint("\t");
		}
		return  msg;
	}
	
	public Node typeCheck(){return null;}
	
	public String codeGeneration(){return null;};
	
	public ArrayList<SemanticError> checkSemantics(Environment env){
		
		ArrayList<SemanticError> res = new ArrayList<SemanticError>();
		for(Node b:blocks){
			res.addAll(b.checkSemantics(env));
		}
		return res;
	}
	
}