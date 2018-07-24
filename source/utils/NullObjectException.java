package utils;

public class NullObjectException extends Exception {
	
	public NullObjectException() {
		super("Null Object Usage", null, true, false);
	}
	
	public String toString() {
		return "Trying to either use or access uninitialized object\n";
	}
}
