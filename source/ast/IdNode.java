package ast;

import java.util.ArrayList;

import org.antlr.v4.runtime.ParserRuleContext;
import utils.Environment;
import utils.SemanticError;
import utils.SymbolTableEntry;

/* corresponds to var */
public class IdNode implements Node {

	private String id;
	private SymbolTableEntry entry;
	private int nestinglevel;
	private ParserRuleContext ctx;

	public IdNode (String i, ParserRuleContext ctx) {
		this.ctx=ctx;
		id = i;
	}
	
	public String getID() {
		return id;
	}

	public String toPrint(String s) {

		return s + "ID Node: " + id + ", cur nestinglevel: " + nestinglevel + (entry != null ? entry.toPrint(", ") : "");
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {

		//create result list
		ArrayList<SemanticError> res = new ArrayList<SemanticError>();
		
		nestinglevel=env.getNestingLevel();
		SymbolTableEntry entry = env.getActiveDec(id);
		if (entry == null)
			res.add(new SemanticError("Variable " + id + " not declared at line: "+ctx.start.getLine()+":"+ctx.start.getCharPositionInLine()+"\n"));
		
		this.entry=entry;
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