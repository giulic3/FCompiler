package ast;

import ast.types.IntType;
import org.antlr.v4.runtime.ParserRuleContext;
import utils.Environment;
import utils.Helpers;
import utils.TypeCheckException;
import java.util.HashSet;

public class PlusNode implements Node {
	
	private Node leftOperand;
	private Node rightOperand;
	private ParserRuleContext ctx;
	
	public PlusNode(Node l, Node r, ParserRuleContext ctx) {
		leftOperand = l;
		rightOperand = r;
		this.ctx = ctx;
	}
	
	// TODO: prova
	public Node copyInstance() {
		ParserRuleContext ctx = new ParserRuleContext();
		ctx.copyFrom(this.ctx);
		return new PlusNode(this.leftOperand.copyInstance(), this.rightOperand.copyInstance(), ctx);
	}
	
	public String toPrint(String s) {
		return s + "Plus Node:\n" + leftOperand.toPrint(s+"\t") + "\n" + rightOperand.toPrint(s+"\t");
	}
	
	public HashSet<String> checkSemantics(Environment env) {
		HashSet<String> res = new HashSet<>();
		
		//check semantics in the left and in the right exp
		res.addAll(leftOperand.checkSemantics(env));
		res.addAll(rightOperand.checkSemantics(env));
		
		return res;
	}
	
	public Node typeCheck() throws Exception {
		if (! ( Helpers.subtypeOf(leftOperand.typeCheck(), new IntType()) &&
				Helpers.subtypeOf(rightOperand.typeCheck(), new IntType()) ) ) {
			throw new TypeCheckException("Plus", ctx.start.getLine(), ctx.start.getCharPositionInLine());
		}
		return new IntType();
	}
	
	public String codeGeneration() {
		return leftOperand.codeGeneration() + rightOperand.codeGeneration() + "add\n";
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return null;
	}
}
