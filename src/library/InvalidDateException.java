package library;

public class InvalidDateException extends RuntimeException {

	
	public InvalidDateException() {
		super("Invalid Date. Pay periods end on fridays");
	}

	
	public InvalidDateException(String s) {
		super(s);
	}
}
