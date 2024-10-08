package com.bank.account;

import com.bank.account.ConsumerSavingsAccount.Builder;
import com.bank.util.Constant.TransactionType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.bank.util.Constant;

public class History {

  private final Long historyId;
  private final Constant.TransactionType type;
  private final double amount;
  private final LocalDateTime transactionDateTime;
  private final double balance;
  private Long fromAccountNumber;
  private Long toAccountNumber;

  public History(Long historyId, Constant.TransactionType type, double amount, double balance) {
    this.historyId = historyId;
    this.type = type;
    this.amount = amount;
    this.balance = balance;
    this.transactionDateTime = LocalDateTime.now();
  }

  public History(Long historyId, Constant.TransactionType type, double amount, double balance,
      Long fromAccountNumber, Long toAccountNumber) {
    this.historyId = historyId;
    this.type = type;
    this.amount = amount;
    this.balance = balance;
    this.transactionDateTime = LocalDateTime.now();
    this.fromAccountNumber = fromAccountNumber;
    this.toAccountNumber = toAccountNumber;
  }

  public static class Builder {

    private Long historyId;
    private Constant.TransactionType type;
    private double amount;
    private LocalDateTime transactionDateTime;
    private double balance;
    private Long fromAccountNumber;
    private Long toAccountNumber;

    public Builder historyId(Long historyId) {
      this.historyId = historyId;
      return this;
    }

    public Builder type(Constant.TransactionType type) {
      this.type = type;
      return this;
    }

    public Builder amount(double amount) {
      this.amount = amount;
      return this;
    }

    public Builder transactionDateTime(LocalDateTime transactionDateTime) {
      this.transactionDateTime = transactionDateTime;
      return this;
    }

    public Builder balance(double balance) {
      this.balance = balance;
      return this;
    }

    public Builder fromAccountNumber(Long fromAccountNumber) {
      this.fromAccountNumber = fromAccountNumber;
      return this;
    }

    public Builder toAccountNumber(Long toAccountNumber) {
      this.toAccountNumber = toAccountNumber;
      return this;
    }

    public History build() {
      return new History(historyId, type, amount, balance, fromAccountNumber, toAccountNumber);
    }
  }

  public static ConsumerSavingsAccount.Builder builder() {
    return new ConsumerSavingsAccount.Builder();
  }

  @Override
  public String toString() {
    return "History{" +
        "historyId=" + historyId +
        ", type=" + type +
        ", amount=" + amount +
        ", transactionDateTime=" + transactionDateTime +
        ", balance=" + balance +
        ", fromAccountNumber=" + fromAccountNumber +
        ", toAccountNumber=" + toAccountNumber +
        '}';
  }
}
