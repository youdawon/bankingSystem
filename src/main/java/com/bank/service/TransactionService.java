package com.bank.service;

import com.bank.account.BankAccount;
import com.bank.account.CheckingAccount;
import com.bank.account.SavingsAccount;

public class TransactionService {

	public void transfer(BankAccount fromAccount, BankAccount toAccount, double amount) throws Exception{
		synchronized (this){
			if(fromAccount.getBalance() < amount){
				throw new Exception("Not enough balance");
			}
			withdraw(fromAccount, amount);
		}

		try {
			synchronized (this) {
				deposit(toAccount, amount);
			}
		}catch(Exception e){
			synchronized (this){
				deposit(fromAccount, amount);
			}
			throw new Exception("Transfer failed and has been rolled back.", e);
		}
	}

	public void deposit(BankAccount bankAccount, double amount){
		synchronized(this) {
			bankAccount.setBalance(bankAccount.getBalance() + amount);
		}
	}

	public void withdraw(BankAccount bankAccount, double amount) throws Exception{
		synchronized (this) {
			if (bankAccount.getBalance() < amount) {
				throw new Exception("Not enough balance in an account.");
			}
			bankAccount.setBalance(bankAccount.getBalance() - amount);
		}
	}

	public double useCheckCard(CheckingAccount bankAccount, double amount) throws Exception{
		synchronized(this) {
			if (amount > bankAccount.getCardLimit()) {
				throw new Exception("the amount is more than card limit.");
			}
			if (bankAccount.getBalance() < amount) {
				throw new Exception("Not enough balance in an account.");
			}
			withdraw(bankAccount, amount);
		}
		return bankAccount.getBalance();
	}


	public double applyInterest(SavingsAccount bankAccount) throws Exception {
		double interest = bankAccount.getBalance() * (bankAccount.getInterestRate() / 100);
		synchronized (this) {
			deposit(bankAccount, interest);
		}
		return interest;
	}
}
