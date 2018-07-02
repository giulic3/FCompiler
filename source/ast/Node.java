package ast;

import java.util.ArrayList;

import utils.*;

public interface Node {
   
  String toPrint(String indent);
  
  Node typeCheck();
  
  String codeGeneration();
  
  ArrayList<SemanticError> checkSemantics(Environment env);
  
}  