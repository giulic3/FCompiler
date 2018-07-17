package ast;

import java.util.HashSet;
import ast.types.BoolType;
import org.antlr.v4.runtime.ParserRuleContext;
import utils.Environment;
import utils.Helpers;
import utils.TypeCheckException;

public class EqNode implements Node {

	private Node left;
	private Node right;
	private ParserRuleContext ctx;
	
	/**
	 *
	 * Gestisce gli operandi dell'uguaglianza.
	 *
	 */
	public EqNode (Node l, Node r, ParserRuleContext ctx) {
		left = l;
		right = r;
		this.ctx = ctx;
	}

	public String toPrint(String s) {
		return s + "Equal Node:\n" + left.toPrint(s+"\t") + "\n"
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

	/**
	 *
	 * L'uguaglianza può essere eseguita solo su booleani e sui sottotipi.
	 *
	 * */
	public Node typeCheck() throws Exception {
		
		Node l = left.typeCheck();
		Node r = right.typeCheck();
		if (! ( Helpers.subtypeOf(l,r) || Helpers.subtypeOf(r,l))) {
			throw new TypeCheckException("Equal Check", ctx.start.getLine(), ctx.start.getCharPositionInLine());
		}
		return new BoolType();
	}

	public String codeGeneration() {
		
		String l1 = Helpers.newLabel();
		String l2 = Helpers.newLabel();
		return left.codeGeneration()+
				right.codeGeneration()+
				"beq "+ l1 +"\n"+
				"push 0\n"+
				"b " + l2 + "\n" +
				l1 + ":\n"+
				"push 1\n" +
				l2 + ":\n";
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return null;
	}
}