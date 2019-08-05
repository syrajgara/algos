package com.shabs.iteration;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Negate, abs, substract, multiply and divide by only using +
 *
 * Negate is the main function - others use negate and +
 */
public class MathOpUsingPlus {

  public int negate(int input) {
    int step = 1;
    if (input > 0) {
      step = -1;
    }

    int output = 0;

    while (input != 0) {
      input += step;
      output += step;
    }
    return output;
  }

  public int abs(int input) {
    return (input > 0) ? input : negate(input);
  }

  public int substract(int a, int b) {
    return (a + negate(b));
  }

  public int multiply(int a, int b) {
    if (a < b) {
      return multiply(b, a);
    }

    int output = 0;
    for (int i = 1; i <= abs(b); i++) {
      output += a;
    }

    if (b < 0 && a > 0) {
      //b is always < a ... else we would have switched it first thing in this method
      output = negate(output);
    }

    if (b < 0 && a < 0) {
      // output is currently negative - make it positive
      output = negate(output);
    }

    return output;
  }

  public String divide(int a, int b) {
    int quotient = 0;
    int sign = 1;

    if ((a < 0 && b > 0) || (a > 0 && b < 0)) {
      sign = -1;
    }

    while (abs(a) >= abs(b)) {
      a = substract(abs(a), abs(b));
      quotient++;
    }

    quotient = multiply(quotient, sign);
    int remainder = multiply(abs(a), sign);

    return "quotient=" + quotient + " remainder=" + remainder;
  }

  @Test
  public void negate() {
    int input = 5;
    int expected = -5;
    int actual = negate(input);
    Assert.assertEquals(actual, expected);
  }

  @Test
  public void abs() {
    int input = 5;
    int expected = 5;
    int actual = abs(input);
    Assert.assertEquals(actual, expected);
  }

  @Test
  public void absNegative() {
    int input = -5;
    int expected = 5;
    int actual = abs(input);
    Assert.assertEquals(actual, expected);
  }

  @Test
  public void substract() {
    int a = 5;
    int b = 2;
    int expected = 3;

    int actual = substract(a, b);
    Assert.assertEquals(actual, expected);
  }

  @Test
  public void multiply() {
    int a = 5;
    int b = 3;
    int expected = 15;

    int actual = multiply(a, b);
    Assert.assertEquals(actual, expected);
  }

  @Test
  public void multiply2() {
    int a = -5;
    int b = 3;
    int expected = -15;

    int actual = multiply(a, b);
    Assert.assertEquals(actual, expected);
  }

  @Test
  public void multiply3() {
    int a = 5;
    int b = -3;
    int expected = -15;

    int actual = multiply(a, b);
    Assert.assertEquals(actual, expected);
  }

  @Test
  public void multiply4() {
    int a = -5;
    int b = -3;
    int expected = 15;

    int actual = multiply(a, b);
    Assert.assertEquals(actual, expected);
  }

  @Test
  public void divide1() {
    int a = 15;
    int b = 3;
    String expected = "quotient=5 remainder=0";

    String actual = divide(a, b);
    Assert.assertEquals(actual, expected);
  }

  @Test
  public void divide2() {
    int a = 3;
    int b = 5;
    String expected = "quotient=0 remainder=3";

    String actual = divide(a, b);
    Assert.assertEquals(actual, expected);
  }

  @Test
  public void divide3() {
    int a = -3;
    int b = 5;
    String expected = "quotient=0 remainder=-3";

    String actual = divide(a, b);
    Assert.assertEquals(actual, expected);
  }

  @Test
  public void divide4() {
    int a = 3;
    int b = -5;
    String expected = "quotient=0 remainder=-3";

    String actual = divide(a, b);
    Assert.assertEquals(actual, expected);
  }

  @Test
  public void divide5() {
    int a = -3;
    int b = -5;
    String expected = "quotient=0 remainder=3";

    String actual = divide(a, b);
    Assert.assertEquals(actual, expected);
  }
}
