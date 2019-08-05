package com.shabs.datastructures.string;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ReverseInteger {

  private int reverseInt(int x) {
    int result = 0;

    while (x != 0) {
      int tail = x % 10;
      int newResult = result * 10 + tail;

      // int overflow - if we cant get the result by reversing above operation ... return zero
      if ((newResult - tail) / 10 != result) {
        return 0;
      }

      result = newResult;
      x = x / 10;
    }

    return result;
  }

  @Test
  public void test() {
    int input = 123;
    int expected = 321;

    int actual = reverseInt(input);

    Assert.assertEquals(actual, expected);
  }
}
