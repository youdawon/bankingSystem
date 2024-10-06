package com.bank.account;

public class Person extends AccountHolder{

	private final String firstName;
	private final String lastName;

	public Person(String firstName, String lastName, int idNumber) {
		super(idNumber);
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}
}
