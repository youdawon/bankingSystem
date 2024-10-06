package com.bank.account;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.bank.util.Constant;

public class History {

	private final Long historyId;
	private final Constant.TransactionType type;
	private final double amount;
	private final LocalDateTime localDateTime;
	private final double balance;
	private Long creditedAccountNumber;

	public History(Long historyId, Constant.TransactionType type, double amount, double balance){
		this.historyId = historyId;
		this.type = type;
		this.amount = amount;
		this.balance = balance;
		this.localDateTime = LocalDateTime.now();
	}

	public History(Long historyId, Constant.TransactionType type, double amount, double balance, Long creditedAccountNumber){
		this.historyId = historyId;
		this.type = type;
		this.amount = amount;
		this.balance = balance;
		this.localDateTime = LocalDateTime.now();
		this.creditedAccountNumber = creditedAccountNumber;
	}

	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return "History{" +
			"historyId=" + historyId +
			", type=" + type +
			", amount=" + amount +
			", localDateTime=" + localDateTime.format(formatter) +
			", balance=" + balance +
			'}';
	}
}
