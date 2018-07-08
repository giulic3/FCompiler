package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;

import sun.awt.Symbol;
import utils.SymbolTableEntry;


public class Environment {
	
	private ArrayList<HashMap<String, SymbolTableEntry>> symTable;
	private int nestingLevel;
	private int offset;
	
	public Environment() {
		symTable = new ArrayList<HashMap<String, SymbolTableEntry>>();
		nestingLevel = -1;
		offset = 0;
	}
	
	public ArrayList<HashMap<String, SymbolTableEntry>> getSymTable() {
		return symTable;
	}
	
	public int getNestingLevel() {
		return nestingLevel;
	}
	
	public int getOffset() {
		return offset;
	}
	
	public void setNestingLevel(int nestingLevel) {
		this.nestingLevel = nestingLevel;
	}
	
	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	public void setSymTable(ArrayList<HashMap<String, SymbolTableEntry>> symTable) {
		this.symTable = symTable;
	}
	
	public SymbolTableEntry getActiveDec(String id) {
		int index = symTable.size();
		SymbolTableEntry res = null;
		
		while(index > 0 && res == null){
			index--;
			res = symTable.get(index).get(id);
		}
		
		return res;
	}
	
	public void pushScope() {
		HashMap<String, SymbolTableEntry> scope = new HashMap<>();
		nestingLevel+=1;
		symTable.add(scope);
	}
	
	public void popScope() {
		symTable.remove(nestingLevel);
		nestingLevel-=1;
	}
}
