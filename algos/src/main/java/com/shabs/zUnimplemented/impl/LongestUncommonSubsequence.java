package com.shabs.zUnimplemented.impl;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * find longest uncommon length
 * basically you need length before the start of a subsequence
 * or after the end of the subsequence
 * whichever is bigger
 *
 * if no overlap subsequence - length = -1
 */
public class LongestUncommonSubsequence {

  public int findLUSlength(String a, String b) {
    if (a.length() > b.length()) {
      return find(b, a);
    }
    return find(a,b);
  }

  private int find(String s, String b) {
    int si = 0;
    int bi = 0;
    int max = 0;

    while (si < s.length() && bi < b.length()) {
      if (s.charAt(si) == b.charAt(bi)) {
        si++;
        if (max == 0) {
          max = bi;
        }
      }
      bi++;
    }

    if (si == s.length()) {
      max = Math.max(max, b.length() - bi);
    } else {
      max = -1;
    }

    return max;
  }

  @Test
  public void test() {
    int expected = 5;
    int actual = findLUSlength("aaaaabecxyz", "bc");
    Assert.assertEquals(actual, expected);
  }

  @Test
  public void test2() {
    int expected = 9;
    int actual = findLUSlength("aaaaabecxyzxyzxyz", "bc");
    Assert.assertEquals(actual, expected);
  }

  @Test
  public void test3() {
    int expected = -1;
    int actual = findLUSlength("abc", "cdc");
    Assert.assertEquals(actual, expected);
  }
}
