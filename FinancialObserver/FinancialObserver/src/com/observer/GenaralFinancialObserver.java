package com.observer;

import java.util.ArrayList;

public abstract class GenaralFinancialObserver<T> extends GeneralObserver<T> {
	
	private String accNo;
	private ArrayList<T> subscribers = new ArrayList<T>();
	
	public ArrayList<T> getSubscribers() {
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
