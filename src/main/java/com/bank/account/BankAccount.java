package com.bank.account;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import com.bank.util.Constant;

public class BankAccount {

	private final AccountHolder accountHolder;
	private final Long accountNumber;
	private int pinNumber;
	private double balance;
	private Map<Long, History> accountHistory;
	private AtomicLong historyId;

	public BankAccount(AccountHolder accountHolder, Long accountNumber, int pinNumber, double amount) {
		this.accountNumber = accountNumber;
		this.accountHolder = accountHolder;
		this.pinNumber = pinNumber;
		this.balance = amount;
		this.accountHistory = new LinkedHashMap<>();
		this.historyId = new AtomicLong(Constant.UNIQUE_NUMBER_INITIAL_VALUE);
	}

	public double getBalance() {
		return this.balance;
	}

	public int getPinNumber() {
		return this.pinNumber;
	}

	public Long getAccountNumber() {
		return this.accountNumber;
	}

	public AccountHolder getAccountHolder() {
		return accountHolder;
	}

	public void setPinNumber(int pinNumber) {
		this.pinNumber = pinNumber;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Map<Long, History> getAccountHistory() {
		return accountHistory;
	}

	public void setAccountHistory(Map<Long, History> accountHistory) {
		this.accountHistory = accountHistory;
	}

	public AtomicLong getHistoryId() {
		return historyId;
	}

	public void setHistoryId(AtomicLong historyId) {
		this.historyId = historyId;
	}
}
