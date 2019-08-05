package com.shabs.recurssion;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * x power of n
 * <p>
 * - take care of negative - 1/x -n
 * - if even n, recursive call x*x and n/2
 * - if odd n, x * recursive call x*x and n/2
 */
public class POW {

  public int pow(int x, int n) {
    if (n == 0) {
      return 1;
    }

    /*
    // doesnt work for negative exponent
    if (n < 0) {
      n = -n;
      x = 1 / x;
    }
    */

    if (n % 2 == 0) {
      return pow(x * x, n / 2);
    }

    return x * pow(x * x, n / 2);
  }

  @Test
  public void test1() {
    int x = 3;
    int n = 0;

    int expected = 1;
    int actual = pow(x, n);
    Assert.assertEquals(actual, expected);
  }

  @Test
  public void test2() {
    int x = 3;
    int n = 1;

    int expected = 3;
    int actual = pow(x, n);
    Assert.assertEquals(actual, expected);
  }

  @Test
  public void test3() {
    int x = 3;
    int n = 4;

    int expected = 81;
    int actual = pow(x, n);
    Assert.assertEquals(actual, expected);
  }

  @Test
  public void test4() {
    int x = 3;
    int n = 3;

    int expected = 27;
    int actual = pow(x, n);
    Assert.assertEquals(actual, expected);
  }

  //@Test
  public void test5() {
    int x = 2;
    int n = -2;

    double expected = 0.25;
    int actual = pow(x, n);
    Assert.assertEquals(actual, expected);
  }
}
