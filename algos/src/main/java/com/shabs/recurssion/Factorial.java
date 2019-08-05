package com.shabs.recurssion;

import org.testng.annotations.Test;

public class Factorial {

  public static int computeRecurssive(int n) {
    if (n == 0) {
      return 1;
    }

    return n * computeRecurssive(n - 1);
  }

  public static int computeTailRecursive(int n, int previousFactorial) {
    if (n <= 1) {
      return previousFactorial;
    }

    return computeTailRecursive(n - 1, n * previousFactorial);
  }

  public static int computeIterative(int n) {
    int factorial = 1;

    for (int i=2; i<= n; i++) {
      factorial *= i;
    }

    return factorial;
  }

  @Test
  public void test() {
    long startTime = System.nanoTime();

    System.out.println("Factorial for 0: " + computeRecurssive(0));
    System.out.println("Factorial for 1: " + computeRecurssive(1));
    System.out.println("Factorial for 2: " + computeRecurssive(2));
    System.out.println("Factorial for 3: " + computeRecurssive(3));
    System.out.println("Factorial for 4: " + computeRecurssive(4));
    System.out.println("Factorial for 10: " + computeRecurssive(10));

    System.out.println("### Time Recursive: " + (System.nanoTime() - startTime));

    startTime = System.nanoTime();

    System.out.println("Factorial for 0: " + computeTailRecursive(0, 1));
    System.out.println("Factorial for 1: " + computeTailRecursive(1, 1));
    System.out.println("Factorial for 2: " + computeTailRecursive(2, 1));
    System.out.println("Factorial for 3: " + computeTailRecursive(3, 1));
    System.out.println("Factorial for 4: " + computeTailRecursive(4, 1));
    System.out.println("Factorial for 10: " + computeTailRecursive(10, 1));

    System.out.println("### Time Tail Recursive: " + (System.nanoTime() - startTime));

    startTime = System.nanoTime();

    System.out.println("Factorial for 0: " + computeIterative(0));
    System.out.println("Factorial for 1: " + computeIterative(1));
    System.out.println("Factorial for 2: " + computeIterative(2));
    System.out.println("Factorial for 3: " + computeIterative(3));
    System.out.println("Factorial for 4: " + computeIterative(4));
    System.out.println("Factorial for 10: " + computeIterative(10));

    System.out.println("### Time Iterative: " + (System.nanoTime() - startTime));
  }

}
