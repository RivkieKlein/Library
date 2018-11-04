package library;

public class NoVacationException extends RuntimeException {
	public NoVacationException() {
		super("");
	}

	public NoVacationException(String s) {
		super(s);
	}
}
