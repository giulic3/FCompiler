package utils;

import ast.Node;
import ast.types.*;

import java.util.HashSet;
import java.util.Set;

public class Helpers {
	
	private static int labelCount = 0;
	private static int funLabelCount = 0;
	private static String funcAssembly = "";
	
	
	public static boolean subtypeOf(Node a, Node b) {
		
		if ((a instanceof BoolType) && (b instanceof IntType)) {
			return true;
		} else if ((a instanceof IntType) && (b instanceof IntType)) {
			return true;
		} else if ((a instanceof VoidType) && (b instanceof VoidType)) {
			return true;
		} else if ((a instanceof BoolType) && (b instanceof BoolType)) {
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
}
