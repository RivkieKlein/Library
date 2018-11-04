package library;

public class InvalidAgeException extends RuntimeException {
	public InvalidAgeException(String s) {
		super(s);
	}

	public InvalidAgeException() {
		super("That is not a valid age");
	}

}
