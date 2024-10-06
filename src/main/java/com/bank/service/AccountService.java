package com.bank.service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import com.bank.account.AccountHolder;
import com.bank.account.BankAccount;
import com.bank.account.CheckingAccount;
import com.bank.account.CommercialAccount;
import com.bank.account.CommercialSavingAccount;
import com.bank.account.Person;
import com.bank.account.SavingsAccount;
import com.bank.util.Constant;

public class AccountService {

	ConcurrentHashMap<Long, BankAccount> accounts = new ConcurrentHashMap<>();
	AtomicLong id = new AtomicLong(Constant.UNIQUE_NUMBER_INITIAL_VALUE);

	public BankAccount createAccount(AccountHolder accountHolder, int pinNumber, double amount, double cardLimit, double interestRate,  Constant.AccountType accountType) throws Exception {
		long accountNumber = this.id.incrementAndGet();

		BankAccount bankAccount;
		switch (accountType){
			case CHECKING -> bankAccount = new CheckingAccount(accountHolder, accountNumber, pinNumber, amount, cardLimit);
			case SAVING -> bankAccount = new SavingsAccount(accountHolder, accountNumber, pinNumber, amount, interestRate);
			case COMMERCIAL_SAVING -> bankAccount = new CommercialSavingAccount(accountHolder, accountNumber, pinNumber, amount, interestRate);
			default -> throw new Exception("Unknown account type");
		}
		return registerAccount(accountNumber, bankAccount);
	}

	public boolean authenticateUser(Long accountNumber, int pinNumber) throws Exception{
		BankAccount bankAccount = getBankAccount(accountNumber);
		return bankAccount.getPinNumber() == pinNumber;

	}

	public BankAccount getBankAccount(Long accountNumber) throws Exception{
		BankAccount bankAccount = this.accounts.get(accountNumber);
		if(bankAccount == null){
			throw new Exception("Account not Exist");
		}
		return bankAccount;
	}

	private BankAccount registerAccount(Long accountNumber, BankAccount account) {
		this.accounts.put(accountNumber, account);
		return this.accounts.get(accountNumber);
	}

	public double getBalance(Long accountNumber){
		return accounts.get(accountNumber).getBalance();
	}


	public void addAuthorizedUser(CommercialSavingAccount commercialSavingAccount, Person person){
		commercialSavingAccount.addAuthorizedUser(person);
	}
}
