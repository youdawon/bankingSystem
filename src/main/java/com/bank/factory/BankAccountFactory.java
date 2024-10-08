package com.bank.factory;

import com.bank.account.AccountHolder;
import com.bank.account.BankAccount;
import com.bank.account.ConsumerCheckingAccount;
import com.bank.account.CommercialSavingAccount;
import com.bank.account.ConsumerSavingsAccount;
import com.bank.stretegy.CheckingStrategy;
import com.bank.stretegy.CommercialSavingsStrategy;
import com.bank.stretegy.SavingsStrategy;
import com.bank.util.Constant.AccountType;

public class BankAccountFactory {

  public static BankAccount openBankAccount(Long accountNumber, AccountHolder accountHolder,
      int pinNumber, double amount, double cardLimit, double interestRate,
      AccountType accountType) throws Exception {

    switch (accountType) {
      case CHECKING -> {
        return ConsumerCheckingAccount.builder()
            .accountHolder(accountHolder)
            .accountNumber(accountNumber)
            .pinNumber(pinNumber)
            .balance(amount)
            .interestRate(interestRate)
            .accountStrategy(new CheckingStrategy()).build();
      }
      case SAVING -> {
        return ConsumerSavingsAccount.builder()
            .accountHolder(accountHolder)
            .accountNumber(accountNumber)
            .pinNumber(pinNumber)
            .balance(amount)
            .interestRate(interestRate)
            .accountStrategy(new SavingsStrategy()).build();
      }
      case COMMERCIAL_SAVING -> {
        return CommercialSavingAccount.builder()
            .accountHolder(accountHolder)
            .accountNumber(accountNumber)
            .pinNumber(pinNumber)
            .balance(amount)
            .interestRate(interestRate)
            .accountStrategy(new CommercialSavingsStrategy()).build();
      }
      default -> throw new Exception("Unknown account type");
    }

  }
}
