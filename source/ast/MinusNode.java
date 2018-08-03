package ast;

import ast.types.IntType;
import org.antlr.v4.runtime.ParserRuleContext;
import utils.Environment;
import utils.Helpers;
import utils.TypeCheckException;
import java.util.HashSet;

public class MinusNode implements Node {
	
	private Node value;
	private ParserRuleContext ctx;
	
	public MinusNode(Node value, ParserRuleContext ctx) {
		this.value = value;
		this.ctx = ctx;
	}
	
	public String toPrint(String indent) {
		return indent + "Minus Node:\n" + value.toPrint(indent+"\t");
	}
	
	public HashSet<String> checkSemantics(Environment env) {
		return value.checkSemantics(env);
	}
	
	public Node typeCheck() throws Exception {
		if(!Helpers.subtypeOf(new IntType(), value.typeCheck())){
			throw new TypeCheckException("Unary Minus", ctx.start.getLine(), ctx.start.getCharPositionInLine());
		}
		return new IntType();
	}
	
	public String codeGeneration() {
		// push(-pop), si assume che value.codegen lasci il risultato sulla cima dello
		// stack, prendiamo questo valore, facciamo pop, mettiamo il meno davanti e
		// rifacciamo push. (il codice fenerato da value.codegen() deve essere eseguito prima
		// di quello di minus, quindi deve comparire prima nella stringa. Il risultato sarà codestring + minus.codegen).
		String valCodeGen = value.codeGeneration();
		return "push -1\n" + valCodeGen + "mult\n";
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return null;
	}
}
