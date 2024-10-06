package com.bank.account;

import java.util.HashSet;
import java.util.Set;

public class CommercialSavingAccount extends BankAccount{

	Set<Person> authorizedUsers;
	private double interestRate;

	public CommercialSavingAccount(AccountHolder accountHolder, Long accountId, int pinNumber, double amount, double interestRate) {
		super(accountHolder, accountId, pinNumber, amount);
		this.interestRate = interestRate;
		authorizedUsers = new HashSet<>();
	}

	public void setInterestRate(double interestRate){
		this.interestRate = interestRate;
	}

	public double getInterestRate(){
		return this.interestRate;
	}

	public void addAuthorizedUser(Person person) {
		synchronized (this) {
			this.authorizedUsers.add(person);
		}
	}
}
