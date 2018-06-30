package utils;

import ast.types.*;

public class SymbolTableEntry {
	
	private int nestingLevel;
	private int offset;
	private BaseType type;
	
	public SymbolTableEntry(int level, int offset, BaseType t) {
		this.nestingLevel = level;
		this.offset = offset;
		this.type = t;
	}
	
	public int getOffset() {
		return offset;
	}
	
	public int getNestingLevel() {
		return nestingLevel;
	}
	
	public BaseType getType() {
		return type;
	}
	
	public void setType(BaseType type) {
		this.type = type;
	}
	
	public String toPrint(String s) {
		return s + ": STEntry = {\n" +
				"\tnesting level: " + Integer.toString(nestingLevel) + "\n" +
				"\ttype: " + type.toPrint() + "\n" +
				"\toffset: " + Integer.toString(offset) + "\n}\n";
	}
}
