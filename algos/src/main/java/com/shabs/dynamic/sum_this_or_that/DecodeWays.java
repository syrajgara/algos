package com.shabs.dynamic.sum_this_or_that;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Given an encoded message, count the number of ways you can decode it.
 * <p>
 * Message Keys
 * A = 1, B = 2 ... Z = 26
 * Encoded Message = "12" => AB or L ... so 2 ways to decode "12"
 * <p>
 * - Since we are counting the number of (ways) valid decoded strings
 * - *** count=1 only at the last char of input string ***
 * - do not count every valid char as 1 way
 */
public class DecodeWays {

  private int countWays(String encoded, int currentIndex, String previousChar) {

    // exit condition
    if (currentIndex >= encoded.length()) {
      if (previousChar.equals("")) {
        // previous recursion have already formed correct decode strings
        return 1;
      }
      // previous recursion is relying on this call to provide valid decode string,
      // but if startIndex is greater than last index - this call is not going to be able to add any value
      return 0;
    }

    int count = 0;

    String currentChar = String.valueOf(encoded.charAt(currentIndex));

    if (previousChar.isEmpty()) {
      // option #1: use currentChar
      if (isValidChar(currentChar)) {
        count = countWays(encoded, currentIndex + 1, "");
      }

      // option #2: do not use currentChar - pass it on to next level
      count += countWays(encoded, currentIndex + 1, currentChar);
    } else {
      // single option: since you have to use previous char, and max code length is 2 chars (01 - 26)
      String previousPlusCurrent = previousChar + currentChar;

      if (isValidChar(previousPlusCurrent)) {
        count = countWays(encoded, currentIndex + 1, "");
      }
    }

    return count;
  }

  private boolean isValidChar(String key) {
    if (key.charAt(0) == '0') {
      // if the String is "02" cant take 2 only -- since 0 has to be used, in which case this is an invalid path
      return false;
    }

    Integer keyValue;
    try {
      keyValue = 64 + Integer.valueOf(key);
    } catch (NumberFormatException e) {
      keyValue = 0;
    }

    // A to Z
    return keyValue >= 65 && keyValue <= 90;
  }

  @Test
  public void test1() {
    String encoded = "1263";
    int expected = 3;
    int actual = countWays(encoded, 0, "");

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void test2() {
    String encoded = "102";
    int expected = 1;
    int actual = countWays(encoded, 0, "");

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void test3() {
    String encoded = "1002";
    int expected = 0;
    int actual = countWays(encoded, 0, "");

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void test4() {
    String encoded = "1902";
    int expected = 0;
    int actual = countWays(encoded, 0, "");

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void test5() {
    String encoded = "x1";
    int expected = 0;
    int actual = countWays(encoded, 0, "");

    Assert.assertEquals(actual, expected);
  }
}
