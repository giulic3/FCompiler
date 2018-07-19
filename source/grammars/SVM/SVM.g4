grammar SVM;

@header {
package grammars.SVM;
import java.util.HashMap;
}

@lexer::members {
public int lexicalErrors=0;
}

@parser::members {
public static int[] code = new int[ExecuteVM.CODESIZE];
static int i = 0;
static HashMap<String,Integer> labelAdd = new HashMap<String,Integer>();
static HashMap<Integer,String> labelRef = new HashMap<Integer,String>();
}

/*------------------------------------------------------------------
 * PARSER RULES
 *------------------------------------------------------------------*/

assembly: ( simpleCmd )* ;

simpleCmd: ( composedCmd | POP | ADD | SUB | MULT | DIV | STOREW | LOADW | JS | LOADRA | STORERA
            | LOADRV | STORERV | LOADFP | STOREFP | COPYFP | LOADHP | STOREHP | PRINT | HALT | NEW ) ;

composedCmd: ( PUSH num=NUMBER | PUSH label=LABEL | label=LABEL COL
            | BRANCH label=LABEL | BRANCHEQ label=LABEL | BRANCHLESSEQ label=LABEL ) ;

/*------------------------------------------------------------------
 * LEXER RULES
 *------------------------------------------------------------------*/

PUSH  	 : 'push' ; 	// pushes constant in the stack
POP	 : 'pop' ; 	// pops from stack
ADD	 : 'add' ;  	// add two values from the stack
SUB	 : 'sub' ;	// add two values from the stack
MULT	 : 'mult' ;  	// add two values from the stack
DIV	 : 'div' ;	// add two values from the stack
STOREW	 : 'sw' ; 	// store in the memory cell pointed by top the value next
LOADW	 : 'lw' ;	// load a value from the memory cell pointed by top
BRANCH	 : 'b' ;	// jump to label
BRANCHEQ : 'beq' ;	// jump to label if top == next
BRANCHLESSEQ:'bleq' ;	// jump to label if top <= next
JS	 : 'js' ;	// jump to instruction pointed by top of stack and store next instruction in ra
LOADRA	 : 'lra' ;	// load from ra
STORERA  : 'sra' ;	// store top into ra
LOADRV	 : 'lrv' ;	// load from rv
STORERV  : 'srv' ;	// store top into rv
LOADFP	 : 'lfp' ;	// load frame pointer in the stack
STOREFP	 : 'sfp' ;	// store top into frame pointer
COPYFP   : 'cfp' ;      // copy stack pointer into frame pointer
LOADHP	 : 'lhp' ;	// load heap pointer in the stack
STOREHP	 : 'shp' ;	// store top into heap pointer
PRINT	 : 'print' ;	// print top of stack
HALT	 : 'halt' ;	// stop execution
NEW      : 'new'; // alloc heap memory
COL	 : ':' ;
LABEL	 : ('a'..'z'|'A'..'Z')('a'..'z' | 'A'..'Z' | '0'..'9')* ;
NUMBER	 : '0' | ('-')?(('1'..'9')('0'..'9')*) ;

WHITESP  : ( '\t' | ' ' | '\r' | '\n' )+   -> channel(HIDDEN);

ERR   	 : . { System.err.println("Invalid char: "+ getText()); lexicalErrors++;  } -> channel(HIDDEN);
