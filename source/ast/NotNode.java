package ast;

import ast.types.BoolType;
import utils.Environment;
import utils.Helpers;
import java.util.HashSet;

public class NotNode implements Node {
	
	private Node value;
	
	public NotNode(Node value) {
		this.value = value;
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
			throw new Exception("'not' used on Non Bool type");
		return value.typeCheck();
	}
	
	public String codeGeneration() {
		
		String lab = Helpers.newLabel();
		String pass = Helpers.newLabel();
		return value.codeGeneration()+
				"push 1\n"+
				"beq" + lab + "\n"+
				"push 1\n"+
				"b " + pass + "\n"+
				lab+":\n"+
				"push 0"+
				pass+":\n";
	}
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return null;
	}
}
