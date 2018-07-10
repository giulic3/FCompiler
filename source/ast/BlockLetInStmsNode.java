package ast;

import utils.Environment;
import utils.SemanticError;

import java.util.ArrayList;

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
		ArrayList<SemanticError> tmp = new ArrayList<SemanticError>();
		
		
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


