package com.bank.account;

import java.util.HashSet;
import java.util.Set;

public class CommercialAccount extends BankAccount{

	Set<Person> holders;

	public CommercialAccount(AccountHolder accountHolder, Long accountNumber, int pinNumber, double amount) {
		super(accountHolder, accountNumber, pinNumber, amount);
		holders = new HashSet<>();
	}
}
