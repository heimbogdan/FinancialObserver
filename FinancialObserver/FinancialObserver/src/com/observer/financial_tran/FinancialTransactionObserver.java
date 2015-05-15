package com.observer.financial_tran;

import com.observer.GenaralFinancialObserver;
import com.observer.subscriber.TransactionSubscriber;

public class FinancialTransactionObserver extends
		GenaralFinancialObserver<TransactionSubscriber> {

	private boolean lock = false;

	public boolean isLock() {
		return lock;
	}

	public void setLock(boolean lock) {
		this.lock = lock;
	}

	@Override
	public void register(TransactionSubscriber subscriber) {
		this.getSubscribers().add(subscriber);
	}

	@Override
	public void unregister(TransactionSubscriber subscriber) {
		this.getSubscribers().remove(subscriber);
	}

	@Override
	public void update() {
		if (this.getSubscribers().size() > 0) {
			this.getSubscribers().get(0).update();
		}
	}

}
