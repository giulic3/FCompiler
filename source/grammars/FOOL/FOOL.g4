grammar FOOL;

@header {
package grammars.FOOL;
}

/*------------------------------------------------------------------
 * PARSER RULES
 *------------------------------------------------------------------*/
prog : (block SEMIC)+  ; // il parser riconosce solo un sottoinsieme dell'input senza dare errori se la parte che segue è sbagliata

block  : let stms             #letInStms
       | classdec             #classDecBlock // sembra che ad ANTLR non piacciono label che hanno lo stesso nome di una regola del parser
       ;

/* una classe può avere o 0 (no parentesi tonde) o più campi, ma ha sempre almeno un metodo,
 ogni dichiarazione di f è seguita da un ;
 */
classdec : CLASS className=ID ( EXTENDS superName=ID )? (LPAR varasm (COMMA varasm)* RPAR)? CLPAR (fundec SEMIC)+ CRPAR ;

let    : LET (dec SEMIC)+ IN ;

/* le variabili vanno inizializzate alla dichiarazione */

vardec  : type ID ;

varasm  : vardec ASM exp ;

fundec    : type ID LPAR ( vardec ( COMMA vardec)* )? RPAR (LET (varasm SEMIC)+ IN )? (stms | ((stms)? RETURN exp));

dec   : varasm           #varDecAssignment
      | fundec           #funDeclaration
      ;
   
type   : INT  
       | BOOL
       | VOID
       | ID
      ;  
    
exp   : left=operand (operator=(EQ | LEQ | GEQ ) right=exp)?
      ;

operand:  left=term (operator=(PLUS | MINUS) right=operand)?
    ;

   
term  : left=factor (operator=(TIMES | DIV) right=term)?
      ;

factor : left=atom (operator=( OR | AND) right=factor)?
      ;
/* this works fot both integers and bool */
atom : (NOT)? (MINUS)? val=value ;

value  :  INTEGER                          #intVal
      | BOOLVAL                            #boolVal
      | NULL                               #nullVal
      | LPAR exp RPAR                      #baseExp
      | IF LPAR cond=exp RPAR THEN CLPAR thenBranch=exp CRPAR ELSE CLPAR elseBranch=exp CRPAR  #ifExp
      | var     #varExp
      | ID (LPAR (exp (COMMA exp)* )? RPAR )         #funExp
      | object=var DOT memberName=ID ( LPAR (exp (COMMA exp)* )? RPAR )?  #methodExp
      | NEW className=ID (LPAR RPAR)       #newExp
      ;

var: ID;

stm : (var | object=var DOT fieldName=ID) ASM exp #varStmAssignment
    | IF cond=exp THEN CLPAR thenBranch=stms CRPAR (ELSE CLPAR elseBranch=stms CRPAR)?  #ifStm
    | object=var DOT memberName=ID ( LPAR (exp (COMMA exp)* )? RPAR )?  #methodStm
    | PRINT LPAR exp (COMMA exp)* RPAR  #printStm
    | ID (LPAR (exp (COMMA exp)* )? RPAR )         #funStm
    ;

stms : ( stm )+  ;

/*------------------------------------------------------------------
 * LEXER RULES
 *------------------------------------------------------------------*/
SEMIC  : ';' ;
COLON  : ':' ;
COMMA  : ',' ;
EQ     : '==' ;
LEQ    : '<=' ;
GEQ    : '>=' ;
OR     : '||' ;
AND    : '&&' ;
NOT    : 'not' ;
ASM    : '=' ;
PLUS   : '+' ;
MINUS  : '-' ;
TIMES  : '*' ;
DIV    : '/' ;
BOOLVAL: (TRUE|FALSE);
TRUE   : 'true' ;
FALSE  : 'false' ;
LPAR   : '(' ;
RPAR   : ')' ;
CLPAR  : '{' ;
CRPAR  : '}' ;
IF     : 'if' ;
THEN   : 'then' ;
ELSE   : 'else' ;
PRINT : 'print' ;
LET    : 'let' ;
IN     : 'in' ;
INT    : 'int' ;
BOOL   : 'bool' ;
VOID   : 'void' ;
CLASS  : 'class' ;
EXTENDS : 'extends' ;
NULL : 'null' | 'NULL' ;
NEW : 'new' ;
DOT : '.' ;
RETURN: 'return';

//Numbers
fragment DIGIT : '0'..'9';    
INTEGER       : DIGIT+;

//IDs
fragment CHAR  : 'a'..'z' |'A'..'Z' ;
ID              : CHAR (CHAR | DIGIT)* ;

//ESCAPED SEQUENCES
WS              : (' '|'\t'|'\n'|'\r')-> skip;
LINECOMENTS    : '//' (~('\n'|'\r'))* -> skip;
BLOCKCOMENTS    : '/*'( ~('/'|'*')|'/'~'*'|'*'~'/'|BLOCKCOMENTS)* '*/' -> skip;
