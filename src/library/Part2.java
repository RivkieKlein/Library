package library;
import java.util.*;

public class Part2 {
	public static void main(String[]args) {
		//this author will be used to test
		Address addr = new Address("Yellow", "Hudson", "New York", "12485" );
		Person rowling=new Person("J", "Rowling",addr, "1998-02-11" );
		//this will pass parameters of a real book so class should not throw exception
		Book real=null;
		Book fake=null;
		try {
	real =new Book("0545010225", "Harry Potter", rowling, BookCategory.FANTASY );
	
		System.out.println(real);//implicit call to toString
		System.out.println();
		real.addAuthor(rowling);//should not add cause already exists
		real.addAuthor(new Person("r", "klein", "Fromer","Catskill", "New York", "11204", "1998-02-11" ));
		real.addCategory(BookCategory.MYSTERY);//should add
		ArrayList<BookCategory> cats=new ArrayList<BookCategory>(2);
		cats.add(BookCategory.MYSTERY);
		cats.add(BookCategory.NOVEL);
		real.addCategories(cats);//should only add novel cause mystery is already there
		
		
		//now print again but will display another category and author
		System.out.println(real);
		System.out.println();
		//use getters to display, should say same info as toString wthout formatting (besides author will be formatted)
		System.out.println(real.getTitle());
		System.out.println(real.getISBN());
		System.out.println(real.getAuthors());//implicit call to toString of person Class
		System.out.println(real.getSubjectCategories());
		System.out.println();
		
		//fake book should throw invalid isbn error (isbn is only 12 long)
		
		
			
			fake=new Book("978870063162", "Harry Faker", rowling, BookCategory.FANTASY );	
		}
		catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
		}
	
		
		//test equals method should print false because the fake one is null because it was never instantiated cause error was thrown
		if(real.equals(fake)) {
			System.out.println("Same Book!");
		}
		else {
			System.out.println("Those objects are not the same");
		}
		
		Book real2=new Book("9781936523399", "Curious", rowling, BookCategory.FANTASY );
		System.out.println(real2);
	
		//compare 2 books both isbns should be valid
		int comparison= real.compareTo(real2);
		System.out.println(comparison);
	
		
	}//end main

}
