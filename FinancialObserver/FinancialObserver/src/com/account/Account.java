package com.account;

public class Account {

	private int id;
	private String accNo;
	public Account(String accNo, int id) {
		super();
		this.id = id;
		this.accNo = accNo;
	}
	
	public void handle(){
		System.out.println(id);
	}
}
