package com.bank.account;

import com.bank.account.ConsumerCheckingAccount.Builder;
import com.bank.stretegy.AccountStrategy;
import com.bank.stretegy.SavingsStrategy;

public class ConsumerSavingsAccount extends BankAccount {

  private SavingsStrategy savingsStrategy;

  private ConsumerSavingsAccount(AccountHolder accountHolder, Long accountNumber, int pinNumber,
      double amount,
      double interestRate, AccountStrategy accountStrategy) {
    super(accountHolder, accountNumber, pinNumber, amount, interestRate, accountStrategy);
    this.accountStrategy = accountStrategy;
    if (super.getInterestRate() == 0) {
      super.setInterestRate(super.getAccountStrategy().applyInterest());
    }
  }

  public static class Builder {

    private AccountHolder accountHolder;
    private Long accountNumber;
    private int pinNumber;
    private double balance;
    private double interestRate;
    private double cardLimit;
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

    public Builder cardLimit(double cardLimit) {
      this.cardLimit = cardLimit;
      return this;
    }

    public Builder accountStrategy(AccountStrategy accountStrategy) {
      this.accountStrategy = accountStrategy;
      return this;
    }

    public ConsumerSavingsAccount build() {
      return new ConsumerSavingsAccount(accountHolder, accountNumber, pinNumber, balance,
          interestRate, accountStrategy);
    }
  }

  public static Builder builder() {
    return new Builder();
  }
}
