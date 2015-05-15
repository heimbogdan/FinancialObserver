package com.observer.financial_tran;

import com.observer.GenaralFinancialObserver;

import sun.security.x509.GeneralNameInterface;


public class FinancialTransactionObserver extends GenaralFinancialObserver{

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
		this.getSubscribers().get(0).update();
	}

	@Override
	public <T> void register(T subscriber) {
		
	}

	@Override
	public <T> void unregister(T subscriber) {
		
	}

	

}
