package utils;

import ast.Node;

public class Helpers {
	
	public static boolean subtypeOf(Node a, Node b) {
		
		if (a.getClass() == b.getClass()) return true;
		
		return false;
	}
}
