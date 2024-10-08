package com.bank.util;

public class Constant {

  public enum TransactionType {
    CREATE_ACCOUNT,
    DEPOSIT,
    WITHDRAWAL,
    INTEREST_APPLIED,
    CARD_USED,
    AUTHORIZED_USER_ADDED
  }

  public enum AccountType {
    CHECKING,
    SAVING,
    COMMERCIAL_SAVING,
    COMMERCIAL_CHECKING
  }

  public static long UNIQUE_NUMBER_INITIAL_VALUE = 0;
  public static double COMMERCIAL_CARD_LIMIT_DEFAULT = 10000000;
  public static double CONSUMER_CARD_LIMIT_DEFAULT = 100000;
  public static double COMMERCIAL_INTEREST_DEFAULT = 3;
  public static double CONSUMER_INTEREST_DEFAULT = 5;
}
