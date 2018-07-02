package ast;

import ast.types.BaseType;
import utils.Environment;
import utils.SemanticError;

import java.util.ArrayList;

public class BlockLetInStmsNode implements Node {
	
	private Node stms;
	private Node dec;
	
	public BlockLetInStmsNode(Node d, Node s){
		stms=d;
		dec=s;
	}
	public String toPrint(String s){
		return "\n" + s +"  BlockLetInStms: \n"  + dec.toPrint(s+"   ") + "\n" + stms.toPrint(s+"    ") ;
	};
	
	public BaseType typeCheck(){return null;};
	
	public String codeGeneration(){return null;};
	
	public ArrayList<SemanticError> checkSemantics(Environment env){return null;};
	
}


