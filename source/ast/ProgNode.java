package ast;

import utils.Environment;
import utils.Helpers;

import java.util.ArrayList;
import java.util.HashSet;

public class ProgNode  implements Node {
	
	private ArrayList<Node> blocks;
	
	
	/**
	 *
	 * Nodo di ingresso dell'<em>ast</em> (albero di sintassi astratta)
	 *
	 * */
	public ProgNode (ArrayList<Node> d) {
		this.blocks=d;
	}
	
	/**
	 *
	 * Dà inizio alla generazione della stringa per la stampa del <em>ast</em>
	 *
	 * */
	public String toPrint(String indent){
		String msg = "Prog Node:";
		
		for (Node b:this.blocks) {
			msg += b.toPrint("\t");
		}
		return  msg;
	}
	
	/**
	 *
	 * Dà inizio ai controlli semantici sui nodi del <em>ast</em>
	 *
	 * */
	public HashSet<String> checkSemantics(Environment env){
		
		HashSet<String> errors = new HashSet<>();
		HashSet<String> tmp = new HashSet<>();
		
		env.pushScope();
		
		for (Node b:blocks)
			if (b instanceof BlockClassDecNode)
				tmp.addAll(b.checkSemantics(env));
		
		env.settingSecondCheck();
		
		if (Helpers.detectSuperclassErrors(tmp))
			return tmp;
		
		for (Node b:blocks)
			errors.addAll(b.checkSemantics(env));
		
		return errors;
	}
	
	/**
	 *
	 * Dà inizio ai controlli sui tipi
	 *
	 * */
	public Node typeCheck() throws Exception{
		Node res=null;
		for(Node b:blocks)
			res=b.typeCheck();
		return res;
	}
	
	/**
	 *
	 * Dà inizio ai controlli sui tipi
	 *
	 * */
	public String codeGeneration(){
		String res="";
		for(Node b:blocks)
			res += b.codeGeneration();
		return res +"halt\n" + Helpers.getFuncAssembly() + "\n" + Helpers.generateDispatchTablesCode();
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return null;
	}
}