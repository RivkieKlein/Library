package library;
import java.time.*;
import java.util.*;
public class part1 {
	public static void main(String []args) {
		
		//demonstrate asking user for address and sending it to address constructor
		Scanner keyboard=new Scanner(System.in);
		System.out.println("Please enter the street you live on");
		String street=keyboard.nextLine();
		
		System.out.println("Please enter the city you live in");
		String city=keyboard.nextLine();
		
		System.out.println("Please enter the state you live in");
		String state=keyboard.nextLine();
		
		System.out.println("Please enter your zip code");
		String zip=keyboard.nextLine();
		
		//tests constructor that accepts state as a string
		Address add1= new Address(street, city, state, zip);
		
		//tests constructor that accepts state as an enum
		int position=state.indexOf(" ");
		if(position>=0) {
			state=state.substring(0, position)+state.substring(position+1);
		}
		
		Address add2= new Address(street, city, USState.valueOf(state.toUpperCase()), zip);
		
		//tests toString of Address class
		System.out.println(add1);//implicit call to toString
		
		//tests getters
		System.out.println(add2.getStreet());
		System.out.println(add2.getCity());
		System.out.println(add2.getState());
		System.out.println(add2.getZip());
		
		
		//test person class
		
		//get input
		System.out.println("What is your first name");
		String first=keyboard.nextLine();
		
		System.out.println("What is your last name");
		String last=keyboard.nextLine();
		
		System.out.println("Please enter your birthdate in the yyyy-mm-dd format including dashes");
		String date=keyboard.nextLine();
		
		//test first constructor by passing names, address and date as string
		Person john=new Person(first, last, add1, date);
		
		//test second constructor by creating new object with same info in dift format
		Person john2=new Person(first, last, street, city, state, zip,date);
		
		//test third constructor should be same info dift format
		LocalDate dateL=LocalDate.parse(date);
		Person john3= new Person(first, last, add2, dateL);
		
		//test equals method
		//all the johns should be equal even tho objects were created diftly with dift address objects (of same info)
		if(john.equals(john2)) {
			if(john.equals(john3)) {
				if(john2.equals(john3)) {
					System.out.println("This Program seems to be working as it should!");
				}
			}
		}
		
		//now change john 1s last name so when comparing it to john 2 it should return a value of 1
		last=last+1;
		john.setLastName(last);
		int compare =john.compareTo(john2);
		System.out.println(compare);
		
		//next compare john2 and john 3 without changing anything it should return 0
		compare= john2.compareTo(john3);
		System.out.println(compare);
		
		//next compare john2 and 3 but change john 3 to be a later in alpha than 3
		john3.setLastName(last);//the greater last name is given to john3
		compare= john2.compareTo(john3);
		System.out.println(compare);
		
		//test 2 string and getters both times should display same details but getters will be without the formatting
		System.out.println(john2);//implicit call to toString
		
		System.out.println(john2.getFirstName());
		System.out.println(john2.getLastName());
		System.out.println(john2.getAddress());//implicit call to toString of adress class
		System.out.println(john2.getBirthDate());
	}//end main
	}

