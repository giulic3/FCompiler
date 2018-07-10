package ast;

import java.util.ArrayList;

import util.Environment;
import util.SemanticError;

public interface Node {
   
  String toPrint(String indent);

  // fa il type checking e ritorna:
  //  per una espressione, il suo tipo (oggetto BoolType o IntType)
  //  per una dichiarazione, "null"
  Node typeCheck();
  
  String codeGeneration();
  
  HashSet<String> checkSemantics(Environment env);
  
}  