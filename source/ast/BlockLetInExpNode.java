package ast;

import ast.types.BaseType;
import utils.Environment;
import utils.SemanticError;

import java.util.ArrayList;

public class BlockLetInExpNode implements Node {

	private ArrayList<Node> decs;
	private Node exp;

	public BlockLetInExpNode(ArrayList<Node> d, Node e){

		decs = d;
		exp = e;
	}
	public String toPrint(String s){

		String msg = "\n"+s+"    BlockLetInExp: " ;
		for (Node b : decs) {
			msg += "\n "+ s+b.toPrint("\t");
		}
		msg += "\n"+ exp.toPrint(s+"    ");
		return msg;
	}

	public Node typeCheck(){return null;}

	public String codeGeneration(){return null;}

	public ArrayList<SemanticError> checkSemantics(Environment env){return null;}

}


