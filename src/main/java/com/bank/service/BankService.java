package com.bank.service;

import java.util.List;

import com.bank.account.AccountHolder;
import com.bank.account.BankAccount;
import com.bank.account.CheckingAccount;
import com.bank.account.CommercialSavingAccount;
import com.bank.account.Person;
import com.bank.account.SavingsAccount;
import com.bank.account.History;
import com.bank.util.Constant;

public class BankService {

	private AccountService accountService;
	private TransactionService transactionService;
	private AccountHistoryService accountHistoryService;

	public BankService(AccountService accountService, TransactionService transactionService, AccountHistoryService accountHistoryService){
		this.accountService = accountService;
		this.transactionService = transactionService;
		this.accountHistoryService = accountHistoryService;
	}

	public BankAccount createAccount(AccountHolder accountHolder, int pinNumber, double amount, double cardLimit, double interestRate,  Constant.AccountType accountType) throws Exception {
		BankAccount bankAccount = accountService.createAccount(accountHolder, pinNumber, amount, cardLimit, interestRate, accountType);
		updateAccountHistory(bankAccount, Constant.TransactionType.CREATE_ACCOUNT, amount, accountService.getBalance(bankAccount.getAccountNumber()), null);
		return bankAccount;
	}

	public boolean authenticateUser(Long accountNumber, int pinNumber) throws Exception{
		return accountService.authenticateUser(accountNumber, pinNumber);
	}

	public void transfer(Long from, Long to, double amount) throws Exception{
		BankAccount fromAccount = accountService.getBankAccount(from);
		BankAccount toAccount = accountService.getBankAccount(to);
		transactionService.transfer(fromAccount, toAccount, amount);
		updateAccountHistory(fromAccount, Constant.TransactionType.TRANSFER, amount, fromAccount.getBalance(),toAccount.getAccountNumber());
	}

	public void deposit(Long accountNumber, double amount) throws Exception{
		BankAccount bankAccount = accountService.getBankAccount(accountNumber);
		transactionService.deposit(bankAccount, amount);
		updateAccountHistory(bankAccount, Constant.TransactionType.DEPOSIT, amount, bankAccount.getBalance(), null);
	}

	public void withdraw(Long accountNumber, double amount) throws Exception{
		BankAccount bankAccount = accountService.getBankAccount(accountNumber);
		transactionService.withdraw(bankAccount, amount);
		updateAccountHistory(bankAccount, Constant.TransactionType.WITHDRAWAL, amount, bankAccount.getBalance(), null);
	}

	public void useCheckCard(Long accountNumber, double amount) throws Exception{
		BankAccount bankAccount = accountService.getBankAccount(accountNumber);
		if (bankAccount instanceof CheckingAccount) {
			transactionService.useCheckCard(((CheckingAccount)bankAccount), amount);
		} else {
			throw new Exception("This account does not support card usage.");
		}
		updateAccountHistory(bankAccount, Constant.TransactionType.CARD_USED, amount, bankAccount.getBalance(), null);
	}

	public double applyInterest(Long accountNumber) throws Exception{
		BankAccount bankAccount = accountService.getBankAccount(accountNumber);
		double interest = 0;
		if (bankAccount instanceof SavingsAccount) {
			interest = transactionService.applyInterest(((SavingsAccount)bankAccount));
		} else {
			throw new Exception("This account does not support card usage.");
		}
		updateAccountHistory(bankAccount, Constant.TransactionType.INTEREST_APPLIED, interest, bankAccount.getBalance(), null);
		return interest;
	}

	public List<History> getAccountHistoryList(Long accountNumber, Long historyId) throws Exception{
		BankAccount bankAccount = accountService.getBankAccount(accountNumber);
		return accountHistoryService.getAccountHistoryList(bankAccount, historyId);
	}

	public void addAuthorizedUser(Long accountNumber, Person person) throws Exception{
		BankAccount bankAccount = accountService.getBankAccount(accountNumber);
		if(bankAccount instanceof CommercialSavingAccount){
			accountService.addAuthorizedUser((CommercialSavingAccount)bankAccount, person);
		}
	}

	private void updateAccountHistory(BankAccount bankAccount, Constant.TransactionType type, double amount, double balance, Long creditedAccountNumber){
		accountHistoryService.updateAccountHistory(bankAccount, type, amount, balance, creditedAccountNumber);
	}


}
