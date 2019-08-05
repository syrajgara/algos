package com.shabs.dynamic.matrix;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * MATRIX [pattern + 1] [string + 1]
 * init mat[0][0] = true
 *
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 * <p>
 * The matching should cover the entire input string (not partial).
 * <p>
 * isMatch("aa","a") → false
 * isMatch("aa","aa") → true
 * isMatch("aaa","aa") → false
 * isMatch("aa", "*") → true
 * isMatch("aa", "a*") → true
 * isMatch("ab", "?*") → true
 * isMatch("aab", "c*a*b") → false
 * <p>
 * - Create a matrix to track the String (+1) and the Pattern (+1)
 * - init (0,0) as true ... null char matches null pattern
 * - check (previous char and previous pattern) to decide the (next char and next pattern)
 * - + if pattern not ? or * - same char match
 * - + if pattern ? - no additional match needed
 * - + if pattern * - OR previous char match (without previous pattern)
 * <p>
 * - final output is at (last pattern, last char)
 */
public class WildcardMatching {

  public boolean isMatch(String input, String pattern) {

    boolean[][] matchMatrix = new boolean[pattern.length() + 1][input.length() + 1];

    // init
    matchMatrix[0][0] = true;

    for (int row = 1; row <= pattern.length(); row++) {
      char currPatternChar = pattern.charAt(row - 1);

      for (int col = 1; col <= input.length(); col++) {
        boolean previousPatternAndPreviousCharMatch = matchMatrix[row - 1][col - 1];

        if (currPatternChar == '?') {
          // will match if previous pattern+char match
          matchMatrix[row][col] = previousPatternAndPreviousCharMatch;
          continue;
        }

        if (currPatternChar == '*') {
          // will match if previous pattern+char match or previous char matches
          matchMatrix[row][col] = previousPatternAndPreviousCharMatch
                                  || matchMatrix[row][col - 1];
          continue;
        }

        if (currPatternChar == input.charAt(col - 1)) {
          // will match if previous pattern+char match and also current pattern + char match
          matchMatrix[row][col] = previousPatternAndPreviousCharMatch;
        }
      }
    }

    print(input, pattern, matchMatrix);

    return matchMatrix[pattern.length()][input.length()];
  }

  private void print(String input, String pattern, boolean[][] matchMatrix) {
    System.out.print("  N ");
    for (int i = 0; i < input.length(); i++) {
      System.out.print(input.charAt(i) + " ");
    }
    System.out.println();

    for (int row = 0; row < matchMatrix.length; row++) {
      if (row == 0) {
        System.out.print("N ");
      } else {
        System.out.print(pattern.charAt(row - 1) + " ");
      }
      for (int col = 0; col < matchMatrix[0].length; col++) {
        int val = matchMatrix[row][col] ? 1 : 0;
        System.out.print(val + " ");
      }
      System.out.println();
    }
    System.out.println("--------------------------");
  }

  @Test
  public void test1() {
    String input = "aa";
    String pattern = "a";

    boolean expected = false;
    boolean actual = isMatch(input, pattern);
    Assert.assertEquals(actual, expected);
  }

  @Test
  public void test2() {
    String input = "aa";
    String pattern = "aa";

    boolean expected = true;
    boolean actual = isMatch(input, pattern);
    Assert.assertEquals(actual, expected);
  }

  @Test
  public void test3() {
    String input = "aaa";
    String pattern = "aa";

    boolean expected = false;
    boolean actual = isMatch(input, pattern);
    Assert.assertEquals(actual, expected);
  }

  @Test
  public void test4() {
    String input = "aa";
    String pattern = "*";

    boolean expected = true;
    boolean actual = isMatch(input, pattern);
    Assert.assertEquals(actual, expected);
  }

  @Test
  public void test5() {
    String input = "aa";
    String pattern = "a*";

    boolean expected = true;
    boolean actual = isMatch(input, pattern);
    Assert.assertEquals(actual, expected);
  }

  @Test
  public void test6() {
    String input = "abcde";
    String pattern = "?*";

    boolean expected = true;
    boolean actual = isMatch(input, pattern);
    Assert.assertEquals(actual, expected);
  }

  @Test
  public void test7() {
    String input = "aab";
    String pattern = "c*a*b";

    boolean expected = false;
    boolean actual = isMatch(input, pattern);
    Assert.assertEquals(actual, expected);
  }
}
