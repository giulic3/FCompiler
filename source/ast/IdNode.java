package ast;

import java.util.ArrayList;

import ast.types.ArrowType;
import ast.types.BaseType;
import ast.types.IntType;
import utils.Environment;
import utils.SemanticError;
import utils.SymbolTableEntry;

/* corresponds to var */
public class IdNode implements Node {

	private String id;
	private SymbolTableEntry entry;
	private int nestinglevel;

	public IdNode (String i) {
		id = i;
	}

	public String toPrint(String s) {

		/*
		commento temporaneo: la toPrint viene eseguita dopo la fase di checkSemantics quando
		nestingLevel è già inizializzato

		return s+"Id:" + id
				+ " at nestlev " + nestinglevel +"\n"
				+ entry.toPrint(s+"  ") ;
		*/

		return s + "ID: " + id;
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {

		//create result list
		ArrayList<SemanticError> res = new ArrayList<SemanticError>();
		/*
		int j = env.getNestingLevel();
		SymbolTableEntry tmp = null;
		while (j>=0 && tmp==null)
			tmp = (env.getSymTable().get(j--)).get(id);
		if (tmp==null)
			res.add(new SemanticError("Id "+id+" not declared"));

		else{
			entry = tmp;
			nestinglevel = env.getNestingLevel();
		}
		*/
		return res;
	}

	public Node typeCheck () {
		/*
		if (entry.getType() instanceof ArrowType) { //
			System.out.println("Wrong usage of function identifier");
			System.exit(0);
		}
		*/
		
		/* da cambiare anche il tipo in STEntry*/
		return entry.getType();
		//return new IntType();
		
	}

	public String codeGeneration() {
		/*
		String getAR="";
		for (int i=0; i<nestinglevel-entry.getNestinglevel(); i++)
			getAR+="lw\n";
		return "push "+entry.getOffset()+"\n"+ //metto offset sullo stack
				"lfp\n"+getAR+ //risalgo la catena statica
				"add\n"+
				"lw\n"; //carico sullo stack il valore all'indirizzo ottenuto
				*/

		return null;
	}
}