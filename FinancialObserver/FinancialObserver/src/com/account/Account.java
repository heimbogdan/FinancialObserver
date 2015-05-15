package com.account;

public class Account {
	public String accNo;
	public int id;

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	
	public void handle(){
		System.out.println(id);
	}
	
}
