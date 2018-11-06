package library;

import java.time.LocalDate;

public class Person {
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private Address address;

	public Person(String f, String l, Address add, String birthDate) {
		// call other constructor that accepts localDate
		this(f, l, add, LocalDate.parse(birthDate));
	}// end constructor 1

	public Person(String f, String l, String s, String c, String state, String zip, String birthDate) {
		// using the separated adress call standard constructor for this class by
		// calling the address constructor in it
		this(f, l, new Address(s, c, state, zip), LocalDate.parse(birthDate));
	}// end constructor 2

	public Person(String f, String l, Address add, LocalDate birthDate) {
		this.firstName = f;
		this.lastName = l;
		this.address = add;
		this.birthDate = birthDate;
	}// end constructor 3

	// copy constructor
	public Person(Person p) {
		Person pers = new Person(p.firstName, p.lastName, p.address, p.birthDate);
	}// end copy constructor

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Address getAddress() {
		return address;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		StringBuilder output = new StringBuilder("Name: " + firstName + " " + lastName);
		output.append("\nBirthdate: " + birthDate);
		output.append("\n" + address);
		return output.toString();
	}

	public int compareTo(Person p) {
		if (firstName.equalsIgnoreCase(p.firstName)) {
			if (lastName.equalsIgnoreCase(p.lastName)) {
				if (birthDate.equals(p.birthDate)) {
					return 0;
				} else if (birthDate.compareTo(p.birthDate) > 0) {
					return 1;
				} else if (birthDate.compareTo(p.birthDate) < 0) {
					return -1;
				}
			} else if (lastName.compareTo(p.lastName) > 0) {
				return 1;
			} else if (lastName.compareTo(p.lastName) < 0) {
				return -1;
			}
		} else if (firstName.compareTo(p.firstName) > 0) {
			return 1;
		}
		return -1;

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (birthDate == null) {
			if (other.birthDate != null)
				return false;
		} else if (!birthDate.equals(other.birthDate))
			return false;

		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;

		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}
}
