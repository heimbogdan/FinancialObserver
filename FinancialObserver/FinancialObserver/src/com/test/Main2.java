package com.test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.account.Account;
import com.observer.manager.FinancialTransactionManager;
import com.observer.subscriber.TransactionSubscriber;

public class Main2 {

	private static FinancialTransactionManager manager = FinancialTransactionManager.getInstance();
	
	public static void main(String[] args) {
		
		Account a1 = new Account("1","2", 12);
		Account a2 = new Account("1","3", 13);
		Account a3 = new Account("2","3", 23);
		Account a4 = new Account("4","5", 45);
		Account a5 = new Account("3","1", 31);
		Account a6 = new Account("2","4", 24);
		Account a7 = new Account("3","2", 32);
		Account a8 = new Account("2","5", 25);
		Account a9 = new Account("5","1", 51);
		
		CyclicBarrier gate = new CyclicBarrier(4);
		ExecutorService es = Executors.newFixedThreadPool(3);

		Thread t1, t2, t3;
		
		t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					gate.await();
					manager.register(a1);
					Thread.sleep(1);
					manager.register(a2);
					Thread.sleep(1);
					manager.register(a3);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					gate.await();
					manager.register(a4);
					Thread.sleep(1);
					manager.register(a5);
					Thread.sleep(1);
					manager.register(a6);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		t3 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					gate.await();
					manager.register(a7);
					Thread.sleep(1);
					manager.register(a8);
					Thread.sleep(1);
					manager.register(a9);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		es.submit(t1);
		es.submit(t2);
		es.submit(t3);

		es.shutdown();
		try {
			gate.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			es.awaitTermination(1, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
