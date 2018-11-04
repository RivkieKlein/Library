package library;
import java.time.LocalDate;
import java.util.*;

public class Library {
	
//variables
String libraryName;
ArrayList<Person> people;
ArrayList<Book> books;

//constructor
public Library(String name) {
	libraryName=name;
	this.people=new ArrayList<Person>();
	this.books=new ArrayList<Book>();
}

//get lib name
public String getName() {
	return libraryName;
}

//add employee
public boolean addEmployee(String f, String l, Address addr, String birthdate, String hireDate, Double wages) {
Employee newE=new Employee(f, l, addr, birthdate, hireDate, wages);
if(!people.contains(newE)) {
	people.add(newE);
	return true;
}
return false;
}

//add librarian
public boolean addLibrarian(String f, String l, Address addr, String birthdate, String hireDate, Double salary, EarnedDegree degree ) {
	Librarian newE=new Librarian(f, l, addr, birthdate, hireDate, degree, salary);
	if(!people.contains(newE)) {
		people.add(newE);
		return true;
	}
	return false;
}

//add library book
public void addLibraryBook(String isbn, String title, ArrayList<Person> authors, ArrayList<String>categories) {
	//new array list will hold category enums converted from strings
	ArrayList<BookCategory> cats=new ArrayList<BookCategory>();
	for(String s: categories) {
	cats.add(BookCategory.valueOf(s.toUpperCase()));	
	}
	Book libBook=new Book(isbn, title, authors, cats);
}


//modify last name when told old name
public void modifyLastName(String first, String oldLast, String newLast, LocalDate birth) {
	//loop through array of people 
	for(Person pers: people) {
		//first check if birthdates match to see if same person
		if(pers.getBirthDate().equals(birth)) {
			if(pers.getLastName().equals(oldLast)) {
				if(pers.getFirstName().equals(first)) {
					pers.setLastName(newLast);//if all 3 conditions are fulfilled 
				}
			}
		}
	}//end for loop
}

//modify last name when told id
public void modifyLastName(int iD, String newLast) {
	for(Person pers: people) {
		//first must check if person is employee
		if(pers instanceof Employee) {
			if(((Employee) pers).getEmployeeID()==iD) {
				pers.setLastName(newLast);
			}
		}
	}
}

//post time sheet
public void postTimeSheet(int empID, int hours) {
	//runs through librarys array of people
	for(int c=0; c<people.size(); c++) {
		//if that person is an emplyee i.e. has an employee id
		if(people.get(c) instanceof Employee) {
			//if that employee id matches the one passed in
			if(((Employee) people.get(c)).getEmployeeID()==empID) {
				//call complete time sheet method for that emp
				((Employee)people.get(c)).completeTimeSheet(hours);//type cast cause it is part of array of people (polymorph)
				
			}
		}
	}
}

//get total wages for curent year for  specific employee
public double getTotalWages(int empID) {
	//runs through librarys array of people
		for(int c=0; c<people.size(); c++) {
			//if that person is an Librarian 
			if(people.get(c) instanceof Librarian) {
				//if that employee id matches the one passed in
				if(((Librarian) people.get(c)).getEmployeeID()==empID) {
					//call get total from librarian class
					return ((Librarian) people.get(c)).getTotalPay(); //type cast cause it is part of array of people (polymorph)
					
				}
			}
			//not librarian but still employee
			else if(people.get(c) instanceof Employee) {
				if(((Employee)people.get(c)).getEmployeeID()==empID){
					return ((Employee) people.get(c)).getTotalPay(LocalDate.now().getYear());
				}

			}
		}
		//if the employee (ID)does not exist
	return -1;
}

//get total wages paid for this year for all employees
public double getTotalWages() {
	//varaible to hold total of all paid
	double total=0;
	//runs through librarys array of people
	for(int c=0; c<people.size(); c++) {
		//if object in array is a librarian 
	    if(people.get(c) instanceof Librarian) {
	    //the get total pay method of librarian class returns the wages paid
	    //for this year divided by 12 and times the amound of complete months that have passed
	    total+=((Librarian)people.get(c)).getTotalPay();
		}
	    //if object in array is employee but not librarian
	    else if(people.get(c) instanceof Employee ) {
	    total+=((Employee)people.get(c)).getTotalPay(LocalDate.now().getYear());	
	    }
	}//end outer  for
	return total;
}

//terminate employee
public void terminateEmployee(int id) {
	//runs through array and will remove employee with this id
	for(Person pers: people) {
		if(((Employee)pers).getEmployeeID()==id) {
			people.remove(pers);
			break;
		}
	}
}

//modify pay when told id
public void modifyPay(int iD, Double newPay) {
	for(Person pers: people) {
		//first must check if person is employee
		if(pers instanceof Employee) {
			if(((Employee) pers).getEmployeeID()==iD) {
				((Employee)pers).modifyPay(newPay);
			}
		}
	}
}

@Override
public String toString() {
StringBuilder output=new StringBuilder(libraryName.toUpperCase());
//run through array of people. will autocall toString based on what type of person it is
output.append("\n\nPeople:");
for(Person pers:people ) {
	
output.append("\n"+pers);
if(pers instanceof Librarian) {
	output.append("\nYearly Salary: $"+(((Librarian)pers).getYearlyPay()));
}
}
//runs through array of books
output.append("\n\nBooks");
for(Book bk: books) {
output.append(bk);//implicit call to to string of book class	
}
return output.toString();
}//end toString

}
