package com.shabs.design;

import org.testng.annotations.Test;

public class MyThread extends Thread {

  private String name;

  public MyThread() {}

  public MyThread(String name) {
    this.name = name;
  }

  @Override
  public void run() {
    System.out.println("Running Thread " + name);
    try {
      Thread.sleep(1000);
      System.out.println("Done Running " + name);
    } catch (InterruptedException e) {
      // ignore
    }
  }

  @Test
  public void testDaemon() throws InterruptedException {
    Thread t1 = new MyThread("Number 1");
    t1.start();

    // without this, the main thread will finish before the thread1/2 has a chance to finish.
    Thread.sleep(2000);
  }
}
