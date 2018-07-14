package ast;

import utils.Environment;
;
import utils.SymbolTableEntry;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
	
	public HashSet<String> checkSemantics(Environment env){
		
		HashSet<String> errors = new HashSet<>();
		
		env.pushScope();
		
		for (Node bClass:blocks) {
			if (bClass instanceof BlockClassDecNode)
				errors.addAll(bClass.checkSemantics(env));
		}
		
		env.settingSecondCheck();
		
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