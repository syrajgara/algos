package com.shabs.dynamic.sum_this_or_that;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * MEMOIZE
 * Get N th fibonacci number
 */
public class Fibonacci {
  private Map<Integer, Integer> fibonacciSeries = new HashMap<>();

  public Fibonacci() {
    fibonacciSeries.put(1, 1);
    fibonacciSeries.put(2, 1);
  }

  public Integer compute(int n) {
    if (!fibonacciSeries.containsKey(n)) {
      fibonacciSeries.put(n, compute(n - 1) + compute(n - 2));
    }

    return fibonacciSeries.get(n);
  }

  @Test
  public void test() {
    // 0,1,1,2,3,5,8,13,21
    Fibonacci fibonacci = new Fibonacci();

    System.out.println("Fibonacci 1: " + fibonacci.compute(1));
    System.out.println("Fibonacci 2: " + fibonacci.compute(2));
    System.out.println("Fibonacci 3: " + fibonacci.compute(3));
    System.out.println("Fibonacci 4: " + fibonacci.compute(4));
    System.out.println("Fibonacci 5: " + fibonacci.compute(5));
    System.out.println("Fibonacci 46: " + fibonacci.compute(46));
    System.out.println("Fibonacci 47: " + fibonacci.compute(47)); // signed int overflow -- > 2^31
  }
}
