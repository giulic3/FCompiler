package ast;

import java.util.ArrayList;

import ast.types.BaseType;
import utils.*;

public interface Node {
   
  String toPrint(String indent);

  //  fa il type checking e ritorna:
  //  per una espressione, il suo tipo (oggetto BoolType o IntType)
  //  per una dichiarazione, "null"
  BaseType typeCheck();
  
  String codeGeneration();
  
  ArrayList<SemanticError> checkSemantics(Environment env);
  
}  