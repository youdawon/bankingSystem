package com.bank.account;

import com.bank.stretegy.AccountStrategy;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

import com.bank.util.Constant;

public class BankAccount {

  protected final AccountHolder accountHolder;
  protected final Long accountNumber;
  protected int pinNumber;
  protected double balance;
  protected Map<Long, History> accountHistory;
  protected AtomicLong historyId;
  protected final ReentrantLock lock;
  protected double interestRate;
  protected AccountStrategy accountStrategy;


  protected BankAccount(AccountHolder accountHolder, Long accountNumber, int pinNumber,
      double amount,
      double interestRate, AccountStrategy accountStrategy) {
    this.accountNumber = accountNumber;
    this.accountHolder = accountHolder;
    this.pinNumber = pinNumber;
    this.balance = amount;
    this.accountHistory = new LinkedHashMap<>();
    this.historyId = new AtomicLong(Constant.UNIQUE_NUMBER_INITIAL_VALUE);
    this.lock = new ReentrantLock();
    this.interestRate = interestRate;
    this.accountStrategy = accountStrategy;
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

  public ReentrantLock getLock() {
    return lock;
  }

  public double getInterestRate() {
    return interestRate;
  }

  public void setInterestRate(double interestRate) {
    this.interestRate = interestRate;
  }

  public AccountStrategy getAccountStrategy() {
    return accountStrategy;
  }

  public void setAccountStrategy(AccountStrategy accountStrategy) {
    this.accountStrategy = accountStrategy;
  }
}
