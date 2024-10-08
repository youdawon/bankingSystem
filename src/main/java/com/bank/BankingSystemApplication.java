package com.bank;

import javax.naming.CompositeName;

import com.bank.account.BankAccount;
import com.bank.account.Company;
import com.bank.account.History;
import com.bank.account.Person;
import com.bank.service.AccountHistoryService;
import com.bank.service.AccountService;
import com.bank.service.BankService;
import com.bank.service.TransactionService;
import com.bank.util.Constant;

public class BankingSystemApplication {

  public static void main(String[] args) {
    try {
      BankService bankService = new BankService(new AccountService(), new TransactionService(),
          new AccountHistoryService());
      BankAccount bankAccount = bankService.createAccount(new Person("Dawon", "You", 123), 1234,
          10000, 100000, 0,
          Constant.AccountType.SAVING);
      bankService.deposit(bankAccount.getAccountNumber(), 10);
      bankService.withdraw(bankAccount.getAccountNumber(), 1234);
      bankService.applyInterest(bankAccount.getAccountNumber());
      BankAccount bankAccount2 = bankService.createAccount(new Person("Sumin", "You", 123), 1234,
          100, 100000, 0,
          Constant.AccountType.SAVING);
      bankService.transfer(bankAccount.getAccountNumber(), bankAccount2.getAccountNumber(), 4000);
      System.out.println("accoutNumber : " + bankAccount.getAccountNumber());
      for (History history : bankService.getAccountHistoryList(bankAccount.getAccountNumber(),
          null)) {
        System.out.println(history.toString());
      }
      System.out.println("--------------------------");
      System.out.println("accoutNumber : " + bankAccount2.getAccountNumber());
      for (History history : bankService.getAccountHistoryList(bankAccount2.getAccountNumber(),
          null)) {
        System.out.println(history.toString());
      }
      BankAccount bankAccount3 = bankService.createAccount(new Company("leetcode", 123), 1234,
          10000, 0, 5, Constant.AccountType.COMMERCIAL_SAVING);

      System.out.println("--------------------------");
      System.out.println("accoutNumber : " + bankAccount3.getAccountNumber());
      for (History history : bankService.getAccountHistoryList(bankAccount3.getAccountNumber(),
          null)) {
        System.out.println(history.toString());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
