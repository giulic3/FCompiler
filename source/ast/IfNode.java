package ast;


import ast.types.BoolType;
import ast.types.VoidType;
import org.antlr.v4.runtime.ParserRuleContext;
import utils.Environment;
import utils.Helpers;
import utils.TypeCheckException;

import java.util.ArrayList;
import java.util.HashSet;

/* used both for ifExp and ifStm*/
public class IfNode implements Node {

	private Node cond;
	private Node th;
	private Node el;
	private ArrayList<Node> thStms;
	private ArrayList<Node> elStms;
	private ParserRuleContext ctx;

	// IfExp
	public IfNode(Node cond, Node th, Node el, ParserRuleContext ctx) {
		this.cond = cond;
		this.th = th;
		this.el = el;
		this.thStms = new ArrayList<>();
		this.elStms = new ArrayList<>();
		this.ctx = ctx;
	}

	/* ifStm */
	public IfNode(Node cond, ArrayList<Node> thStms, ParserRuleContext ctx) {
		this.cond = cond;
		this.th = null;
		this.el = null;
		this.thStms = thStms;
		this.elStms = new ArrayList<>();
		this.ctx = ctx;
	}

	public IfNode(Node cond, ArrayList<Node> thStms, ArrayList<Node> elStms, ParserRuleContext ctx) {
		this.cond = cond;
		this.th = null;
		this.el = null;
		this.thStms = thStms;
		this.elStms = elStms;
		this.ctx = ctx;
	}
	
	// TODO: prova
	public Node copyInstance() {
		ParserRuleContext ctx = new ParserRuleContext();
		ctx.copyFrom(this.ctx);
		
		// IfExp
		if (this.th != null) {
			return new IfNode(this.cond.copyInstance(), this.th.copyInstance(), this.el.copyInstance(), ctx);
		}
		// IfStms
		else {
			ArrayList<Node> thStmsCopy = new ArrayList<>(this.thStms);
			for (Node n: this.thStms)
				thStmsCopy.add(n.copyInstance());
			ArrayList<Node> elStmsCopy = new ArrayList<>(this.elStms);
			for (Node n: this.elStms)
				elStmsCopy.add(n.copyInstance());
			return new IfNode(this.cond.copyInstance(), thStmsCopy, elStmsCopy, ctx);
		}
	}
	
	@Override
	public String toPrint(String s) {

		/* ifExp */
		if (th != null) {
			String ifExp = s + "If Exp Node:\n" + s + "\tCond:\n" +
					cond.toPrint(s + "\t\t") + "\n" +
					s + "\tThen Branch:\n" + th.toPrint(s + "\t\t");
			ifExp += "\n" + s + "\tElse Branch:\n" + el.toPrint(s + "\t\t");

			return ifExp;
		}
		/* ifStm */
		else {
			String thStmsString = "";
			String elStmsString = "";

			for (Node stm : thStms)
				thStmsString += "\n" + stm.toPrint(s+"\t\t");

			String ifStm = s + "If Stms Node:\n" + s + "\tCond:\n" +
					cond.toPrint(s+"\t\t") + "\n" +
					s + "\tThen Branch:" + thStmsString;
			
			for (Node stm : elStms)
				elStmsString += "\n" + stm.toPrint(s + "\t\t");
			if (!elStms.isEmpty()) ifStm += "\n" + s + "\tElse Branch:" + elStmsString;

			return ifStm;
		}
	}
	
	@Override
	public HashSet<String> checkSemantics(Environment env) {
		//create the result
		HashSet<String> res = new HashSet<String>();
		
		res.addAll(cond.checkSemantics(env));
		
		// IfExp
		if (th != null) {
			res.addAll(th.checkSemantics(env));
			res.addAll(el.checkSemantics(env));
		}
		// IfStms
		else {
			for (Node stm: thStms)
				res.addAll(stm.checkSemantics(env));
			
			for (Node stm: elStms)
				res.addAll(stm.checkSemantics(env));
		}
		
		return res;
	}
	
	@Override
	public Node typeCheck() throws Exception {
		
		if(!Helpers.subtypeOf(cond.typeCheck(), new BoolType())){
			throw new TypeCheckException("If (condition)", ctx.start.getLine(), ctx.start.getCharPositionInLine());
		}
		
		// IfExp
		if (th != null) {
			if(Helpers.subtypeOf(th.typeCheck(),el.typeCheck())){
				return el.typeCheck();
			}
			if(Helpers.subtypeOf(el.typeCheck(),th.typeCheck())){
				return th.typeCheck();
			}
			throw new TypeCheckException("If (then/else type)", ctx.start.getLine(), ctx.start.getCharPositionInLine());
		}
		// IfStms
		else {
			for(Node ths : thStms)
				if(!Helpers.subtypeOf(ths.typeCheck(), new VoidType()))
					throw new TypeCheckException("If (non void statement)", ctx.start.getLine(), ctx.start.getCharPositionInLine());
			
			for(Node els : elStms)
				if(!Helpers.subtypeOf(els.typeCheck(), new VoidType()))
					throw new TypeCheckException("If (non void statement)", ctx.start.getLine(), ctx.start.getCharPositionInLine());
					
			return new VoidType();
		}
	}
	
	@Override
	public String codeGeneration() {
		String trueBranch = Helpers.newLabel();
		String exitBranch = Helpers.newLabel();
		
		String thenCode = "";
		String elseCode = "";
		
		// Exp
		if (this.th != null) {
			thenCode += this.th.codeGeneration();
			elseCode += (this.el != null) ? this.el.codeGeneration() : "";
		}
		// Stms
		else {
			for (Node thStm: this.thStms)
				thenCode += thStm.codeGeneration();
			
			for (Node elStm: this.elStms)
				elseCode += elStm.codeGeneration();
		}
		
		return  cond.codeGeneration() +
				"push 1\n" +
				"beq " + trueBranch + "\n" +
				elseCode +
				"b " + exitBranch + "\n" +
				trueBranch + ":\n" +
				thenCode +
				exitBranch + ":\n";
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return null;
	}
}