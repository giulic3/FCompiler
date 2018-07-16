package ast.types;

import ast.Node;
import utils.Environment;
import utils.SymbolTableEntry;

import java.util.HashSet;

public class NullType extends ClassType {
	
	public NullType(){
		super(null, null, null,null, null);
	}
	
	@Override
	public String toPrint(String indent) {
		return indent + "Null Type"; // TODO: to be updated
	}
	
	
	@Override
	public Node typeCheck() {
		// TODO: da implementare
		return this;
	}
	
	@Override
	public String codeGeneration() {
		// TODO: da implementare
		return null;
	}
	
	@Override
	public HashSet<String> checkSemantics(Environment env) {
		// TODO: non dovrebbe servire
		return new HashSet<>();
	}
	
	@Override
	public String getID() {
		return "Null Type";
	}
	
	
}
