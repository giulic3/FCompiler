package ast;

import ast.types.BaseType;
import ast.types.IntType;
import org.antlr.v4.runtime.ParserRuleContext;
import utils.Environment;
import utils.SemanticError;
import utils.SymbolTableEntry;

import java.util.ArrayList;

/* method call */
public class MethodNode implements Node {
	
	
	private String obj;
	private String id;
	private ArrayList<Node> args;
	private SymbolTableEntry entry = null;
	private int callNestingLevel;
	private boolean isExp;
	private ParserRuleContext ctx;

	public MethodNode(String obj, String ID, ArrayList<Node> args, boolean isExp, ParserRuleContext ctx){
		this.ctx=ctx;
		this.obj = obj;
		this.id = ID;
		this.args = args;
		this.isExp = isExp;
	}
	
	
	public String toPrint(String s) {
		String msg = s+"Method Call Node:\n" + s + "\tObject: " + this.obj + "\n" + s + "\tMethod: " + this.id +"(";
		if (this.args != null && !this.args.isEmpty()) {
			for (Node p : this.args) {
				msg += "\n " + s + p.toPrint("\t\t");
			}
			msg += "\n" + s + "\t)";
		} else
			msg += ")";
		return msg;
	}
	
	
	@Override
	public Node typeCheck() {
		return new IntType();
	}
	
	public String codeGeneration() {
		// TODO: da implementare
		return null;
	}
	
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		
		/*
		 *  spudoratamente copiato dalla funexp ma in questo caso è più complesso.
		 *  Va cercata la definizione dell'oggetto, la classe con cui è definito
		 *  e va cercato il metodo all'interno della classe in esame
		 */
		
		
		ArrayList<SemanticError> res = new ArrayList<SemanticError>();
		
		int j=env.getNestingLevel();
		SymbolTableEntry tmp=null;
		while (j>=0 && tmp==null)
			tmp=(env.getSymTable().get(j--)).get(obj);
		if (tmp==null)
			res.add(new SemanticError("Id "+obj+" not declared at line: "+ctx.start.getLine()+":"+ctx.start.getCharPositionInLine()+"\n"));
		else{
			this.entry = tmp;
			this.callNestingLevel = env.getNestingLevel();
			
			for(Node arg : args)
				res.addAll(arg.checkSemantics(env));
		}
		return res;
	}
}

