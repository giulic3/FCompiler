package utils;

import ast.Node;
import ast.types.*;

import java.util.*;

public class Helpers {
	
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
		return "DT$Class$" + classID;
	}
	
	public static ArrayList<String> getDispatchTable(String classID) {
		return dispatchTables.get(classID);
	}
	
	public static void addDispatchTable(String classID, ArrayList<String> dt) {
		dispatchTables.put(classID, dt);
	}
}