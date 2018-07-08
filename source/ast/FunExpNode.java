package ast;

import ast.types.IntType;
import ast.types.VoidType;
import org.antlr.v4.runtime.ParserRuleContext;
import utils.Environment;
import utils.SemanticError;
import utils.SymbolTableEntry;

import java.util.ArrayList;

public class FunExpNode implements Node {
	
	protected String id;
	protected ArrayList<Node> args;
	protected SymbolTableEntry entry = null;
	protected int callNestingLevel;
	private boolean isExp;
	private ParserRuleContext ctx;
	
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
	
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		ArrayList<SemanticError> res = new ArrayList<SemanticError>();
		
		int j=env.getNestingLevel();
		SymbolTableEntry tmp=null;
		while (j>=0 && tmp==null)
			tmp=(env.getSymTable().get(j--)).get(id);
		if (tmp==null)
			res.add(new SemanticError("Id "+id+" not declared at line: "+ctx.start.getLine()+":"+ctx.start.getCharPositionInLine()+"\n"));
		else{
			this.entry = tmp;
			this.callNestingLevel = env.getNestingLevel();
			
			for(Node arg : args)
				res.addAll(arg.checkSemantics(env));
		}
		return res;
	}
	
	public String getID() {
		return id;
	}
	
}