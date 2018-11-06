package library;

import java.time.*;

public class Librarian extends Employee {

	EarnedDegree degree;
	double yearlyPay;

	public Librarian(String f, String l, Address addr, String birthdate, String hireDate, EarnedDegree degree,
			double yearlyPay) {
		super(f, l, addr, birthdate, hireDate, 30);
		this.degree = degree;// shallow copy
		this.yearlyPay = yearlyPay;
	}

	public EarnedDegree getDegree() {
		return degree;// shallow return
	}

	@Override // overrides pay getter from employee class
	
	public double getTotalPay(int year) {
		return (yearlyPay / 12) * (LocalDate.now().getMonthValue() - 1);// total yearly salary is divided into monthly
																		// salaries and multiplied by the amount or
																		// months passed
																		// minus one cause only months passed already
	}


	public double getTotalPay() {
		return (yearlyPay / 12) * (LocalDate.now().getMonthValue() - 1);// total yearly salary is divided into monthly
																		// salaries and multiplied by the amount or
																		// months passed
		// minus one cause only months passed already
	}

	public double getYearlyPay() {
		return yearlyPay;
	}

	public void modifyPay(double pay) {
		yearlyPay = pay;
	}

	@Override // because librarians dont have time sheets
	public void completeTimeSheet(LocalDate date, double hour) {
		;
	}

	@Override
	public void completeTimeSheet(double hours) {
		;
	}

	public int getTotalHoursWorked() {
		LocalDate now = LocalDate.now();
		LocalDate beginYear = LocalDate.of(now.getYear(), Month.JANUARY, 01);
		Period days = now.until(beginYear);
		// returns hours worked assuming a 7 day workweek and an 8 hour workday
		return 8 * (days.getDays() - (getUsedVacationDays() + getUsedSickDays()));

	}

	@Override
	public double getPayForWeek(LocalDate l) {
		return yearlyPay / 52;
	}

}
