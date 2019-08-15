package com.shabs.recurssion;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Cycle Detection - Floyd's Tortoise and hare
 * 2 Pointers
 * <p>
 * Give a number n - determine if the number is Happy
 * A number is happy if
 * - you do sum(square(individual digits of the number)) and after some iteration the result is 1.
 * - not happy - if you run into a loop, where you never reach 1
 * <p>
 * - function to get sum of squares of individual digits
 * - Use 2 pointers - 1st moving 1 step at a time, 2nd moving 2 steps at a time.
 * - eventually both pointers will be the same number
 * - if not loop this number will be 1, if loop it would be non-1 number
 */
public class HappyNumber {

  public boolean checkLoop(int n) {
    int fastSum = n;
    int slowSum = n;

    do {
      slowSum = getSumOfSquareOfDigits(slowSum);
      System.out.println("fastSum = " + fastSum);
      fastSum = getSumOfSquareOfDigits(fastSum);
      System.out.println("fastSum = " + fastSum);
      fastSum = getSumOfSquareOfDigits(fastSum);
      if (fastSum == 1) {
        return true;
      }
    } while (fastSum != slowSum);

    return false;
  }

  private int getSumOfSquareOfDigits(int n) {
    int sum = 0;

    while (n != 0) {
      int digit = n % 10;
      sum += digit * digit;
      n = n / 10;
    }

    return sum;
  }

  @Test
  public void testPositive() {
    int n = 19;
    boolean expected = true;
    boolean actual = checkLoop(n);

    Assert.assertEquals(actual, expected);
  }
}
