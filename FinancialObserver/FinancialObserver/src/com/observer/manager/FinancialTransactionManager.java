package com.observer.manager;

import java.util.ArrayList;

import com.account.Account;
import com.observer.financial_tran.FinancialTransactionObserver;
import com.observer.subscriber.TransactionSubscriber;

public class FinancialTransactionManager {

	private static FinancialTransactionManager instance;

	private ArrayList<FinancialTransactionObserver> observers;
	private ArrayList<String> accNumbers;

	private FinancialTransactionManager() {
		observers = new ArrayList<FinancialTransactionObserver>();
		accNumbers = new ArrayList<String>();
	}

	public static FinancialTransactionManager getInstance() {
		if (instance == null) {
			instance = new FinancialTransactionManager();
		}
		return instance;
	}

	public void register(Account acc) {
		synchronized (this) {
			FinancialTransactionObserver obs1, obs2;
			String accNo1 = acc.getAccNo1();
			String accNo2 = acc.getAccNo2();
			if (observers.size() == 0) {
				obs1 = newObserver(accNo1);
				obs2 = newObserver(accNo2);
			} else {
				int index1 = accNumbers.indexOf(accNo1);
				int index2 = accNumbers.indexOf(accNo2);
				obs1 = index1 != -1 ? observers.get(index1)
						: newObserver(accNo1);
				obs2 = index2 != -1 ? observers.get(index2)
						: newObserver(accNo2);
			}
			new TransactionSubscriber(obs1, obs2, acc);
		}
	}

	private FinancialTransactionObserver newObserver(String accNo) {
		FinancialTransactionObserver observer = new FinancialTransactionObserver(
				this);
		accNumbers.add(accNo);
		observers.add(observer);
		return observer;
	}

	public void removeObserver(FinancialTransactionObserver observer) {
		synchronized (this) {
			int index = observers.indexOf(observer);
			observers.remove(observer);
			accNumbers.remove(index);
		}
	}
}
