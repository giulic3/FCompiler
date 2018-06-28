grammar FOOL;

@lexer::members {
   //there is a much better way to do this, check the ANTLR guide
   //I will leave it like this for now just because it is quick
   //but it doesn't work well
   public int lexicalErrors=0;
}

/*------------------------------------------------------------------
 * PARSER RULES
 *------------------------------------------------------------------*/
  
prog : (block SEMIC)+ ;

block  : exp                  #singleExp
       | let exp              #letInExp
       | classdec             #classDecBlock // sembra che ad ANTLR non piacciono label che hanno lo stesso nome di una regola del parser
       ;

/* una classe può avere o 0 (no parentesi tonde) o più campi, ma ha almeno un metodo,
 ogni dichiarazione di f è seguita da un ;
 */
classdec : CLASS ID ( SLPAR EXTENDS ID SRPAR )? (LPAR (varasm SEMIC)+ RPAR)? CLPAR (fun SEMIC)+ CRPAR ;

let    : LET (dec SEMIC)+ IN ;

vardec  : type ID ;

varasm  : vardec ASM exp ;

fun    : type ID LPAR ( vardec ( COMMA vardec)* )? RPAR (let)? exp ;

dec   : varasm           #varDecAssignment
      | fun              #funDeclaration
      ;
   
type   : INT  
        | BOOL 
      ;  
    
exp    :  ('-')? left=term ((PLUS | MINUS) right=exp)?
      ;
   
term   : left=factor ((TIMES | DIV) right=term)?
      ;

factor : left=atom ((EQ | LEQ | GEQ | OR | AND) right=atom)?
      ;
/* this works fot both integers and bool */
atom : (NOT)? operand=value ;

/* una funzione senza param viene chiamata senza () */
value  :  INTEGER                          #intVal
      | ( TRUE | FALSE )                   #boolVal
      | NULL                               #nullVal
      | LPAR exp RPAR                      #baseExp
      | IF cond=exp THEN CLPAR thenBranch=exp CRPAR ELSE CLPAR elseBranch=exp CRPAR  #ifExp
      | ID                                             #varExp
      | ID ( LPAR (exp (COMMA exp)* )? RPAR )?         #funExp
      | ID DOT ID ( LPAR (exp (COMMA exp)* )? RPAR )?  #methodExp
      | NEW ID (LPAR (exp (COMMA exp)* )? RPAR)?       #newExp
      ;

stm : ID ASM exp SEMIC #varStmAssignment
    | IF cond=exp THEN CLPAR thenBranch=stms CRPAR ELSE CLPAR elseBranch=stms CRPAR  #ifStm
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
TRUE   : 'true' ;
FALSE  : 'false' ;
LPAR   : '(' ;
RPAR   : ')' ;
SLPAR  : '[' ;
SRPAR  : ']' ;
CLPAR  : '{' ;
CRPAR  : '}' ;
IF     : 'if' ;
THEN   : 'then' ;
ELSE   : 'else' ;
//PRINT : 'print' ; 
LET    : 'let' ;
IN     : 'in' ;
VAR    : 'var' ;
FUN    : 'fun' ;
INT    : 'int' ;
BOOL   : 'bool' ;
VOID   : 'void' ;
CLASS  : 'class' ;
EXTENDS : 'extends' ;
NULL : 'null' | 'NULL' ;
NEW : 'new' ;
DOT : '.' ;

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


 //VERY SIMPLISTIC ERROR CHECK FOR THE LEXING PROCESS, THE OUTPUT GOES DIRECTLY TO THE TERMINAL
 //THIS IS WRONG!!!!

 /*
 this moves lexer errors to the parser:
 https://stackoverflow.com/questions/18782388/antlr4-lexer-error-reporting-length-of-offending-characters
 to handle errors:
 https://stackoverflow.com/questions/39533809/antlr4-how-to-detect-unrecognized-token-and-given-sentence-is-invalid
 ANTLR Reference guide, chapter 9
 */
ERR     : . { System.out.println("Invalid char: "+ getText()); lexicalErrors++; } -> channel(HIDDEN); 
