package com.observer.subscriber;

import com.account.Account;
import com.observer.financial_tran.FinancialTransactionObserver;
import com.observer.interfaces.*;

public class TransactionSubscriber implements TransactionSubscriberInterface {

	private Account acc;
	private FinancialTransactionObserver observer1;

	public void update() {
		synchronized (observer1) {
			if (!observer1.isLock()) {
				observer1.setLock(true);
				acc.handle();
				observer1.unregister(this);
				observer1.setLock(false);
			}
			observer1.update();
		}

		
	}

	public TransactionSubscriber(FinancialTransactionObserver newObserver1,
			Account acc) {
		observer1 = newObserver1;
		this.acc = acc;
		synchronized (observer1) {
			observer1.register(this);
		}
	}

}
