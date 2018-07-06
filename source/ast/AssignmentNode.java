package ast;

import ast.types.BaseType;
import ast.types.VoidType;
import utils.Environment;
import utils.SemanticError;
import utils.SymbolTableEntry;

import java.util.ArrayList;

public class AssignmentNode implements Node {
	
	private String id;
	private Node exp;
	
	public AssignmentNode(String id, Node exp){
		this.id=id;
		this.exp=exp;
	}
	
	public String toPrint(String s){
		return s + "Assignment Node: " + id + " (type: '') = " + exp.toPrint("");
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
	
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		
		/*
		* controllare che la definizione non esista già nella symbol table
		*
		* */
		
		ArrayList<SemanticError> res = new ArrayList<>();
		
		SymbolTableEntry entry = env.getActiveDec(id);
		if (entry == null)
			res.add(new SemanticError("Variable " + id + " not declared\n"));
		
		res.addAll(exp.checkSemantics(env));
		
		return res;
	}
}
