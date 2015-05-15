package com.observer;

public abstract class GeneralObserver<T> {

	public abstract void register(T subscriber);
	
	public abstract void unregister(T subscriber);
	
	public abstract void update();

}
