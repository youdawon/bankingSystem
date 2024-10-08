package com.bank.account;

import com.bank.stretegy.AccountStrategy;
import java.util.HashSet;
import java.util.Set;

public class CommercialCheckingAccount extends BankAccount {

  Set<Person> authorizedUsers;

  public CommercialCheckingAccount(AccountHolder accountHolder, Long accountNumber, int pinNumber,
      double amount, double interestRate, AccountStrategy accountStrategy) {
    super(accountHolder, accountNumber, pinNumber, amount, interestRate, accountStrategy);
    authorizedUsers = new HashSet<>();
    if (super.getInterestRate() == 0) {
      super.setInterestRate(super.getAccountStrategy().applyInterest());
    }
  }

  public void addAuthorizedUser(Person person) {
    synchronized (this) {
      this.authorizedUsers.add(person);
    }
  }

  public static class Builder {

    private AccountHolder accountHolder;
    private Long accountNumber;
    private int pinNumber;
    private double balance;
    private double interestRate;
    private AccountStrategy accountStrategy;

    public Builder accountHolder(AccountHolder accountHolder) {
      this.accountHolder = accountHolder;
      return this;
    }

    public Builder accountNumber(Long accountNumber) {
      this.accountNumber = accountNumber;
      return this;
    }

    public Builder pinNumber(int pinNumber) {
      this.pinNumber = pinNumber;
      return this;
    }

    public Builder balance(double balance) {
      this.balance = balance;
      return this;
    }

    public Builder interestRate(double interestRate) {
      this.interestRate = interestRate;
      return this;
    }

    public Builder accountStrategy(AccountStrategy accountStrategy) {
      this.accountStrategy = accountStrategy;
      return this;
    }

    public CommercialCheckingAccount build() {
      return new CommercialCheckingAccount(accountHolder, accountNumber, pinNumber, balance,
          interestRate, accountStrategy);
    }
  }

  public static Builder builder() {
    return new Builder();
  }
}
