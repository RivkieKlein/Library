package library;

import java.time.*;

public class TimeSheet {
	private LocalDate endOfWeekDate;
	private int hoursWorked;

	// constructor for when hours are given on the day the period ends
	public TimeSheet(Double hours) {
		this(LocalDate.now(), hours);

	}// end constructor that defaults to current date

	public TimeSheet(LocalDate date, Double hours) {
		// if valid date
		if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {// can use == when comparing enums
			// if valid hours
			if (hours > 0 && hours < 168) {
				endOfWeekDate = date;
				hoursWorked = (int) Math.round(hours);// rounds hours to nearest if not a full hour
			}
			// if invalid hours
			else {
				throw new InvalidHoursException();
			}
		}
		// if not valid date
		else {
			throw new InvalidDateException();// if its not a friday the its not a valid payroll date
		}

	}

	// returns date
	public LocalDate getDate() {
		return endOfWeekDate;
	}

	// returns hours worked
	public int getHours() {
		return hoursWorked;
	}

	@Override
	public String toString() {
		StringBuilder output = new StringBuilder(endOfWeekDate.getMonth() + " " + endOfWeekDate.getDayOfMonth() + ", ");
		output.append(endOfWeekDate.getYear() + " ");
		output.append(hoursWorked + " hours ");
		return output.toString();
	}

	// compares 2 time sheets based on their dates

	public int compareTo(TimeSheet t) {
		if (endOfWeekDate.isBefore(t.endOfWeekDate)) {
			return 1;
		} else if (endOfWeekDate.isAfter(t.endOfWeekDate)) {
			return -1;
		} else {
			return 0;// if it is not after nor before than it must be equal
		}
	}

	@Override
	public boolean equals(Object t) {
		if (this == t) {
			return true;
		}
		if (t == null) {
			return false;
		}
		if (getClass() != t.getClass()) {
			return false;
		}
		TimeSheet ts = (TimeSheet) t;
		if (!endOfWeekDate.equals(ts.endOfWeekDate)) {
			return false;
		} else if (hoursWorked != ts.hoursWorked) {
			return false;
		}
		return true;
	}
}
