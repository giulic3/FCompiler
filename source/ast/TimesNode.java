package ast;

import ast.types.*;
import org.antlr.v4.runtime.ParserRuleContext;
import utils.*;
import java.util.HashSet;

public class TimesNode implements Node {

	private Node leftOperand;
	private Node rightOperand;
	private ParserRuleContext ctx;
	
	
	public TimesNode (Node l, Node r, ParserRuleContext ctx) {
		leftOperand = l;
		rightOperand = r;
		this.ctx = ctx;
	}
	
	public Node copyInstance() {
		ParserRuleContext ctx = new ParserRuleContext();
		ctx.copyFrom(this.ctx);
		return new TimesNode(this.leftOperand.copyInstance(), this.rightOperand.copyInstance(), ctx);
	}
	
	public String toPrint(String s) {
		return  s + "Times Node\n" +
				leftOperand.toPrint(s + "\t") + "\n" +
				rightOperand.toPrint(s + "\t");
	}

	public HashSet<String> checkSemantics(Environment env) {
		HashSet<String> res = new HashSet<>();

		res.addAll(leftOperand.checkSemantics(env));
		res.addAll(rightOperand.checkSemantics(env));

		return res;
	}
	
	public Node typeCheck() throws Exception {
		if (! (Helpers.subtypeOf(leftOperand.typeCheck(), new IntType()) && Helpers.subtypeOf(rightOperand.typeCheck(), new IntType()) ))
			throw new TypeCheckException("Times", ctx.start.getLine(), ctx.start.getCharPositionInLine());
		
		return new IntType();
	}

	public String codeGeneration() {
		return leftOperand.codeGeneration() + rightOperand.codeGeneration() + "mult\n";
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return null;
	}
}
