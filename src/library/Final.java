package library;

import java.time.LocalDate;
import java.util.*;

public class Final {
	public static void main(String[] arg) {

		Scanner keyboard = new Scanner(System.in);
		
		// create new instance of library class
		Library lib = new Library("Brooklyn Public");
		
		// variable to hold user choice
		int choice;
		
		// will repeat till user says to exit
		do {
			menu(lib);// calls method that displays menu
			choice = keyboard.nextInt();// absorbs user choice
			keyboard.nextLine();
			// switch statement based on option user chose
			switch (choice) {
			case 1:

				try {
					System.out.println("what is the employee's first name?");
					String f = keyboard.nextLine();

					System.out.println("what is " + f + "'s last name?");
					String l = keyboard.nextLine();

					System.out.println("what street does " + f + " live on");
					String s = keyboard.nextLine();

					System.out.println("what city does " + f + " live in");
					String c = keyboard.nextLine();
					System.out.println("what state does " + f + " live in");
					String st = keyboard.nextLine();
					System.out.println("what is " + f + "'s zip code? ");
					String z = keyboard.nextLine();
					// create address
					Address a = new Address(s, c, st, z);
					System.out.println("what is " + f + "'s birthdate? pls enter in yyyy-mm-dd form");
					String d = keyboard.nextLine();

					System.out.println("when was " + f + " hired? Please enter in yyyy-mm-dd form");
					String h = keyboard.nextLine();
					double r;
					do {
						System.out.println("How much is " + f + "'s rate");
						r = keyboard.nextDouble();
						keyboard.nextLine();
						if (r < 10) {
							System.out.println("Please enter a rate that is at least $10. no negative rates");
						}
					} while (r < 10);

					lib.addEmployee(f, l, a, d, h, r);
					System.out.println("Employee added.");
				}

				catch (InvalidAgeException e) {
					e.getMessage();
				} catch (InvalidPayRateException ex) {
					ex.getMessage();
				}
				break;
			case 2:
				try {
					System.out.println("what is the librarians's first name?");
					String f = keyboard.nextLine();

					System.out.println("what is " + f + "'s last name?");
					String l = keyboard.nextLine();

					System.out.println("what street does " + f + " live on");
					String s = keyboard.nextLine();

					System.out.println("what city does " + f + " live in");
					String c = keyboard.nextLine();
					System.out.println("what state does " + f + " live in");
					String st = keyboard.nextLine();
					System.out.println("what is " + f + "'s zip code? ");
					String z = keyboard.nextLine();
					// create address
					Address a = new Address(s, c, st, z);
					System.out.println("what is " + f + "'s birthdate? pls enter in yyyy-mm-dd form");
					String d = keyboard.nextLine();
					System.out.println("when was " + f + " hired? Please enter in yyyy-mm-dd form");
					String h = keyboard.nextLine();
					double r;
					do {
						System.out.println("How much is " + f + "'s salary");
						r = keyboard.nextDouble();
						keyboard.nextLine();
						if (r < 0) {
							System.out.println("Salary cannot be negative");
						}
					} while (r < 0);
					System.out.println("Does " + f + " have a bachelors, masters or, PHD degreee?");
					String de = keyboard.nextLine();

					System.out.println("when did they earn their " + de + "? Please enter in yyyy-mm-dd form");
					LocalDate grad = LocalDate.parse(keyboard.next());
					keyboard.nextLine();
					System.out.println("And from which institution?");
					String school = keyboard.nextLine();

					lib.addLibrarian(f, l, a, d, h, r,
							new EarnedDegree(DegreeType.valueOf(de.toUpperCase()), grad, school));
					System.out.println("Librarian added.");
				} catch (InvalidAgeException e) {
					e.getMessage();
				} catch (InvalidPayRateException ex) {
					ex.getMessage();
				}
				break;

			case 3:

				System.out.println("what is the title of the book");
				String t = keyboard.nextLine();

				System.out.println("What is the isbn or isbn-13 of " + t + "?");
				String i = keyboard.nextLine();

				// loop till user enters possible number of authors
				int num;
				do {
					System.out.println("how many authors does " + t + " have? ");
					num = keyboard.nextInt();
					if (num < 0) {
						System.out.println("Book must have at least one author");
					}
				} while (num < 0);
				ArrayList<Person> auths = new ArrayList<Person>(num);
				for (int counter = 0; counter < num; counter++) {
					System.out.println("What is the first name of author " + (counter + 1) + "?");
					String fi = keyboard.nextLine();
					System.out.println("what is " + fi + "'s last name?");
					String la = keyboard.nextLine();

					System.out.println("what street does " + fi + " live on");
					String street = keyboard.nextLine();

					System.out.println("what city does " + fi + " live in");
					String cit = keyboard.nextLine();
					System.out.println("what state does " + fi + " live in");
					String sta = keyboard.nextLine();
					System.out.println("what is " + fi + "'s zip code? ");
					String zip = keyboard.nextLine();
					System.out.println("what is " + fi + "'s birthdate? pls enter in yyyy-mm-dd form");
					String date = keyboard.nextLine();
					auths.add(new Person(fi, la, new Address(street, cit, sta, zip), date));
				}
				ArrayList<String> cats = new ArrayList<String>();
				System.out.println("What genre is " + t + "?");
				cats.add(keyboard.nextLine());
				lib.addLibraryBook(i, t, auths, cats);
				System.out.println("Book added");

				break;
			case 4:

				System.out.println("If the person who's name you wish to modify has an employee"
						+ " ID and you know what it is please enter it now. other wise enter -1");
				int id = keyboard.nextInt();
				keyboard.nextLine();
				if (id == -1) {
					System.out.println("What is the persons first name?");
					String of = keyboard.nextLine();
					System.out.println("What is " + of + "'s old last name?");
					String ol = keyboard.nextLine();
					System.out.println("What is " + of + "'s new last name?");
					String newLa = keyboard.nextLine();
					System.out.println("What is " + of + "'s birthdate in yyyy-mm-dd format");
					String birth = keyboard.nextLine();
					lib.modifyLastName(of, ol, newLa, LocalDate.parse(birth));

				} else {
					System.out.println("What is the employee's new last name?");
					String newLas = keyboard.nextLine();
					lib.modifyLastName(id, newLas);
				}
				break;

			case 5:
				try {
					System.out.println("What is the ID of the employee whos time sheet you would like to post?");
					int emp = keyboard.nextInt();
					keyboard.nextLine();
					int hours;
					do {
						System.out.println("How many hours did they work this way");
						hours = keyboard.nextInt();
						keyboard.nextLine();
						if (hours < 0 || hours > 168) {
							System.out.println("an employee cannot have worked negative hours or more than 168 hours");
						}
					} while (hours < 0 || hours > 168);
					lib.postTimeSheet(emp, hours);
				} catch (InvalidHoursException e) {
					e.getMessage();
				} catch (InvalidDateException e) {
					e.getMessage();
				}
				break;// end case 5

			case 6:
				int wemp;
				do {
					System.out.println("What is the ID of the employee whos wages you woul like to get?");
					wemp = keyboard.nextInt();
					keyboard.nextLine();
					if (wemp < 1) {
						System.out.println("Employee IDs start from 1");
					}
				} while (wemp < 1);
				System.out.println("This employee was paid $" + lib.getTotalWages(wemp) + " so far this year");
				break;// end case 6

			case 7:
				System.out.println(
						"The library paid its employess a total of $" + lib.getTotalWages() + " so far this year");
				break;// end case 7

			case 8:
				int endID;
				do {
					System.out.println("What is the ID of the employee you would like to terminate?");
					endID = keyboard.nextInt();
					keyboard.nextLine();
					if (endID < 1) {
						System.out.println("Employee IDs start from 1");
					}
				} while (endID < 1);
				lib.terminateEmployee(endID);
				System.out.println("Employee terminated");
				break;

			case 9:
				int modID;
				do {
					System.out.println("What is the ID of the employee whose pay you would like to modify?");
					modID = keyboard.nextInt();
					keyboard.nextLine();
					if (modID < 1) {
						System.out.println("Employee IDs start from 1");
					}
				} while (modID < 1);
				System.out.println("What is their new pay?");
				double newp = keyboard.nextDouble();
				keyboard.nextLine();
				lib.modifyPay(modID, newp);
				break;

			case 10:
				System.out.println(lib);
				break;

			case 0:
				System.out.println("Thank you for visiting this library program. Happy reading!");
				System.exit(1);
				break;
			default:
				System.out.println("please enter a valid menu option");
			}
		} while (choice != 0);

	}// end main

	// menu method will display options for library class
	public static void menu(Library lib) {
		System.out.println("Library options for " + lib.getName());
		System.out.println("1. Add employee\n2. Add librarian\n3. Add library book");
		System.out.println(
				"4. Modify last name\n5.Post time sheet\n6.Get total wages paid this year for employee\n7.Get total wages paid this year for whole staff");
		System.out.println("8.Terminate employee\n9. Modify pay\n10.Display");
		System.out.println("Please enter the number that corresponds to your choice or 0 to exit");
	}

}
