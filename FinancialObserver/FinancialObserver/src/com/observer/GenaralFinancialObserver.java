package com.observer;

import java.util.ArrayList;

public abstract class GenaralFinancialObserver extends GeneralObserver {
	
	private String accNo;
	private ArrayList<TransactionSubscriber> subscribers = new ArrayList<TransactionSubscriber>();
	
	public ArrayList<TransactionSubscriber> getSubscribers() {
		return subscribers;
	}

	public String getAccNo(){
		return accNo;
	}
	
	@Override
	public abstract void register(TransactionSubscriber subscriber);
	
	@Override
	public abstract void unregister(TransactionSubscriber subscriber);

	@Override
	public abstract void update();

}
