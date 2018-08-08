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
	
	public Node copyInstance() {
		ArrayList<Node> sCopy = new ArrayList<>(this.stms);
		for (Node n: this.stms)
			sCopy.add(n.copyInstance());
		ArrayList<Node> dCopy = new ArrayList<>(this.decs);
		for (Node n: this.decs)
			dCopy.add(n.copyInstance());
		return new BlockLetInStmsNode(dCopy, sCopy);
	}
	
	public String toPrint(String s){
		StringBuilder msg = new StringBuilder();
		msg.append("\n").append(s).append("BlockLetInStms:\n").append(s).append("\tDecs:");
		
		for (Node b:decs)
			msg.append("\n").append(s).append(b.toPrint("\t\t"));
		
		msg.append("\n").append(s).append("\tIn:");
		for (Node b:stms)
			msg.append("\n").append(s).append(b.toPrint("\t\t"));
		
		return msg.toString();
	}
	
	/**
	 *
	 * Aggiunge uno scope alla symbol table e lancia checkSemantics() sulle relative
	 * dichiarazioni e statment dello scope, successivamente si occupa di rimuovere lo scope.
	 *
	 * */
	public HashSet<String> checkSemantics(Environment env){
		
		HashSet<String> res = new HashSet<>();
		HashSet<String> tmp = new HashSet<>();
		
		env.pushScope();
		
		if (decs.size() > 0) env.setOffset(-1);
		
		for(Node dec : decs)
			res.addAll(dec.checkSemantics(env));
		
		env.settingFunSecondCheck(true);
		
		if (decs.size() > 0) env.setOffset(-1);
		
		for(Node dec : decs) {
			HashSet<String> funErrors;
			if (dec instanceof FunDecNode) {
				funErrors = dec.checkSemantics(env);
				res.addAll(funErrors);
				tmp.addAll(funErrors);
			}
			else tmp.addAll(dec.checkSemantics(env));
		}
		
		env.settingFunSecondCheck(false);
		
		for(Node stm : stms)
			res.addAll(stm.checkSemantics(env));
		
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
		for (Node d : decs) d.typeCheck();
		for (Node s : stms) s.typeCheck();
		return new VoidType();
	}
	
	public String codeGeneration(){
		StringBuilder res = new StringBuilder();
		
		for (Node d : decs) res.append(d.codeGeneration());
		for (Node s : stms) res.append(s.codeGeneration());
		
		res.append("cfp\n");
		return res.toString();
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return null;
	}
}


