package ast;

import utils.Environment;
import utils.SemanticError;

import java.util.ArrayList;

public class MinusNode implements Node {
	
	private Node value;
	
	public MinusNode(Node value) {
		this.value = value;
	}
	
	public String toPrint(String indent) {
		return indent + "Minus Node:\n" + value.toPrint(indent+"\t");
	}
	
	public Node typeCheck() {
		// TODO: da implementare
		return null;
	}
	
	public String codeGeneration() {
		// TODO: da implementare
		return null;
	}
	
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		// TODO: da implementare
		return null;
	}
	
}
