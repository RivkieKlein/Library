package library;

public class InvalidPayRateException extends RuntimeException {

	public InvalidPayRateException(String s) {
		super(s);
	}

	public InvalidPayRateException() {
		super("That is not a vali pay rate");
	}
}
