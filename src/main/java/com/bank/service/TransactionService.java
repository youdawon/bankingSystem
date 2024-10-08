package com.bank.service;

import com.bank.account.BankAccount;
import com.bank.account.ConsumerCheckingAccount;
import com.bank.account.ConsumerSavingsAccount;

public class TransactionService {

  public void transfer(BankAccount fromAccount, BankAccount toAccount, double amount)
      throws Exception {

    BankAccount firstLock =
        fromAccount.getAccountNumber() < toAccount.getAccountNumber() ? fromAccount : toAccount;
    BankAccount secondLock =
        fromAccount.getAccountNumber() < toAccount.getAccountNumber() ? toAccount : fromAccount;

    try {
      firstLock.getLock().lock();
      secondLock.getLock().lock();

      if (fromAccount.getBalance() < amount) {
        throw new Exception("Not enough balance");
      }
      withdraw(fromAccount, amount);
      deposit(toAccount, amount);

    } catch (Exception e) {
      try {
        deposit(fromAccount, amount);
      } catch (Exception rollbackException) {
        throw new Exception("Transfer failed and rollback also failed.", rollbackException);
      }
      throw new Exception("Transfer failed and has been rolled back.", e);
    } finally {
      secondLock.getLock().unlock();
      firstLock.getLock().unlock();
    }
  }

  public void deposit(BankAccount bankAccount, double amount) throws Exception {
    try {
      bankAccount.getLock().lock();
      bankAccount.setBalance(bankAccount.getBalance() + amount);
    } catch (Exception e) {
      throw new Exception(
          "Deposit failed for account " + bankAccount.getAccountNumber() + " with amount " + amount,
          e);
    } finally {
      bankAccount.getLock().unlock();
    }
  }

  public void withdraw(BankAccount bankAccount, double amount) throws Exception {
    try {
      bankAccount.getLock().lock();

      if (bankAccount.getBalance() < amount) {
        throw new Exception("Not enough balance in an account.");
      }
      bankAccount.setBalance(bankAccount.getBalance() - amount);
    } catch (Exception e) {
      throw new Exception(
          "withdraw failed for account " + bankAccount.getAccountNumber() + " with amount "
              + amount, e);
    } finally {
      bankAccount.getLock().unlock();
    }
  }

  public double useCheckCard(ConsumerCheckingAccount bankAccount, double amount) throws Exception {
    try {
      bankAccount.getLock().lock();

      if (amount > bankAccount.getCardLimit()) {
        throw new Exception("the amount is more than card limit.");
      }
      if (bankAccount.getBalance() < amount) {
        throw new Exception("Not enough balance in an account.");
      }
      withdraw(bankAccount, amount);
    } catch (Exception e) {
      throw new Exception(
          "useCheckCard failed for account " + bankAccount.getAccountNumber() + " with amount "
              + amount, e);
    } finally {
      bankAccount.getLock().unlock();
    }
    return bankAccount.getBalance();
  }


  public double applyInterest(ConsumerSavingsAccount bankAccount) throws Exception {

    double interest = bankAccount.getBalance() * (bankAccount.getInterestRate() / 100);

    try {
      bankAccount.getLock().lock();
      deposit(bankAccount, interest);
    } catch (Exception e) {
      throw new Exception(
          "applyInterest failed for account " + bankAccount.getAccountNumber() + " with interest "
              + interest, e);
    } finally {
      bankAccount.getLock().unlock();
    }
    return interest;
  }
}
