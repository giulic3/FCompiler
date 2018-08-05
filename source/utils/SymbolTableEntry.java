package utils;

import ast.Node;
import ast.types.*;

public class SymbolTableEntry {
	
	private int nestingLevel;
	private int offset;
	private Node type;
	private String className = null;
	private Node staticType = null;
	
	public SymbolTableEntry(int level, int offset, Node t) {
		this.nestingLevel = level;
		this.offset = offset;
		this.type = t;
	}
	
	// TODO: sperimentale
	public static SymbolTableEntry copyInstance(SymbolTableEntry s) {
		if (s == null) return null;
		SymbolTableEntry copy = new SymbolTableEntry(s.nestingLevel, s.offset, s.type.copyInstance());
		copy.className = s.className;
		copy.staticType = (s.staticType != null) ? s.staticType.copyInstance() : null;
		return copy;
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
	
	public Node getStaticType() {
		return staticType;
	}
	
	public void setStaticType(Node type) {
		this.staticType = type;
	}
	
	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	public String toPrint(String s) {
		return s + "Associated STEntry = { type: " + type.toPrint("") + (staticType != null ? ", static type: " + staticType.toPrint("") : "") + ", nesting level: " + nestingLevel + ", offset: " + offset + " }";
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
