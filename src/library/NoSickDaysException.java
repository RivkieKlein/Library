package library;

public class NoSickDaysException extends RuntimeException {
	
public NoSickDaysException(String s) {
	super(s);
}

public NoSickDaysException() {
	super("You have no sick days left for this year");
}

}
