package com.shabs.zUnimplemented;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TODO UNIMPLEMENTED
 * FREQUENCY map for each char
 *
 * Find length of longest substring which has at least K repeating characters
 *
 * s = "aaabb", k = 3 =>> length = 3
 * s = "ababbc", k = 2 =>> length = 5
 *
 * - create frequency map for the chars in the string
 * - loop thru chars to find the lowest index,
 * ie index of char in the frequency with frequency >= K
 * - loop thru chars to find the highest index
 */
public class LongestSubstringWithRepeatingCharacters_KTimes {

  public int find(String input, int k) {
    int maxSubstringLength = 0;

    return 0;
  }

  @Test
  public void test1() {
    String input = "aaabb";
    int k = 3;

    int expected = 3;
    int actual = find(input, k);

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void test2() {
    String input = "ababbc";
    int k = 2;

    int expected = 5;
    int actual = find(input, k);

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void test3() {
    String input = "abdabbc";
    int k = 2;

    int expected = 2;
    int actual = find(input, k);

    Assert.assertEquals(actual, expected);
  }
}
