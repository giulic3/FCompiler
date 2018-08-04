package utils;

import ast.Node;
import ast.types.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Helpers {
	
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	
	private static int labelCount = 0;
	private static int funLabelCount = 0;
	private static String funcAssembly = "";
	private static HashMap<String, ArrayList<String>> dispatchTables = new HashMap<>();
	
	/*
		A <: B!!!!!
	 */
	public static boolean subtypeOf(Node a, Node b) {
		
		if ((a instanceof BoolType) && (b instanceof IntType)) {
			return true;
		} else if ((a instanceof IntType) && (b instanceof IntType)) {
			return true;
		} else if ((a instanceof VoidType) && (b instanceof VoidType)) {
			return true;
		} else if ((a instanceof BoolType) && (b instanceof BoolType)) {
			return true;
		} else if ((a instanceof NullType) && (b instanceof ClassType)) {
			return true;
		}
		else if ((a instanceof ClassType) && (b instanceof ClassType)) {
			Set<String> SuperA = ((ClassType) a).getSuperList(true);
			Set<String> SuperB = ((ClassType) b).getSuperList(true);
			Set<String> intersection = new HashSet<String>(SuperA);
			intersection.retainAll(SuperB);
			if(!intersection.isEmpty()){
				return true;
			}
		}
		return false;
	}
	
	public static String newLabel() {
		return "label" + (labelCount++);
	}
	
	public static String newFuncLabel() {
		return "function" + (funLabelCount++);
	}
	
	public static void appendFuncAssembly(String assembly) {
		funcAssembly += "\n" + assembly;
	}
	
	public static String getFuncAssembly() {
		return funcAssembly;
	}
	
	public static String getActivationRecordCode(int callNestingLevel, int decNestingLevel) {
		String code = "";
		for (int i = 0; i < callNestingLevel - decNestingLevel; i++)
			code += "lw\n";
		return code;
	}
	
	public static String getDispatchTableLabelForClass(String classID) {
		return "DTClass" + classID;
	}
	
	public static ArrayList<String> getDispatchTable(String classID) {
		return dispatchTables.get(getDispatchTableLabelForClass(classID));
	}
	
	public static void addDispatchTable(String classID, ArrayList<String> dt) {
		dispatchTables.put(getDispatchTableLabelForClass(classID), dt);
	}
	
	public static String generateDispatchTablesCode() {
		String code = "";
		
		for (Map.Entry<String, ArrayList<String>> dt : dispatchTables.entrySet()) {
			code += dt.getKey() + ":\n";
			for (String metLabel: dt.getValue()) {
				code += metLabel;
			}
			code += "\n";
		}
		
		return code;
	}
	
	public static List<String> getOrderedListOfErrors(HashSet<String> set) {
		
		Pattern p = Pattern.compile("[0-9]+:[0-9]+");
		
		ArrayList<SemanticError> errorsWithLine = new ArrayList<>();
		ArrayList<String> errorsWithoutLine = new ArrayList<>();
		
		for (String s: set) {
			Matcher m = p.matcher(s);
			if (m.find()) {
				String lineColumn = m.group(0);
				String[] parts = lineColumn.split(":");
				SemanticError t = new SemanticError(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), s);
				errorsWithLine.add(t);
			}
			else
				errorsWithoutLine.add(s);
		}
		
		Collections.sort(errorsWithLine, new SemanticError.SortByRowColumn());
		
		List<String> errors = errorsWithLine.stream().map(SemanticError::getError).collect(Collectors.toList());
		errors.addAll(errorsWithoutLine);
		
		return errors;
	}
	
	public static boolean detectSuperclassErrors(HashSet<String> set) {
		
		Pattern p = Pattern.compile("Superclass '[a-zA-Z0-9]+' not declared");
		for (String s: set) {
			Matcher m = p.matcher(s);
			if (m.find()) return true;
		}
		return false;
	}
}

class SemanticError {
	
	private final Integer row;
	private final Integer column;
	private final String error;
	
	public SemanticError(Integer row, Integer column, String error) {
		this.row = row;
		this.column = column;
		this.error = error;
	}
	
	@Override
	public String toString() {
		return "Semantic Error: row: " + row + ", column: " + column + ", error: " + error;
	}
	
	public String getError() {
		return error;
	}
	
	static class SortByRowColumn implements Comparator<SemanticError> {
		public int compare(SemanticError t1, SemanticError t2) {
			if (t1.row < t2.row) return -1;
			else if (t1.row > t2.row) return 1;
			else {
				if (t1.column < t2.column) return -1;
				else if (t1.column > t2.column) return 1;
				else return 0;
			}
		}
	}
}