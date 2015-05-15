package com.observer.subscriber;

import com.account.Account;
import com.observer.financial_tran.FinancialTransactionObserver;
import com.observer.interfaces.*;

public class TransactionSubscriber implements TransactionSubscriberInterface {

	private Account acc;
	private FinancialTransactionObserver observer1, observer2;
	private boolean lock1 = false;
	private boolean lock2 = false;

	public void update() {
		synchronized (this) {
			if (observer1.getSubscribers().contains(this) && !observer1.isLock()) {
				observer1.setLock(true);
				this.lock1 = true;
			}
			if (observer2.getSubscribers().contains(this) && !observer2.isLock()) {
				observer2.setLock(true);
				this.lock2 = true;
			}

			if (lock1 && lock2) {
				acc.handle();
				observer1.unregister(this);
				observer1.setLock(false);
				observer2.unregister(this);
				observer2.setLock(false);
				observer1.update();
				observer2.update();
			}
		}
	}

	public TransactionSubscriber(FinancialTransactionObserver newObserver1,
			FinancialTransactionObserver newObserver2, Account acc) {
		observer1 = newObserver1;
		observer2 = newObserver2;
		this.acc = acc;
		synchronized (this) {
			observer1.register(this);
			observer2.register(this);
		}
	}

}
