package ast;

import ast.types.VoidType;
import utils.Environment;

import java.util.ArrayList;
import java.util.HashSet;

public class BlockLetInStmsNode implements Node {
	
	private ArrayList<Node> stms;
	private ArrayList<Node> decs;
	
	/**
	 *
	 * Nodo per la gestione degli <strong>Scope Let in Stms</strong>.
	 *
	 * */
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
	}
	
	/**
	 *
	 * Aggiunge uno scope alla symbol table e lancia checkSemantics() sulle relative
	 * dichiarazioni e statment dello scope, successivamente si occupa di rimuovere lo scope.
	 *
	 * */
	public HashSet<String> checkSemantics(Environment env){
		
		HashSet<String> res = new HashSet<String>();
		HashSet<String> tmp = new HashSet<String>();
		
		env.pushScope();
		
		if (decs.size() > 0) env.setOffset(-1);
		
		for(Node dec : decs){
			res.addAll(dec.checkSemantics(env));
		}
		
		env.settingFunSecondCheck(true);
		
		if (decs.size() > 0) env.setOffset(-1);
		
		//if (tmp.size() > 0)
		for(Node dec : decs){
			tmp.addAll(dec.checkSemantics(env));
		}
		
		env.settingFunSecondCheck(false);
		
		for(Node stm : stms){
			res.addAll(stm.checkSemantics(env));
		}
		
		HashSet<String> fin = new HashSet<>();
		
		for(String s : res){
			if (tmp.contains(s)){
				fin.add(s);
			}
		}
		
		res.removeAll(tmp);
		
		res.addAll(fin);
		
		env.popScope();
		
		return res;
		
	}
	
	public Node typeCheck() throws Exception {
		for(Node d : decs){
			d.typeCheck();
		}
		for(Node s : stms){
			s.typeCheck();
		}
		return new VoidType();
	}
	
	public String codeGeneration(){
		String res="";
		for (Node d : decs){
			res += d.codeGeneration();
		}
		for (Node s : stms){
			res += s.codeGeneration();
		}
		res += "cfp\n";
		return res;
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return null;
	}
}


