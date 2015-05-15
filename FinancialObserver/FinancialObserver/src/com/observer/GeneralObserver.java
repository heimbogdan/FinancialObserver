package com.observer;

public abstract class GeneralObserver {

	public abstract <T> void register(T subscriber);
	
	public abstract <T> void unregister(T subscriber);
	
	public abstract void update();
}
