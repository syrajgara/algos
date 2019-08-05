package com.shabs.iteration;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * factorial - find number of zeros in the answer
 * cant do factorial, since it will int overflow soon
 * also just need to find number of 5's (125 = 5 * 5 * 5 => 3 zeros)
 * since there are plenty of 2's (2, 4=2*2, ...) to multiple these 5's to make 10
 */
public class FindTrailingZeroInNFactorial {
  private static int findZerosInFactorial(int num) {
    int count = 0;

    if (num < 5) {
      return count;
    }

    // i = 5,25,125...
    for (int i = 5; i <= num; i *= 5) {
      // when i = 5, count all the fives between 5 and num [10 = 2*5 => 10 has one 5]
      // when i = 25, count all the double 5's 25=5*5 [50 = 2*25 => 50 has one 25]
      // when i = 125, count all the triple 5's 125=5*5*5 [250 = 2*125 => 250 has one 125]
      // and so on...
      count += num / i;
    }

    return count;
  }

  @Test
  public void findZerosInFactorial_12() {
    int num = 12;
    int expected = 2;

    int zeros = FindTrailingZeroInNFactorial.findZerosInFactorial(num);
    Assert.assertEquals(zeros, expected);
  }

  @Test
  public void findZerosInFactorial_26() {
    int num = 26;
    int expected = 6;

    int zeros = FindTrailingZeroInNFactorial.findZerosInFactorial(num);
    Assert.assertEquals(zeros, expected);
  }

  @Test
  public void findZerosInFactorial_50() {
    int num = 50;

    // need to check, should be 11??
    int expected = 12;

    int zeros = FindTrailingZeroInNFactorial.findZerosInFactorial(num);
    Assert.assertEquals(zeros, expected);
  }

  @Test
  public void findZerosInFactorial_250() {
    int num = 250;

    // need to check, should be 11??
    int expected = 62;

    int zeros = FindTrailingZeroInNFactorial.findZerosInFactorial(num);
    Assert.assertEquals(zeros, expected);
  }
}
