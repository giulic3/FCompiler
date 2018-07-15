package ast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import ast.types.ClassType;
import ast.types.FunType;
import org.antlr.v4.runtime.ParserRuleContext;
import utils.Environment;
;
import utils.SymbolTableEntry;

public class FunDecNode implements Node {

	protected String name;
	protected Node type;

	protected ArrayList<Node> parList;
	protected ArrayList<Node> decList;
	protected ArrayList<Node> body;
	protected ParserRuleContext ctx;

	protected SymbolTableEntry funEntry;

	public FunDecNode (String name, Node type, ArrayList<Node> decList, ArrayList<Node> parList, ArrayList<Node> body, ParserRuleContext ctx) {
		this.ctx = ctx;
		this.name = name;
		this.type = type;
		this.decList = decList;
		this.parList = parList;
		this.body = body;
	}

	public SymbolTableEntry getSTEntry(){

		return this.funEntry;
	}


	@Override
	public HashSet<String> checkSemantics(Environment env) {

		//create result list
		HashSet<String> res = new HashSet<>();
		
		HashMap<String, SymbolTableEntry> hm = env.getSymTable().get(env.getNestingLevel());
		env.setOffset(env.getOffset()-1);
		SymbolTableEntry entry = new SymbolTableEntry(env.getNestingLevel(),env.getOffset(),type); //separo introducendo "entry"
		
		// TODO: aggiungere controlli su numero dei parametri e ridefinizione delle funzioni
		
		String funID = "Function$" + name;
		
		if(!env.getFunSecondCheck()) {
			if (hm.put(funID, entry) != null)
				res.add("Function " + name + " already declared at line: " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n");
		}
		
		env.pushScope();
		
		HashMap<String, SymbolTableEntry> funContentHM = env.getSymTable().get(env.getNestingLevel());
		
		ArrayList<Node> parTypes = new ArrayList<>();
		int paroffset = 1;
		
		for (Node par : parList) {
			VarNode arg = (VarNode) par;
			parTypes.add(arg.getType());
			SymbolTableEntry funEntry = new SymbolTableEntry(env.getNestingLevel(), paroffset++, arg.getType());
			
			if (funContentHM.put(arg.getId(), funEntry) != null)
				res.add("Parameter name " + arg.getId() + " already declared at line: " + arg.getCtx().start.getLine() + ":" + arg.getCtx().start.getCharPositionInLine() + "\n");
		}

		FunType funType = new FunType(parTypes, type);
		entry.setType(funType);
		this.funEntry = entry;



		for (Node dec : decList) {
			env.setOffset(env.getOffset() - 2);
			res.addAll(dec.checkSemantics(env));
		}
		
		if (env.getFunSecondCheck())
			for (Node b : body) {
				res.addAll(b.checkSemantics(env));
			}
		
		env.popScope();
		
		return res;
	}

	public String toPrint(String s) {

		String parlstr = "";
		String declstr = "";

		if (parList !=null && !parList.isEmpty()) {
			for (Node par : parList)
				parlstr += "\n" + par.toPrint(s + "\t\t");
		}

		if (decList !=null && !decList.isEmpty()) {
			declstr = "\n"+s+"\tFun Decs:";
			for (Node dec : decList)
				declstr += "\n" + dec.toPrint(s + "\t\t");
		}

		return s+"Fun Dec Node: " + name + " : " +((this.funEntry != null) ? this.funEntry.getType().toPrint(s+"\t"): "")
				+parlstr
				+declstr;
				//+body.toPrint(s+"  ") ;
	}

	//valore di ritorno non utilizzato
	public Node typeCheck () throws Exception {
		for(Node p : parList){
			p.typeCheck();
		}
		for(Node d : decList){
			d.typeCheck();
		}
		for(Node b : body){
			b.typeCheck();
		}
		return type;
	}

	public String codeGeneration() {
		return "";
	}
	
	public String getID() {
		return name;
	}

}