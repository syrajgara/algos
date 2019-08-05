package com.shabs.design;

import org.testng.annotations.Test;

/**
 * public synchronized void myMethod() {...}
 * synchronized(this) {...}
 */
public class MyRunnable implements Runnable {

  private String name;

  public MyRunnable() {}

  public MyRunnable(String name) {
    this.name = name;
  }

  @Override
  public void run() {
    System.out.println("Running " + name);
    try {
      Thread.sleep(1000);
      System.out.println("Done Running " + name);
    } catch (InterruptedException e) {
      // ignore
    }
  }

  @Test
  public void testDaemon() throws InterruptedException {
    Thread t1 = new Thread(new MyRunnable("Number 1"));
    t1.start();

    // without this, the main thread will finish before the thread1/2 has a chance to finish.
    Thread.sleep(2000);
  }

  @Test
  public void testNonDaemon() throws InterruptedException {
    Thread t2 = new Thread(new MyRunnable("Number 2"));
    t2.setDaemon(false);
    t2.start();

    // BUG should wait for t2 to finish
  }
}
