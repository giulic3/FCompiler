package ast;

import utils.Environment;
import utils.SemanticError;
import utils.SymbolTableEntry;

import java.util.ArrayList;

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
		ArrayList<SemanticError> res1 = new ArrayList<>();
		
		env.pushScope();
		
		for (Node bClass:blocks) {
			if (bClass instanceof BlockClassDecNode)
				res1.addAll(bClass.checkSemantics(env));
		}
		
		System.out.println("Prima passata");
		System.out.println(res1);
		
		System.out.println("Seconda passata");
		env.settingSecondCheck();
		
		System.out.println(env.getSecondCheck());
		
		for(Node b:blocks){
			res.addAll(b.checkSemantics(env));
		}
		
		// QUI NON SERVE LA POPSCOPE!!!
		
		return res;
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return null;
	}
}