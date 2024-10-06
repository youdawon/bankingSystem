package com.bank.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import com.bank.account.BankAccount;
import com.bank.account.History;
import com.bank.account.Person;
import com.bank.util.Constant;

public class AccountHistoryService {


	public  void updateAccountHistory(BankAccount bankAccount, Constant.TransactionType type, double amount, double balance, Long creditedAccountNumber) {
		Long historyId = bankAccount.getHistoryId().incrementAndGet();

		Optional<Long> credited = Optional.ofNullable(creditedAccountNumber);
		History history = credited.map(his -> new History(historyId, type, amount, balance, creditedAccountNumber))
			.orElse(new History(historyId, type, amount, balance));

		synchronized(this) {
			bankAccount.getAccountHistory().put(historyId, history);
		}
	}

	public ArrayList<History> getAccountHistoryList(BankAccount bankAccount, Long historyId) throws Exception {
		if (historyId == null) {
			return new ArrayList<History>(bankAccount.getAccountHistory().values());
		}
		History history;
		synchronized(this) {
			history = bankAccount.getAccountHistory().get(historyId);
		}
		if(history == null){
			throw  new Exception("history Id : " + historyId + " not found.");
		}
		return new ArrayList<>(Collections.singletonList(history));
	}

}
