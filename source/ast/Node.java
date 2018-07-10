package ast;

import java.util.ArrayList;
import java.util.HashSet;

import utils.*;

public interface Node {
	
	String toPrint(String indent);
	
	Node typeCheck() throws Exception;
	
	String codeGeneration();
	
	HashSet<String> checkSemantics(Environment env);
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	String getID();
}