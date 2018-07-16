package ast;

import utils.Environment;
import java.util.ArrayList;
import java.util.HashSet;

public class ProgNode  implements Node {
	
	/**
	 *
	 * Nodo di ingresso dell'ast (albero di sintassi astratta)
	 *
	 * */
	
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
	
	public Node typeCheck() throws Exception{
		Node res=null;
		for(Node b:blocks)
			res=b.typeCheck();
		return res;
	}
	
	public String codeGeneration(){
		String res="";
		for(Node b:blocks)
			res += b.codeGeneration();
		return res+"halt\n";
	}
	
	public HashSet<String> checkSemantics(Environment env){
		
		HashSet<String> errors = new HashSet<>();
		
		env.pushScope();
		
		env.settingSecondCheck();
		
		if (errors.size() ==0)
		for(Node b:blocks)
			errors.addAll(b.checkSemantics(env));
		
		// QUI NON SERVE LA POPSCOPE!!!
		
		return errors;
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return null;
	}
}