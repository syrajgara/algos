package com.shabs.iteration;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * BINARY SEARCH
 *
 * find sqrt of x
 * <p>
 * Binary Search to find the sqrt of a number
 *
 * SQRT of number can be anywhere between 1 and n/2
 * 1/2 for 4, 1/3 for 9 etc
 */
public class SQRT {

  public int sqrt(int x) {
    if (x <= 1) {
      return x;
    }

    int low = 2;
    int high = x / 2;

    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (mid * mid == x) {
        return mid;
      }

      if (mid * mid < x) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }

    return -1;
  }

  @Test
  public void test1() {
    int x = 0;
    int expected = 0;
    int actual = sqrt(x);
    Assert.assertSame(actual, expected);
  }

  @Test
  public void test2() {
    int x = 1;
    int expected = 1;
    int actual = sqrt(x);
    Assert.assertSame(actual, expected);
  }

  @Test
  public void test21() {
    int x = 2;
    int expected = -1;
    int actual = sqrt(x);
    Assert.assertSame(actual, expected);
  }

  @Test
  public void test22() {
    int x = 3;
    int expected = -1;
    int actual = sqrt(x);
    Assert.assertSame(actual, expected);
  }

  @Test
  public void test23() {
    int x = 4;
    int expected = 2;
    int actual = sqrt(x);
    Assert.assertSame(actual, expected);
  }

  @Test
  public void test3() {
    int x = 100;
    int expected = 10;
    int actual = sqrt(x);
    Assert.assertSame(actual, expected);
  }

  @Test
  public void test4() {
    int x = 25;
    int expected = 5;
    int actual = sqrt(x);
    Assert.assertSame(actual, expected);
  }

  @Test
  public void test5() {
    int x = 17;
    int expected = -1;
    int actual = sqrt(x);
    Assert.assertSame(actual, expected);
  }
}
