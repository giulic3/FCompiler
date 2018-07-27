package ast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import ast.types.ClassType;
import ast.types.FunType;
import ast.types.VoidType;
import org.antlr.v4.runtime.ParserRuleContext;
import utils.Environment;
import utils.Helpers;
import utils.SymbolTableEntry;
import utils.TypeCheckException;

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
		//env.setOffset(env.getOffset()-1);
		
		int offset = env.decreaseOffset();
		
		if (type instanceof FunType && ((FunType)type).getReturnType() instanceof ClassType) {
			Node rt = ((FunType)type).getReturnType();
			ClassType curType = (ClassType)rt;
			SymbolTableEntry classEntry = env.getClassEntry(curType.getID());
			((FunType)type).updateReturnType(classEntry.getType());
		}
		
		SymbolTableEntry entry = new SymbolTableEntry(env.getNestingLevel(), offset, type); //separo introducendo "entry"
		
		// TODO: aggiungere controlli su numero dei parametri e ridefinizione delle funzioni
		
		String funID = "Function$" + name;
		
		if(!env.getFunSecondCheck()) {
			if(hm.get(funID) != null) {
				//this.funEntry = hm.get(funID);
				res.add("Function " + name + " already declared at line: " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n");
			}
			else
				hm.put(funID, entry);
		} else {
			SymbolTableEntry existingEntry = hm.get(funID);
			existingEntry.setOffset(offset);
			this.funEntry=existingEntry;
		}
		
		env.pushScope();
		
		HashMap<String, SymbolTableEntry> funContentHM = env.getSymTable().get(env.getNestingLevel());
		
		ArrayList<Node> parTypes = new ArrayList<>();
		int paroffset = 1;
		
		// TODO: highly experimental
		int currentOffset = env.getOffset();
		env.setOffset(1);
		
		for (Node par : parList) {
			VarNode arg = (VarNode) par;
			parTypes.add(arg.getType());
			res.addAll(arg.checkSemantics(env));
			//SymbolTableEntry funEntry = new SymbolTableEntry(env.getNestingLevel(), paroffset++, arg.getType());
			
			//if (funContentHM.put(arg.getID(), funEntry) != null)
			//	res.add("Parameter name " + arg.getID() + " already declared at line: " + arg.getCtx().start.getLine() + ":" + arg.getCtx().start.getCharPositionInLine() + "\n");
		}
		env.setOffset(currentOffset);
		// TODO: end highly experimental
		
		this.funEntry = entry;
		
		if(!decList.isEmpty())
			env.setOffset(-2);
			
		for (Node dec : decList) {
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
		// check if the type of the last stms or exp in body is subtype of the function return type
		if (!Helpers.subtypeOf(body.get(body.size()-1).typeCheck(), ((FunType)type).getReturnType()))
			throw new TypeCheckException("Function Return", ctx.start.getLine(), ctx.start.getCharPositionInLine());

		return ((FunType)type).getReturnType();
	}

	public String codeGeneration() {
		// TODO: da controllare
		String decAssembly = "";
		String decPopAssembly = "";
		String parPopAssembly = "";
		String bodyAssembly = "";
		String funLabel = Helpers.newFuncLabel();
		
		FunType funcType = (FunType)type;
		String storeRetVal = funcType.getReturnType() instanceof VoidType ? "" : "srv\n";
		String loadRetVal = funcType.getReturnType() instanceof VoidType ? "" : "lrv\n";
		
		for (Node dec: decList) {
			decAssembly += dec.codeGeneration();
			decPopAssembly += "pop\n";
		}
		
		for (Node par: parList)
			parPopAssembly += "pop\n";
		
		for (Node n: body)
			bodyAssembly += n.codeGeneration();
		
		String funcCode = funLabel + ":\n" +
				"cfp\n" +
				"lra\n" +
				decAssembly +
				bodyAssembly +
				storeRetVal +
				decPopAssembly +
				"sra\n" +
				"pop\n" +
				parPopAssembly +
				"sfp\n" +
				loadRetVal +
				"lra\n" +
				"js\n";
		Helpers.appendFuncAssembly(funcCode);
		
		return "push " + funLabel + "\n";
	}
	
	public String getID() {
		return name;
	}

}