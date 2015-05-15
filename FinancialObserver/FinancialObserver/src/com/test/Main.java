package com.test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.account.Account;
import com.observer.financial_tran.FinancialTransactionObserver;
import com.observer.subscriber.TransactionSubscriber;

public class Main {

	private static FinancialTransactionObserver fto;

	public static void main(String[] args) {
		fto = new FinancialTransactionObserver();

		Account a1 = new Account("100", 1);
		Account a2 = new Account("100", 1);
		Account a3 = new Account("100", 1);
		Account a4 = new Account("100", 2);
		Account a5 = new Account("100", 2);
		Account a6 = new Account("100", 2);
		Account a7 = new Account("100", 3);
		Account a8 = new Account("100", 3);
		Account a9 = new Account("100", 3);

		CyclicBarrier gate = new CyclicBarrier(4);
		ExecutorService es = Executors.newFixedThreadPool(3);

		Thread t1, t2, t3;

		t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					gate.await();
					new TransactionSubscriber(fto, a1);

					Thread.sleep(1);

					new TransactionSubscriber(fto, a2);
					Thread.sleep(1);
					new TransactionSubscriber(fto, a3);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					gate.await();

					new TransactionSubscriber(fto, a4);
					Thread.sleep(1);
					new TransactionSubscriber(fto, a5);
					Thread.sleep(1);
					new TransactionSubscriber(fto, a6);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		t3 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					gate.await();
					new TransactionSubscriber(fto, a7);
					Thread.sleep(1);
					new TransactionSubscriber(fto, a8);
					Thread.sleep(1);
					new TransactionSubscriber(fto, a9);
				} catch (Exception e) {
					// TODO Auto-generated catch block
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
