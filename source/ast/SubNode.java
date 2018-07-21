package ast;

import ast.types.IntType;
import org.antlr.v4.runtime.ParserRuleContext;
import utils.Environment;
import utils.Helpers;
import utils.TypeCheckException;

import java.util.HashSet;

public class SubNode implements Node {
	
	private Node leftOperand;
	private Node rightOperand;
	private ParserRuleContext ctx;
	private String classID = null;
	
	
	public SubNode(Node l, Node r, ParserRuleContext ctx) {
		leftOperand = l;
		rightOperand = r;
		this.ctx = ctx;
	}
	
	public String toPrint(String s) {
		return s + "Sub Node:\n" + leftOperand.toPrint(s+"\t") + "\n" + rightOperand.toPrint(s+"\t");
	}
	
	public HashSet<String> checkSemantics(Environment env) {
		HashSet<String> res = new HashSet<String>();
		
		//check semantics in the left and in the right exp
		leftOperand.setClassID(classID);
		rightOperand.setClassID(classID);
		res.addAll(leftOperand.checkSemantics(env));
		res.addAll(rightOperand.checkSemantics(env));
		
		return res;
	}
	
	public Node typeCheck() throws Exception {
		if (! ( Helpers.subtypeOf(leftOperand.typeCheck(), new IntType()) &&
				Helpers.subtypeOf(rightOperand.typeCheck(), new IntType()) ) ) {
			throw new TypeCheckException("Sub", ctx.start.getLine(), ctx.start.getCharPositionInLine());
		}
		return new IntType();
	}
	
	public String codeGeneration() {
		// TODO: da controllare
		return leftOperand.codeGeneration() + rightOperand.codeGeneration() + "sub\n";
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return null;
	}
	
	public void setClassID(String id) {
		this.classID = id;
	}
}
