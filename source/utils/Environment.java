package utils;

import java.util.ArrayList;
import java.util.HashMap;


public class Environment {
	
	private ArrayList<HashMap<String, SymbolTableEntry>> symTable;
	private int nestingLevel;
	private int offset;
	private boolean secondCheck;
	private boolean secondFunCheck;
	private String definingClass = null;
	
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

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int decreaseOffset() {
		return offset--;
	}
	
	public int increaseOffset() {
		return offset++;
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
	
	public SymbolTableEntry getClassEntry(String classID) {
		return symTable.get(0).get("Class$" + classID);
	}
	
	public void setDefiningClass(String classID) {
		this.definingClass = classID;
	}
	
	public String getDefiningClass() {
		return this.definingClass;
	}
}
