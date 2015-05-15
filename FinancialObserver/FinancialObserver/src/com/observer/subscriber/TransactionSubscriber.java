package com.observer.subscriber;

import com.observer.financial_tran.FinancialTransactionObserver;
import com.observer.interfaces.*;

public class TransactionSubscriber implements TransactionSubscriberInterface {
	
	public FinancialTransactionObserver observer1;
	public boolean lock;
	
	
	public void update(){
		synchronized(observer1){
			if(!observer1.isLock()){
				this.lock=true;
				observer1.setLock(true);
			}
		}
	}
	
	public TransactionSubscriber(FinancialTransactionObserver newObserver1){
		observer1= newObserver1;
		observer1.register(this);
	}
	
}
