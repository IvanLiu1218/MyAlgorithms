package com.ivanliu.learn;

public class TrySynchronized {
	
	public void method1() {
		synchronized(this) {
			System.out.println("try synchronized");
		}
	}
	
	public synchronized void method2() {
		System.out.println("synchronized method");
	}
}
