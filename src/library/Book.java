package library;

import java.util.*;

public class Book {

	private String iSBN;
	private String title;
	private ArrayList<Person> authors;
	private ArrayList<BookCategory> subjectCategories;

	// constructor will accept array list of authors and array list of book
	// categories
	// throws illegal argument exception if isbn is not valid
	public Book(String iSBN, String title, ArrayList<Person> auths, ArrayList<BookCategory> categories) {

		// check validity of isbn
		if (checkISBN(iSBN) == true) {
			this.iSBN = iSBN;
		} else {
			throw new IllegalArgumentException("That is not a valid ISBN");
		}

		this.title = title;

		// copy array of authors
		this.authors = new ArrayList<Person>();// initialize new array
		for (Person auth : auths) {
			this.authors.add(auth);// shallow copy of author if authors info changes want it to update in
									// connection with book too
		}

		// copy array of categories
		this.subjectCategories = new ArrayList<BookCategory>();// initialize new array
		for (BookCategory cat : categories) {
			this.subjectCategories.add(cat);
		}
	}

	// constructor accepts one book category
	// throws IllegalArgumentException if isbn is not valid
	public Book(String iSBN, String title, ArrayList<Person> auths, BookCategory category) {
		// cannot just call other constructor because need to create array list of
		// categories
		if (checkISBN(iSBN) == true) {
			this.iSBN = iSBN;
		} else {
			throw new IllegalArgumentException("That is not a valid ISBN");
		}
		this.title = title;

		// copy array of authors
		this.authors = new ArrayList<Person>();
		for (Person auth : auths) {
			this.authors.add(auth);// shallow copy of author deep copy of array list
		}
		
		//create array list of categories add the one category passed in
		this.subjectCategories = new ArrayList<BookCategory>();
		subjectCategories.add(category);

	}

	// constructor accepts one person and one category as objects and converts to
	// throws IllegalArgumentException if isbn is not valid
	public Book(String isbn, String title, Person author, BookCategory category) {
		if (checkISBN(isbn) == true) {
			this.iSBN = isbn;
		} else {
			throw new IllegalArgumentException("That is not a valid ISBN");
		}
		this.title = title;
		this.authors = new ArrayList<Person>();
		authors.add(author);// shallow copy of author
		this.subjectCategories = new ArrayList<BookCategory>();
		subjectCategories.add(category);

	}

	// gets isbn
	public String getISBN() {
		return iSBN;
	}

	// return title
	public String getTitle() {
		return title;
	}// 

	// return authors as an arraylist
	public ArrayList<Person> getAuthors() {
		// new array list will have a copy of the authors
		ArrayList<Person> authorsNew = new ArrayList<Person>(this.authors.size());
		// deepcopy
		for (int counter = 0; counter < this.authors.size(); counter++) {
			// returns doubly deep copy
			authorsNew.add(new Person(this.authors.get(counter).getFirstName(), this.authors.get(counter).getLastName(),
					this.authors.get(counter).getAddress(), this.authors.get(counter).getBirthDate()));// inner level
																										// deep copy
																										// calls persons
																										// copy
																										// constructor
		}
		return authorsNew;
	}

	// return genres as a new ArrayList
	public ArrayList<BookCategory> getSubjectCategories() {
		
		ArrayList<BookCategory> catsNew = new ArrayList<BookCategory>();
		
		//copys contents
		for (int counter = 0; counter < this.subjectCategories.size(); counter++) {
			catsNew.add(this.subjectCategories.get(counter));
		}
		
		return catsNew;
	}

	// toString
	// will not include address and birthdate of author because not relevent for
	// this toString
	@Override
	public String toString() {
		
		StringBuilder output = new StringBuilder(title);
		
		//add authors to string builder
		output.append("\nBy: ");
		
		for (int counter = 0; counter < authors.size(); counter++) {// for loop to print multiple authors
			
			// this will skip to the next line and indent if more than one author
			if (counter > 0) {
				output.append("\n    ");
			}
			
			output.append(authors.get(counter).getFirstName() + " " + authors.get(counter).getLastName());
		} 
		
		//add genres to string builder
		output.append("\nGenre: ");
		
		for (int counter = 0; counter < subjectCategories.size(); counter++) {// for loop to print multiple genres
			
			if (counter > 0) {// if its the second author or further
				output.append(", ");// this will add a comma
			}
			
			output.append(this.subjectCategories.get(counter));
			
		} // end for loop
		output.append("\nISBN: " + iSBN);
		
		return output.toString();
		
	}// end toString

	// compare to based on isbn
	public int compareTo(Book other) {
		
		return iSBN.compareTo(other.iSBN);
		
	}// end compareTo

	@Override 
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (iSBN == null) {
			if (other.iSBN != null)
				return false;
		} else if (!iSBN.equals(other.iSBN))
			return false;
		return true;
	}

	// adds author to existing list of author
	// verifies that it is not already there before adding
	//does not throw exception if already there just ignores
	public void addAuthor(Person author) {
		
		if (!authors.contains(author)) {// ???what is it checking for when it checks if it contains
			authors.add(author);
		}
		
	}

	// this setter adds a single category  to an existing category list
	// it verifies it does not already exist before adding
	//does not throw exception if already in list, just ignores
	public void addCategory(BookCategory category) {
		
		if (!subjectCategories.contains(category)) {
			subjectCategories.add(category);
		}
		
	}

	//adds categories to an existing category list
	// runs through array of categories to and checks each one against list of old
	//does not throw exception if already in list just ignores
	public void addCategories(ArrayList<BookCategory> categories) {
		
		for (int counter = 0; counter < categories.size(); counter++) {// runs through arraylist of news
			if (!subjectCategories.contains(categories.get(counter))) { // checks against array list of olds
				subjectCategories.add(categories.get(counter));
			}
		}
		
	}
	
	//verifies isbns are valid according to ISBN rules
	private boolean checkISBN(String isbn) {
		
		int length = isbn.length();
		
		//10 digit ISBNs
		if (length == 10) {
			int total = 0;
			// loop goes through isbn to implement algorithm (continuously multiplying and
			// adding)
			for (int counter = 1; counter < 11; counter++) {
				total += counter * isbn.charAt(10 - counter);
			} // end for loop
			if (total % 11 == 0) {
				return true;
			}
		} 
		
		//13 digit isbn
		else if (length == 13) {
			int total1 = 0;
			int total2 = 0;
			for (int counter = 1; counter < 14; counter++) {
				if (counter % 2 == 1) {// if odd
					total1 += isbn.charAt(13 - counter);
				} else {// if even
					total2 += 3 * isbn.charAt(13 - counter);
				}
			}
			if ((total1 + total2) % 10 == 0) {
				return true;
			}
		} 

		// return false if isbn is neither 10 nor 13 or invalid
		return false;
	}
}
