package ast;

import ast.types.IntType;
import utils.Environment;
import utils.Helpers;
;

import java.util.ArrayList;
import java.util.HashSet;

public class MinusNode implements Node {
	
	private Node value;
	
	public MinusNode(Node value) {
		this.value = value;
	}
	
	public String toPrint(String indent) {
		return indent + "Minus Node:\n" + value.toPrint(indent+"\t");
	}
	
	public Node typeCheck() throws Exception {
		// TODO: da implementare
		if(!Helpers.subtypeOf(new IntType(), value.typeCheck())){
			throw new Exception("minus on non Integer Type");
		}
		return new IntType();
	}
	
	public String codeGeneration() {
		// TODO: da implementare
		// push(-pop), si assume che value.codegen lasci il risultato sulla cima dello
		// stack, prendiamo questo valore, facciamo pop, mettiamo il meno davanti e
		// rifacciamo push. (il codice fenerato da value.codegen() deve essere eseguito prima
		// di quello di minus, quindi deve comparire prima nella stringa. Il risultato sarà codestring + minus.codegen).
		return value.codeGeneration();
		
	}
	
	public HashSet<String> checkSemantics(Environment env) {
		// TODO: da implementare
		return value.checkSemantics(env);
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return null;
	}
}
