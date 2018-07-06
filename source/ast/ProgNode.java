package ast;

import ast.types.BaseType;
import sun.jvm.hotspot.debugger.cdbg.Sym;
import utils.Environment;
import utils.SemanticError;
import utils.SymbolTableEntry;

import java.util.ArrayList;
import java.util.HashMap;

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
		
		HashMap<String, SymbolTableEntry> hm = new HashMap<>();
		env.setNestingLevel(0);
		env.getSymTable().add(hm);
		
		//env.setOffset(-2);
		
		for(Node b:blocks){
			res.addAll(b.checkSemantics(env));
		}
		
		env.getSymTable().remove(env.getNestingLevel());
		env.setNestingLevel(env.getNestingLevel()-1);
		
		return res;
	}
	
}