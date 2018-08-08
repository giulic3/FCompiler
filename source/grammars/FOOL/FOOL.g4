grammar FOOL;

@header {
package grammars.FOOL;
}

/*------------------------------------------------------------------
 * PARSER RULES
 *------------------------------------------------------------------*/
prog        : (block SEMIC)+  ;

// Each block of code must be followed by a semicolon
block       : let stms      #letInStms
            | classdec      #classDecBlock
            ;

// A class can have 0 (no round brackets) or many fields (round brackets needed), and at least one method, followed by a semicolon
classdec    : CLASS className=ID ( EXTENDS superName=ID )? (LPAR varasm (COMMA varasm)* RPAR)? CLPAR (fundec SEMIC)+ CRPAR ;

let         : LET (dec SEMIC)+ IN ;

vardec      : type ID ;

varasm      : vardec ASM exp ;

// A function declaration can have 0 or more inner declarations (LET IN block); its body can be composed of
// - statements only (at least one)
// - return expression only
// - some statements followed by a final return expression
fundec      : type ID LPAR ( vardec ( COMMA vardec)* )? RPAR (LET (varasm SEMIC)+ IN )? (stms | ((stms)? RETURN exp));

// Variables in declarations must be initialized
dec         : varasm        #varDecAssignment
            | fundec        #funDeclaration
            ;
   
type        : INT
            | BOOL
            | VOID
            | ID
            ;
    
exp         : left=operand (operator=( EQ | LEQ | GEQ ) right=exp)? ;

operand     : left=term (operator=( PLUS | MINUS ) right=operand)? ;

term        : left=factor (operator=( TIMES | DIV ) right=term)? ;

factor      : left=atom (operator=( OR | AND ) right=factor)? ;

atom        : (NOT)? (MINUS)? val=value ;

value       : INTEGER                                                                                   #intVal
            | BOOLVAL                                                                                   #boolVal
            | NULL                                                                                      #nullVal
            | LPAR exp RPAR                                                                             #baseExp
            | IF LPAR cond=exp RPAR THEN CLPAR thenBranch=exp CRPAR ELSE CLPAR elseBranch=exp CRPAR     #ifExp
            | var                                                                                       #varExp
            | ID (LPAR (exp (COMMA exp)* )? RPAR )                                                      #funExp
            | object=var DOT memberName=ID ( LPAR (exp (COMMA exp)* )? RPAR )?                          #methodExp
            | NEW className=ID (LPAR RPAR)                                                              #newExp
            ;

var         : ID;

// If Statement has optional else branch
stm         : (var | object=var DOT fieldName=ID) ASM exp                                           #varStmAssignment
            | IF cond=exp THEN CLPAR thenBranch=stms CRPAR (ELSE CLPAR elseBranch=stms CRPAR)?      #ifStm
            | object=var DOT memberName=ID ( LPAR (exp (COMMA exp)* )? RPAR )?                      #methodStm
            | PRINT LPAR exp (COMMA exp)* RPAR                                                      #printStm
            | ID ( LPAR (exp (COMMA exp)* )? RPAR )                                                 #funStm
            ;

stms        : ( stm )+ ;

/*------------------------------------------------------------------
 * LEXER RULES
 *------------------------------------------------------------------*/
SEMIC   : ';' ;
COLON   : ':' ;
COMMA   : ',' ;
EQ      : '==' ;
LEQ     : '<=' ;
GEQ     : '>=' ;
OR      : '||' ;
AND     : '&&' ;
NOT     : 'not' ;
ASM     : '=' ;
PLUS    : '+' ;
MINUS   : '-' ;
TIMES   : '*' ;
DIV     : '/' ;
BOOLVAL : (TRUE|FALSE);
TRUE    : 'true' ;
FALSE   : 'false' ;
LPAR    : '(' ;
RPAR    : ')' ;
CLPAR   : '{' ;
CRPAR   : '}' ;
IF      : 'if' ;
THEN    : 'then' ;
ELSE    : 'else' ;
PRINT   : 'print' ;
LET     : 'let' ;
IN      : 'in' ;
INT     : 'int' ;
BOOL    : 'bool' ;
VOID    : 'void' ;
CLASS   : 'class' ;
EXTENDS : 'extends' ;
NULL    : 'null' | 'NULL' ;
NEW     : 'new' ;
DOT     : '.' ;
RETURN  : 'return';

//Numbers
fragment DIGIT  : '0'..'9';
INTEGER         : DIGIT+;

//IDs
fragment CHAR   : 'a'..'z' |'A'..'Z' ;
ID              : CHAR (CHAR | DIGIT)* ;

//ESCAPED SEQUENCES
WS              : (' '|'\t'|'\n'|'\r')-> skip;
LINECOMENTS     : '//' (~('\n'|'\r'))* -> skip;
BLOCKCOMENTS    : '/*'( ~('/'|'*')|'/'~'*'|'*'~'/'|BLOCKCOMENTS)* '*/' -> skip;
