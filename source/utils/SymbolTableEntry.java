package utils;

import ast.Node;
import ast.types.*;

public class SymbolTableEntry {
	
	private int nestingLevel;
	private int offset;
	private Node type;
	private String className = null;
	
	public SymbolTableEntry(int level, int offset, Node t) {
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
	
	public Node getType() {
		return type;
	}
	
	public void setType(Node type) {
		this.type = type;
	}
	
	public String toPrint(String s) {
		return s + "Associated STEntry = { type: " + type.toPrint("") + ", nesting level: " + nestingLevel + ", offset: " + offset + " }";
	}
	
	public String toString() {
		return toPrint("");
	}
	
	public void setClassName(String className) {
		this.className = className;
	}
	
	public String getClassName() {
		return className;
	}
}
