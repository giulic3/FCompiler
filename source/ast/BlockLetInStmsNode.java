package ast;

import ast.types.BaseType;
import utils.Environment;
import utils.SemanticError;

import java.util.ArrayList;

public class BlockLetInStmsNode implements Node {
	
	private Node stms;
	private ArrayList<Node> decs;
	
	public BlockLetInStmsNode(ArrayList<Node> d, Node s){
		stms=s;
		decs=d;
	}
	public String toPrint(String s){
		
		String msg = "\n"+s+"    BlockLetInStms: " ;
		for (Node b:decs) {
			msg += "\n "+ s+b.toPrint("\t");
		}
		msg += "\n"+ stms.toPrint(s+"    ");
		return msg;
		
		
		//return "\n" + s +"  BlockLetInStms: \n"  + dec.toPrint(s+"   ") + "\n" /*+ stms.toPrint(s+"    ") */;
	};
	
	public Node typeCheck(){return null;};
	
	public String codeGeneration(){return null;};
	
	public ArrayList<SemanticError> checkSemantics(Environment env){return null;};
	
}


