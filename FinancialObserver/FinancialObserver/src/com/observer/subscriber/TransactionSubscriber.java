package com.observer.subscriber;

import com.observer.financial_tran.FinancialTransactionObserver;
import com.observer.interfaces.*;

public class TransactionSubscriber implements TransactionSubscriberInterface {
	
	public FinancialTransactionObserver observer1;
	public boolean lock;
	
	public void update(){
		if(!observer1.isLock()){
			this.lock=true;
			observer1.setLock(true);
		}
		
		
	}
	
}
