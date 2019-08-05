package com.shabs.iteration;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * implement String.str(String haystack, String needle)
 */
public class StringStr {

  private int str(String haystack, String needle) {

    for (int i = 0; i < haystack.length(); i++) {
      if (haystack.charAt(i) == needle.charAt(0)) {
        // start char match, lets see if the rest matches
        if (matchSub(haystack, needle, i)) {
          // match found
          return i;
        }
      }
    }

    return -1;
  }

  private boolean matchSub(String haystack, String needle, int haystackIndex) {
    int needleIndex = 0;
    while (haystackIndex < haystack.length() && needleIndex < needle.length()) {
      if (haystack.charAt(haystackIndex) != needle.charAt(needleIndex)) {
        return false;
      }
      haystackIndex++;
      needleIndex++;
    }

    if (needleIndex != needle.length()) {
      return false;
    }

    return true;
  }

  @Test
  public void test() {
    String haystack = "find the needle in the haystack";
    String needle = "needle";

    int expected = 9;
    int actual = str(haystack, needle);

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void test2() {
    String haystack = "find the broken nee dle in the haystack";
    String needle = "needle";

    int expected = -1;
    int actual = str(haystack, needle);

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void test3() {
    String haystack = "find the at the end broken nee";
    String needle = "needle";

    int expected = -1;
    int actual = str(haystack, needle);

    Assert.assertEquals(actual, expected);
  }
}
