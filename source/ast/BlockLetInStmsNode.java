package ast;

import ast.types.BaseType;
import utils.Environment;
import utils.SemanticError;
import utils.SymbolTableEntry;

import java.util.ArrayList;
import java.util.HashMap;

public class BlockLetInStmsNode implements Node {
	
	private ArrayList<Node> stms;
	private ArrayList<Node> decs;
	
	public BlockLetInStmsNode(ArrayList<Node> d, ArrayList<Node> s){
		stms=s;
		decs=d;
	}
	public String toPrint(String s){
		
		String msg = "\n" + s + "BlockLetInStms:\n" + s + "\tDecs:";
		for (Node b:decs) {
			msg += "\n" + s + b.toPrint("\t\t");
		}
		msg += "\n" + s + "\tIn:";
		for (Node b:stms) {
			msg += "\n" + s + b.toPrint("\t\t");
		}
		//msg += "\n" + s + "\tIn:\n" + stms.toPrint(s+"\t\t");
		return msg;
		
		
		//return "\n" + s +"  BlockLetInStms: \n"  + dec.toPrint(s+"   ") + "\n" /*+ stms.toPrint(s+"    ") */;
	};
	
	public Node typeCheck(){return null;}
	
	public String codeGeneration(){return null;}
	
	public ArrayList<SemanticError> checkSemantics(Environment env){
		
		ArrayList<SemanticError> res = new ArrayList<SemanticError>();
		
		env.setNestingLevel(env.getNestingLevel()+1);
		// TODO: handle offset
		
		HashMap<String, SymbolTableEntry> hm = new HashMap<>();
		env.getSymTable().add(hm);
		
		for(Node dec : decs){
			res.addAll(dec.checkSemantics(env));
			
		}
		
		for(Node stm : stms){
			res.addAll(stm.checkSemantics(env));
		}
		
		env.getSymTable().remove(env.getNestingLevel());
		env.setNestingLevel(env.getNestingLevel()-1);
		
		return res;
	
	}
	
}


