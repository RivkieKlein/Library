package library;

public class Address {
	
	private String street;
	private String city;
	private USState state;
	private String zipCode;
	
	//accepts street, city, state and zip code as strings
	public Address(String street, String city, String state, String zipCode) {
		this.street = street;
		this.city = city;
		
		int position = state.indexOf(" ");//position of space
		if (position >= 0) {
			state = state.substring(0, position) + state.substring(position + 1);//removes space
		}
		this.state = USState.valueOf(state.toUpperCase());//converts to enum
		
		this.zipCode = zipCode;
	}

	//accepts state as enum
	public Address(String street, String city, USState state, String zip) {
		this.street = street;
		this.city = city;
		this.state = state;

		zipCode = zip;
	}

	// street getter
	public String getStreet() {
		return street;
	}

	// city getter
	public String getCity() {
		return city;
	}

	// state getter
	public USState getState() {
		return state;
	}

	// zip getter
	public String getZip() {
		return zipCode;
	}

	@Override
	public String toString() {
		StringBuilder output = new StringBuilder("Address: ");
		output.append("\n " + street + " St.");
		output.append("\n" + city + ", " + state.getSymbol() + " " + zipCode);
		return output.toString();
	}
}
