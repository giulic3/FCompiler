package ast;

import ast.types.VoidType;
import utils.Environment;
;

import java.util.ArrayList;
import java.util.HashSet;

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
	
	public Node typeCheck() throws Exception {
		for(Node s : stms){
			s.typeCheck();
		}
		for(Node d : decs){
			d.typeCheck();
		}
		return new VoidType();
	}
	
	public String codeGeneration(){return null;}
	
	public HashSet<String> checkSemantics(Environment env){
		
		HashSet<String> res = new HashSet<String>();
		HashSet<String> tmp = new HashSet<String>();
		
		
		// TODO: handle offset
		env.pushScope();
		
		for(Node dec : decs){
			tmp.addAll(dec.checkSemantics(env));
			
		}
		
		env.settingFunSecondCheck(true);
		
		if (tmp.size() > 0)
		for(Node dec : decs){
			res.addAll(dec.checkSemantics(env));
		}
		env.settingFunSecondCheck(false);
		
		for(Node stm : stms){
			res.addAll(stm.checkSemantics(env));
		}
		
		env.popScope();
		
		return res;
	
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return null;
	}
}


