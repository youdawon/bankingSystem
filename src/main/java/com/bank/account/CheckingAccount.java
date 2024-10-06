package com.bank.account;


public class CheckingAccount extends BankAccount {

	private double cardLimit;

	public CheckingAccount(AccountHolder accountHolder, Long accountNumber, int pinNumber, double amount, double cardLimit){
		super(accountHolder, accountNumber, pinNumber, amount);
		this.cardLimit = cardLimit;
	}

	public double getCardLimit() {
		return cardLimit;
	}

	public void setCardLimit(double cardLimit) {
		this.cardLimit = cardLimit;
	}
}
