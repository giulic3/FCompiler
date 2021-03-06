package ast;

import ast.types.*;
import org.antlr.v4.runtime.ParserRuleContext;
import utils.*;
import java.util.HashSet;

public class IntValNode implements Node {
	
	private int value;
	private ParserRuleContext ctx;
	
	/**
	 *
	 * Rappresenta i valori interi
	 *
	 * */
	public IntValNode(int n, ParserRuleContext ctx) {
		value = n;
		this.ctx = ctx;
	}
	
	public Node copyInstance() {
		ParserRuleContext ctx = new ParserRuleContext();
		ctx.copyFrom(this.ctx);
		return new IntValNode(this.value, ctx);
	}
	
	public String toPrint(String s) {
		return s + "Integer value: " + value;
	}
	
	public HashSet<String> checkSemantics(Environment env) {
		HashSet<String> res = new HashSet<>();
		
		if (value == Integer.MAX_VALUE)
			res.add("Integer value ±" + Integer.MAX_VALUE + " is reserved and can't be used at line " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine());
		
		return res;
	}
	
	public Node typeCheck() {
		return new IntType();
	}
	
	public String codeGeneration() {
		return  "push " + value + "\n";
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return null;
	}
}
