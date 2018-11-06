package library;

public class NoTimeSheetException extends RuntimeException {
	public NoTimeSheetException(String s) {
		super(s);
	}

	public NoTimeSheetException() {
		super("No time sheet");
	}
}
