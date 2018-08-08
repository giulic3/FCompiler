package ast;

import java.util.HashSet;
import ast.types.BoolType;
import ast.types.IntType;
import org.antlr.v4.runtime.ParserRuleContext;
import utils.Environment;
import utils.Helpers;
import utils.TypeCheckException;


public class GeqNode implements Node {

	private Node left;
	private Node right;
	private ParserRuleContext ctx;
	
	/**
	 *
	 * Gestisce gli operandi del confronto >=.
	 *
	 */
	public GeqNode (Node l, Node r, ParserRuleContext ctx) {
		left = l;
		right = r;
		this.ctx = ctx;
	}
	
	public Node copyInstance() {
		ParserRuleContext ctx = new ParserRuleContext();
		ctx.copyFrom(this.ctx);
		return new GeqNode(this.left.copyInstance(), this.right.copyInstance(), ctx);
	}

	public String toPrint(String s) {
		return  s + "Greater/Equal Node:\n" +
				left.toPrint(s + "\t") + "\n" +
				right.toPrint(s + "\t");
	}
	
	public HashSet<String> checkSemantics(Environment env) {
		//create the result
		HashSet<String> res = new HashSet<>();

		//check semantics in the left and in the right exp
		res.addAll(left.checkSemantics(env));
		res.addAll(right.checkSemantics(env));

		return res;
	}
	
	/**
	 *
	 * Il confronto avviene tra interi e suoi sottotipi, ad esempio i booleani.
	 *
	 * */
	public Node typeCheck() throws Exception {
		Node l = left.typeCheck();
		Node r = right.typeCheck();
		
		if (!(Helpers.subtypeOf(new IntType(),l) && Helpers.subtypeOf(new IntType(),r)))
			throw new TypeCheckException("Greater/Equal", ctx.start.getLine(), ctx.start.getCharPositionInLine());
		
		return new BoolType();
	}

	public String codeGeneration() {
		String geqLabel = Helpers.newLabel();
		String notGeqLabel = Helpers.newLabel();
		
		return  right.codeGeneration() +
				left.codeGeneration() +
				"bleq "+ geqLabel +"\n"+
				"push 0\n"+
				"b " + notGeqLabel + "\n" +
				geqLabel + ":\n"+
				"push 1\n" +
				notGeqLabel + ":\n";
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return null;
	}
}