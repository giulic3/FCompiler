package utils;


public class TypeCheckException extends Exception {
	
	private String nodeCause;
	private int errorLine;
	private int errorColumn;
	
	public TypeCheckException(String nodeCause, int errorLine, int errorColumn) {
		super(nodeCause, null, true, false);
		this.nodeCause = nodeCause;
		this.errorColumn = errorColumn;
		this.errorLine = errorLine;
	}
	
	public String toString() {
		return "Type check error: wrong type in " + nodeCause + " at line " + errorLine + ":" + errorColumn + "\n";
	}
	
}
