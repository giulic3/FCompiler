package ast;

import java.util.ArrayList;

import util.Environment;
import util.SemanticError;

public class IntNode implements Node {

  private Integer val;
  
  public IntNode (Integer n) {
    val=n;
  }
  
  public String toPrint(String s) {
    return s+"Int:" + Integer.toString(val) +"\n";  
  }
  
  public Node typeCheck() {
    return new IntTypeNode();
  } 
  
  @Override
 	public HashSet<String> checkSemantics(Environment env) {

 	  return new HashSet<String>();
 	}
  
  public String codeGeneration() {
	return "push "+val+"\n";
  }

}  