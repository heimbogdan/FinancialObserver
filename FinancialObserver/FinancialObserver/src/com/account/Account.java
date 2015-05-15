package com.account;

public class Account {

	private int id;
	private String accNo1,accNo2;
	
	
	public String getAccNo1() {
		return accNo1;
	}

	public String getAccNo2() {
		return accNo2;
	}

	public Account(String accNo1,String accNo2, int id) {
		super();
		this.id = id;
		this.accNo1 = accNo1;
		this.accNo2 = accNo2;
	}
	
	public void handle(){
		System.out.println(Thread.currentThread().getName() + " - "+ id);
	}
}
