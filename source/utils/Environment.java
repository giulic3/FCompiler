package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;

import ast.types.ClassType;
import sun.awt.Symbol;
import utils.SymbolTableEntry;


public class Environment {
	
	private ArrayList<HashMap<String, SymbolTableEntry>> symTable;
	private int nestingLevel;
	private int offset;
	private boolean secondCheck;
	private boolean secondFunCheck;
	
	public Environment() {
		symTable = new ArrayList<>();
		nestingLevel = -1;
		offset = 0;
		secondCheck = false;
		secondFunCheck = false;
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
	
	public int decreaseOffset() {
		return offset--;
	}
	
	public int increaseOffset() {
		return offset++;
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
	
	public SymbolTableEntry getClassEntry(String classID) {
		return symTable.get(0).get("Class$"+classID);
	}
	
	public void updateClassEntry(ClassType type) {
		SymbolTableEntry curEntry = getClassEntry(type.getID());
		curEntry.setType(type);
		symTable.get(0).put(type.getID(), curEntry);
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
	
	public void settingSecondCheck() {
		secondCheck = true;
	}
	
	public boolean getSecondCheck() {
		return secondCheck;
	}
	
	public void settingFunSecondCheck(boolean isSecond) {
		secondFunCheck = isSecond;
	}
	
	public boolean getFunSecondCheck() {
		return secondFunCheck;
	}
}
