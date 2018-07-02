package ast;

import java.util.ArrayList;

import ast.types.BaseType;
import utils.*;

public interface Node {
   
  String toPrint(String indent);
  
  BaseType typeCheck();
  
  String codeGeneration();
  
  ArrayList<SemanticError> checkSemantics(Environment env);
  
}  