package com.shabs.design;

import org.testng.annotations.Test;

public class SingletonPattern {
  private static SingletonPattern instance;

  private SingletonPattern() {
    // private so that no one else can instantiate this class
  }

  public static SingletonPattern getInstance() {
    if (instance == null) {
      synchronized (SingletonPattern.class) {
        if (instance == null) {
          instance = new SingletonPattern();
        }
      }
    }

    return instance;
  }

  public void someFunction() {
    System.out.println("TEST: running someFunction...done");
  }

  @Test
  public static void singleton() {
    SingletonPattern singletonPattern = SingletonPattern.getInstance();
    singletonPattern.someFunction();
  }
}
