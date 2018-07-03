package ast;

import utils.Environment;
import utils.SemanticError;

import java.util.ArrayList;

public class BlockClassDecNode implements Node {
	String id;
	String ext;
	ArrayList<Node> pars;
	ArrayList<Node> methods;
	
	
	/* se non ci sono parametri par sarà null, se non è estesa ext sarà null */
	
	public BlockClassDecNode(String id, String ext, ArrayList<Node> pars, ArrayList<Node> methods) {
		this.id=id;
		this.ext=ext;
		this.pars=pars;
		this.methods=methods;
	}
	
	public String toPrint(String s){
		String msg = "\n"+s+"Class Dec Node: " + this.id;
		if(ext!=null){
			msg+=" extends " + ext;
		}
		msg+=" {";
		if(pars!=null) {
			msg+="\n"+s+"\tParams:";
			for (Node b : pars) {
				msg += "\n " + s + b.toPrint("\t\t");
			}
		}
		msg += "\n"+s+"\tMethods: \n";
		for (Node b : methods) {
			msg += "\n " + s + b.toPrint("\t\t");
		}
		msg+="\n"+s+"}";
		return msg;
	}
	
	public Node typeCheck(){return null;}
	
	public String codeGeneration(){return null;}
	
	public ArrayList<SemanticError> checkSemantics(Environment env){return null;}
}

