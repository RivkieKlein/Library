package library;

public class InvalidHoursException extends RuntimeException {

	public InvalidHoursException() {
		super("You cannot work less than zero hours. You also cannot work more than 168 hours because a week does not have more than that");
	}

	public InvalidHoursException(String s) {
		super(s);
	}
}
