package com.bank.util;

public class Constant {

	public enum TransactionType {
		CREATE_ACCOUNT,
		DEPOSIT,
		WITHDRAWAL,
		TRANSFER,
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
}
