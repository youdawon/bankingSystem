package com.bank.factory;

import com.bank.account.AccountHolder;
import com.bank.account.BankAccount;
import com.bank.account.CheckingAccount;
import com.bank.account.CommercialSavingAccount;
import com.bank.account.SavingsAccount;
import com.bank.util.Constant;

public class BankAccountFactory {

	public static BankAccount openBankAccount(Long accountNumber, AccountHolder accountHolder, int pinNumber, double amount, double cardLimit, double interestRate,  Constant.AccountType accountType) throws Exception {
		BankAccount bankAccount;
		switch (accountType){
			case CHECKING -> bankAccount = new CheckingAccount(accountHolder, accountNumber, pinNumber, amount, cardLimit);
			case SAVING -> bankAccount = new SavingsAccount(accountHolder, accountNumber, pinNumber, amount, interestRate);
			case COMMERCIAL_SAVING -> bankAccount = new CommercialSavingAccount(accountHolder, accountNumber, pinNumber, amount, interestRate);
			default -> throw new Exception("Unknown account type");
		}
		return bankAccount;
	}
}
