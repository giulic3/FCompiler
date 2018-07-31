package ast;

import java.util.HashSet;
import ast.types.BoolType;
import org.antlr.v4.runtime.ParserRuleContext;
import utils.Environment;
import utils.Helpers;
import utils.TypeCheckException;

public class OrNode implements Node {

	private Node left;
	private Node right;
	private ParserRuleContext ctx;
	
	
	public OrNode (Node l, Node r, ParserRuleContext ctx) {
		left = l;
		right = r;
		this.ctx = ctx;
	}

	public String toPrint(String s) {
		return s + "OR Node:\n" + left.toPrint(s+"\t") + "\n"
				+ right.toPrint(s+"\t") ;
	}

	public HashSet<String> checkSemantics(Environment env) {
		//create the result
		HashSet<String> res = new HashSet<String>();

		//check semantics in the left and in the right exp

		res.addAll(left.checkSemantics(env));
		res.addAll(right.checkSemantics(env));

		return res;
	}

	public Node typeCheck() throws Exception {
		if (!(Helpers.subtypeOf(new BoolType(), left.typeCheck()) && Helpers.subtypeOf(new BoolType(),right.typeCheck())))
			throw new TypeCheckException("Or", ctx.start.getLine(), ctx.start.getCharPositionInLine());
		
		return new BoolType();
	}

	public String codeGeneration() {
		String exitLabel = Helpers.newLabel();
		String atLeastOneTrue = Helpers.newLabel();
		
		return  left.codeGeneration() +
				"push 1\n" +
				"beq " + atLeastOneTrue + "\n" +
				right.codeGeneration() +
				"push 1\n" +
				"beq " + atLeastOneTrue + "\n" +
				"push 0\n" +
				"b " + exitLabel + "\n" +
				atLeastOneTrue + ":\n" +
				"push 1\n" +
				exitLabel + ":\n";
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return null;
	}
}