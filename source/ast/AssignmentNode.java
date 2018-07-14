package ast;

import ast.types.VoidType;
import utils.Environment;
import utils.Helpers;
;

import java.util.ArrayList;
import java.util.HashSet;

public class AssignmentNode implements Node {
	
	private Node varNode = null;
	private Node exp;
	private Node objFieldNode = null;
	
	public AssignmentNode(Node var, Node exp, boolean isClassField){
		if (isClassField) this.objFieldNode = var;
		else this.varNode = var;
		
		this.exp = exp;
	}
	
	public String toPrint(String s){
		return s + "Assignment Node:\n" + (objFieldNode != null ? objFieldNode.toPrint(s+"\t\t") + "\n" : varNode.toPrint(s+"\t") + "\n" ) + exp.toPrint(s+"\t\t");
	}
	
	public Node typeCheck() throws Exception{
		
			
			Node idTypeCheckRes;
			if (objFieldNode!=null) idTypeCheckRes  = objFieldNode.typeCheck();
			varNode.typeCheck();
			exp.typeCheck();
			/*if(!Helpers.subtypeOf(exp.typeCheck(), idTypeCheckRes)){
				throw new Exception("Assignment Node typeCheck exception");
			}

		/*
		
			1 - risalire la symbol table fino al primo id uguale a quello in esame.
			2 - recuperare il tipo dichiarato
			3 - recuperare il tipo dell'espressione a destra dell'uguale
			4 - confrontarli provocando un errore nel caso di incompatibilità
			5 - per le classi non basta risalire la symbol table ma è necessario anche
				controllare che la classe a DESTRA dell'uguale sia sottotipo di quella a SINISTRA
		        DX <: SX
		 
		 */
		
		return new VoidType();
	}
	
	public String codeGeneration() {
		return null;
	}
	
	public HashSet<String> checkSemantics(Environment env) {
		
		/*
		* controllare che la definizione non esista già nella symbol table
		*
		* */
		
		HashSet<String> res = new HashSet<String>();
		
		if (objFieldNode != null)
			res.addAll(objFieldNode.checkSemantics(env));
		else
			res.addAll(varNode.checkSemantics(env));
		
		res.addAll(exp.checkSemantics(env));
		
		return res;
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return null;
	}
}
