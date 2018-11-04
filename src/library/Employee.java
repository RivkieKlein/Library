package library;

import java.util.*;
import java.time.*;

public class Employee extends Person {
	private int employeeID;
	private LocalDate hireDate;
	private int usedVacationDays;
	private int usedSickDays;
	private double hourlyPayRate;
	private ArrayList<TimeSheet> timeSheets;
	
	//static variables
	private static int YearVacationDays = 10;
	private static int YearSickDays = 4;
	private static int LastEmployeeID = 0;
	
	//static methods
	public static void setYearVacationDays(int value) {

		YearVacationDays = value;

	}

	public static void SetYearSickDays(int value) {
		YearSickDays = value;
	}
	
	//throws InvalidAgeException if employee is under 18
	//throws InvalidPayRateException is hourly rate is less than 10
	public Employee(String f, String l, Address addr, String birthDate, String hireDate, double hourlyRate) {
		super(f, l, addr, birthDate);
		// calculate current age
		Period age = LocalDate.parse(birthDate).until(LocalDate.now());
		if (age.getYears() < 18) {
			throw new InvalidAgeException("Employee must be at least 18 years old.");
		}

		// verify rate is a reasonable value
		if (hourlyRate < 10) {
			throw new InvalidPayRateException("Employees must receive at least $10 an hour");
		}

		this.hireDate = LocalDate.parse(hireDate);
		hourlyPayRate = hourlyRate;
		employeeID = ++LastEmployeeID;// first ID is one
		usedSickDays = 0;
		usedVacationDays = 0;
		timeSheets = new ArrayList<TimeSheet>();

	}// end constructor
	
	//adds a single sick day to total used sick days for that employee
	//throws NoSickDaysExceptions if no sick days left
	public void takeSickDays() {
		if ((YearSickDays - usedSickDays) < 1) {
			throw new NoSickDaysException("You have no sick days left this year");

		} else {
			usedSickDays++;
		}
	}
	
	//adds a single vacation day to total used vacation days for that employee
	//throws NoVacationExceptions if no sick days left
	public void takeVacationDays() {
		if ((YearVacationDays - usedVacationDays) >= 1) {
			usedVacationDays++;
		} else {
			throw new NoVacationException("You have no vacation days left this year");
		}
	}

	public void modifyPay(double pay) {
		hourlyPayRate = pay;
	}

	public int getRemainVacationDays() {
		return YearVacationDays - usedVacationDays;
	}

	public int getRemainSickDays() {
		return YearSickDays - usedSickDays;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public int getUsedVacationDays() {
		return usedVacationDays;
	}

	public int getUsedSickDays() {
		return usedSickDays;
	}
	
	//adds new time sheet for this week
	public void completeTimeSheet(double hours) {
		TimeSheet t = new TimeSheet(hours);// instantiates object, uses the time sheet constructor that accepts 1 piece
											// of data
		timeSheets.add(t);// adds to array in this class
	}
	
	//adds new time sheet for specified week
	public void completeTimeSheet(LocalDate date, double hours) {
		TimeSheet t = new TimeSheet(hours);// instantiates time sheet object ,uses the time sheet constructor that
											// accepts 2 pieces of data
		timeSheets.add(t);// adds to array in this claa
	}
	
	//returns a total of all hours employee worked this year based on time sheets
	public int getTotalHoursWorked(int year) {
		
		int total = 0;
		
		for (TimeSheet sheet : timeSheets) {// runs through all the time sheets in the array
			
			if (sheet.getDate().getYear() == year) {// adds the hours in a time sheet to the total only if it is from
													// this year
				total += sheet.getHours();
			}
			
		}
		
		return total;
	}
	
	//total paid for specified week based on time sheet of that week
	//throws invalid date exception if no time sheet for that week
	public double getPayForWeek(LocalDate date) {
		int position = -1;// will hold the position in array of the time sheet for that week
		
		for (int counter = 0; counter < this.timeSheets.size(); counter++) {
			if (timeSheets.get(counter).getDate().equals(date)) {
				position = counter;
			} 
		} // after running through whole array of time sheet, position variable will hold
			// position of time sheet with that date or will keep -1 if none from that date
		
		if(position == -1) {
			throw new InvalidDateException("No time sheet on file for that date");
		}
		
		return timeSheets.get(position).getHours() * hourlyPayRate;// returns whole pay for this period by multiplying
																	// hours worked by rate

	}
	
	//total pay for specified year
	public double getTotalPay(int year) {
		int total = 0;
		for (TimeSheet sheet : timeSheets) {// runs through all the time sheets in the array
			if (sheet.getDate().getYear() == year) {// adds the hours in a time sheet to the total only if it is from
													// this year
				total += sheet.getHours();
			}
		}
		return total * hourlyPayRate; // if no hours will still return zero cause multiplying rate by zero
	}
	
	//resets sick days used to zero if it is the first day of the new year
	public void resetSickDays() {
		if (LocalDate.now().getMonth() == Month.JANUARY && LocalDate.now().getDayOfMonth() == 1) {// if its january
																									// first then reset
																									// the days used to
																									// zero cause new
																									// year
			usedSickDays = 0;
		}
	}
	
	//reset vacation days used to zero if it is first day of new years
	public void resetVacationDays() {
		if (LocalDate.now().getMonth() == Month.JANUARY && LocalDate.now().getDayOfMonth() == 1) {
			usedVacationDays = 0;
		}
	}

	@Override // overrides toString of person class
	public String toString() {
		StringBuilder output = new StringBuilder(super.toString());// calls toString of person class and adds it to the
																	// strign Builder
		output.append("\nEmployee ID: " + employeeID);
		output.append("\nHireDate: " + hireDate);
		output.append("\nHourly Pay Rate: $" + hourlyPayRate);
		output.append("\nVacation Days Used: " + usedVacationDays);
		output.append("\nSick Days Used: " + usedSickDays);
		// print all the time sheets of this year for this employee using a loop to
		// access toString of timeSheet class for every sheet in arry
		// start with header
		output.append("\nWeekly hours according to submitted time sheets");
		for (TimeSheet sheet : timeSheets) {
			output.append("\n" + sheet);// implicit call to toString of TimeSheet class
		}
		return output.toString();
	}

	public int compareTo(Employee e) {
		if (this.employeeID > e.employeeID) {
			return 1;
		} else if (this.employeeID < e.employeeID) {
			return -1;
		}
		return 0;// if not greater or less than then it is equal
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (employeeID != other.employeeID)
			return false;
		if (hireDate == null) {
			if (other.hireDate != null)
				return false;
		} else if (!hireDate.equals(other.hireDate))
			return false;
		if (Double.doubleToLongBits(hourlyPayRate) != Double.doubleToLongBits(other.hourlyPayRate))
			return false;
		if (timeSheets == null) {
			if (other.timeSheets != null)
				return false;
		} else if (!timeSheets.equals(other.timeSheets))
			return false;
		if (usedSickDays != other.usedSickDays)
			return false;
		if (usedVacationDays != other.usedVacationDays)
			return false;
		return true;
	}

}
