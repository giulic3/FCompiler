package ast;

import ast.types.VoidType;
import org.antlr.v4.runtime.ParserRuleContext;
import utils.Environment;
import utils.TypeCheckException;

import java.util.ArrayList;
import java.util.HashSet;

public class PrintNode implements Node {
	
	private ArrayList<Node> exps;
	private ParserRuleContext ctx;
	
	public PrintNode(ArrayList<Node> exps, ParserRuleContext ctx) {
		this.exps = exps;
		this.ctx = ctx;
	}
	
	public Node copyInstance() {
		ArrayList<Node> expsCopy = new ArrayList<>(this.exps);
		for (Node n: this.exps)
			expsCopy.add(n.copyInstance());
		ParserRuleContext ctx = new ParserRuleContext();
		ctx.copyFrom(this.ctx);
		return new PrintNode(expsCopy, ctx);
	}
	
	public String toPrint(String indent) {
		StringBuilder printMsg = new StringBuilder();
		printMsg.append(indent).append("Print Node:");
		
		for (Node e: this.exps)
			printMsg.append("\n").append(e.toPrint(indent + "\t\t"));
		
		return printMsg.toString();
	}
	
	public HashSet<String> checkSemantics(Environment env) {
		HashSet<String> res = new HashSet<>();
		
		for (Node exp: exps)
			res.addAll(exp.checkSemantics(env));
		
		return res;
	}
	
	public Node typeCheck() throws Exception {
		for (Node n: exps)
			if (n.typeCheck() instanceof VoidType)
				throw new TypeCheckException("Print (void exp not allowed)", ctx.start.getLine(), ctx.start.getCharPositionInLine());
		
		return new VoidType();
	}
	
	public String codeGeneration() {
		StringBuilder res = new StringBuilder();
		
		for (Node e: exps)
			res.append(e.codeGeneration()).append("print\n");
		
		return res.toString();
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return null;
	}
}
