package ast;

import ast.types.VoidType;
import utils.Environment;
;

import java.util.ArrayList;
import java.util.HashSet;

public class AssignmentNode implements Node {
	
	private Node id;
	private Node exp;
	private Node object;
	
	public AssignmentNode(Node id, Node exp){
		this.id=id;
		this.exp=exp;
		this.object = null;
	}
	
	public AssignmentNode(Node id, Node exp, Node object){
		this.id=id;
		this.exp=exp;
		this.object = object;
	}
	
	public String toPrint(String s){
		// TODO: handle object printing
		return s + "Assignment Node:\n" + id.toPrint(s+"\t\t") + "\n" + exp.toPrint(s+"\t\t");
	};
	
	public Node typeCheck() {
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
		
		if (object != null)
			res.addAll(object.checkSemantics(env));
		
		res.addAll(id.checkSemantics(env));
		res.addAll(exp.checkSemantics(env));
		
		return res;
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return null;
	}
}
