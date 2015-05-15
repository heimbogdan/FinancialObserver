package com.observer;

import java.util.ArrayList;

import com.observer.subscriber.TransactionSubscriber;

public abstract class GenaralFinancialObserver<T> extends GeneralObserver<T> {
	
	private String accNo;
	private ArrayList<TransactionSubscriber> subscribers = new ArrayList<TransactionSubscriber>();
	
	public ArrayList<TransactionSubscriber> getSubscribers() {
		return subscribers;
	}

	public String getAccNo(){
		return accNo;
	}
	
	@Override
	public abstract void register(T subscriber);
	
	@Override
	public abstract void unregister(T subscriber);

	@Override
	public abstract void update();
}
