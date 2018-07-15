package ast;

import ast.types.FunType;
import ast.types.IntType;
import ast.types.VoidType;
import org.antlr.v4.runtime.ParserRuleContext;
import utils.Environment;
;
import utils.SymbolTableEntry;

import java.util.ArrayList;
import java.util.HashSet;

/* function invocation */

public class FunExpNode implements Node {
	
	protected String id;
	protected ArrayList<Node> args;
	protected SymbolTableEntry entry = null;
	protected int callNestingLevel;
	protected boolean isExp;
	protected ParserRuleContext ctx;
	
	public FunExpNode(String ID, ArrayList<Node> args, boolean isExp, ParserRuleContext ctx){
		this.ctx=ctx;
		this.id = ID;
		this.args=args;
		this.isExp = isExp;
	}
	
	
	public String toPrint(String s) {
		String msg = s+"Function Call Node: " + this.id + "(";
		
		if (this.args != null && !this.args.isEmpty()) {
			for (Node b : this.args) {
				msg += "\n " + s + b.toPrint("\t");
			}
			msg += "\n" + s + ")";
		} else
			msg += ")";
		
		return msg;
	}
	
	
	@Override
	public Node typeCheck() {
		if (!isExp)
			return new VoidType();
		else
			return new IntType();
		
		// TODO: da implementare
		/*
		 *
		 * 1 - cercare la funzione nella symbol table, se non la trovo errore
		 * 2 - se trovo l'identificatore devo controllare che sia una funzione
		 * 3 - controllo che il numero di parametri sia uguale
		 * 4 - controllo i tipi di tutti i parametri
		 *
		 * */
	}
	
	public String codeGeneration() {
		// TODO: da implementare
		return null;
	}
	
	public HashSet<String> checkSemantics(Environment env) {
		HashSet<String> res = new HashSet<String>();

		SymbolTableEntry entry = env.getActiveDec("Function$" + id);

		if (entry==null)
			res.add("Fun "+id+" not declared at line: "+ctx.start.getLine()+":"+ctx.start.getCharPositionInLine()+"\n");
		else{
			this.entry = entry;
			this.callNestingLevel = env.getNestingLevel();
			
			for(Node arg : args)
				res.addAll(arg.checkSemantics(env));

			FunType funType = (FunType) this.entry.getType();
			if (funType.getParList().size() != args.size())
				res.add("Function " + this.id + " call with wrong number of parameters is not allowed at line "
						+ ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n");
		}
		return res;
	}
	
	public String getID() {
		return id;
	}
	
}