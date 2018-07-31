package utils;

public class DivisionByZeroException extends Exception {
	
	public DivisionByZeroException() {
		super("Division By Zero", null, true, false);
	}
	
	public String toString() {
		return "One or more divisions by zero have been detected. Check your math!\n";
	}
	
	@Override
	public String getMessage() {
		return this.toString();
	}
}
