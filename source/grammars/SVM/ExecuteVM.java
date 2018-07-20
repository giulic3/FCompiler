package grammars.SVM;

import java.util.ArrayList;

public class ExecuteVM {
	
	public static final int CODESIZE = 10000;
	public static final int MEMSIZE = 25;
	
	private int[] code;
	private int[] memory = new int[MEMSIZE];
	
	private int ip = 0;
	private int sp = MEMSIZE;
	
	private int hp = 0;
	private int fp = MEMSIZE;
	private int ra;
	private int rv;
	
	public ExecuteVM(int[] code) {
		this.code = code;
	}
	
	public void cpu() {
		while ( true ) {
			int bytecode = code[ip++]; // fetch
			int v1,v2;
			int address;
			switch ( bytecode ) {
				case SVMParser.PUSH:
					push( code[ip++] );
					break;
				case SVMParser.POP:
					pop();
					break;
				case SVMParser.ADD :
					v1=pop();
					v2=pop();
					push(v2 + v1);
					break;
				case SVMParser.MULT :
					v1=pop();
					v2=pop();
					push(v2 * v1);
					break;
				case SVMParser.DIV :
					v1=pop();
					v2=pop();
					push(v2 / v1);
					break;
				case SVMParser.SUB :
					v1=pop();
					v2=pop();
					push(v2 - v1);
					break;
				case SVMParser.STOREW : //
					address = pop();
					memory[address] = pop();
					break;
				case SVMParser.LOADW : //
					push(memory[pop()]);
					break;
				case SVMParser.BRANCH :
					address = code[ip];
					ip = address;
					break;
				case SVMParser.BRANCHEQ : //
					address = code[ip++];
					v1=pop();
					v2=pop();
					if (v2 == v1) ip = address;
					break;
				case SVMParser.BRANCHLESSEQ :
					address = code[ip++];
					v1=pop();
					v2=pop();
					if (v2 <= v1) ip = address;
					break;
				case SVMParser.JS : //
					address = pop();
					ra = ip;
					ip = address;
					break;
				case SVMParser.STORERA : //
					ra=pop();
					break;
				case SVMParser.LOADRA : //
					push(ra);
					break;
				case SVMParser.STORERV : //
					rv=pop();
					break;
				case SVMParser.LOADRV : //
					push(rv);
					break;
				case SVMParser.LOADFP : //
					push(fp);
					break;
				case SVMParser.STOREFP : //
					fp=pop();
					break;
				case SVMParser.COPYFP : //
					fp=sp;
					break;
				case SVMParser.STOREHP : //
					hp=pop();
					break;
				case SVMParser.LOADHP : //
					push(hp);
					break;
				case SVMParser.PRINT :
					System.out.println((sp<MEMSIZE)?memory[sp]:"Empty stack!");
					pop();  // TODO: HIGHLY EXPERIMENTAL
					break;
				case SVMParser.HALT :
					return;
				case SVMParser.NEW:
					// mi prendo l'heap pointer attuale per sapere dove inizia il nuovo oggetto
					int startPointer = hp;
					int dispTB = pop();
					int nargs = pop();
					pushHeap(dispTB);
					for(int i=0; i < nargs; i++){
						pushHeap(pop());
					}
					push(startPointer);
					break;
				case SVMParser.CPHEAD:
					int top = pop();
					push(top);
					push(top);
					break;
				case SVMParser.JSMETH:
					int codeAddress = pop();
					push(code[codeAddress]);
			}
		}
	}
	
	private int pop() {
		return memory[sp++];
	}
	
	private void push(int v) {
		memory[--sp] = v;
	}
	
	private void pushHeap(int v){
		memory[hp++] = v;
	}
	
}