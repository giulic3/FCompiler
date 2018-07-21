package ast;

import ast.types.BoolType;
import org.antlr.v4.runtime.ParserRuleContext;
import utils.Environment;
import utils.Helpers;
import utils.TypeCheckException;

import java.util.HashSet;

public class NotNode implements Node {
	
	private Node value;
	private ParserRuleContext ctx;
	private String classID = null;
	
	public NotNode(Node value, ParserRuleContext ctx) {
		this.value = value;
		this.ctx = ctx;
	}
	
	public String toPrint(String indent) {
		return indent + "Not Node:\n" + value.toPrint(indent+"\t");
	}
	
	public HashSet<String> checkSemantics(Environment env) {
		// TODO: da controllare
		return value.checkSemantics(env);
	}
	
	public Node typeCheck() throws Exception {
		// TODO: da controllare
		
		if (!Helpers.subtypeOf(value.typeCheck(), new BoolType()))
			throw new TypeCheckException("Not", ctx.start.getLine(), ctx.start.getCharPositionInLine());
		
		return value.typeCheck();
	}
	
	public String codeGeneration() {
		String label = Helpers.newLabel();
		String exit = Helpers.newLabel();
		
		return  value.codeGeneration() +
				"push 1\n" +
				"beq " + label + "\n" +
				"push 1\n" +
				"b " + exit + "\n" +
				label + ":\n" +
				"push 0\n" +
				exit + ":\n";
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
