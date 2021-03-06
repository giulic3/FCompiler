package ast;

import ast.types.*;
import org.antlr.v4.runtime.ParserRuleContext;
import utils.*;
import java.util.HashSet;

public class DivNode implements Node {

	private Node leftOperand;
	private Node rightOperand;
	private ParserRuleContext ctx;
	
	/**
	 *
	 * Gestisce gli operandi della Divisione.
	 *
	 */
	public DivNode (Node l, Node r, ParserRuleContext ctx) {
		leftOperand = l;
		rightOperand = r;
		this.ctx = ctx;
	}
	
	public Node copyInstance() {
		ParserRuleContext ctx = new ParserRuleContext();
		ctx.copyFrom(this.ctx);
		return new DivNode(this.leftOperand.copyInstance(), this.rightOperand.copyInstance(), ctx);
	}
	
	public String toPrint(String s) {
		return  s + "Div Node\n" +
				leftOperand.toPrint(s + "\t") + "\n" +
				rightOperand.toPrint(s + "\t") ;
	}
	
	public HashSet<String> checkSemantics(Environment env) {

		HashSet<String> res = new HashSet<>();

		res.addAll(leftOperand.checkSemantics(env));
		res.addAll(rightOperand.checkSemantics(env));

		return res;
	}
	
	/**
	 *
	 * La divisione viene effettuata solo su tipi interi e suoi sottotipi, ad esempio i booleani.
	 *
	 * */
	public Node typeCheck() throws Exception {
		if (!(Helpers.subtypeOf(leftOperand.typeCheck(), new IntType()) && Helpers.subtypeOf(rightOperand.typeCheck(),new IntType())))
			throw new TypeCheckException("Division", ctx.start.getLine(), ctx.start.getCharPositionInLine());
		
		return new IntType();
	}

	public String codeGeneration() {
		return leftOperand.codeGeneration() + rightOperand.codeGeneration() + "div\n";
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return null;
	}
}
