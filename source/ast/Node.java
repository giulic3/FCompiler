package ast;

import java.util.ArrayList;

import utils.*;

public interface Node {
	
	String toPrint(String indent);
	
	Node typeCheck() throws Exception;
	
	String codeGeneration();
	
	ArrayList<SemanticError> checkSemantics(Environment env);
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	String getID();
}