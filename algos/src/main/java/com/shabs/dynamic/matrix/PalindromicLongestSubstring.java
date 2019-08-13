package com.shabs.dynamic.matrix;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * MATRIX boolean [n] [n]
 * init diagonal to true
 *
 * find one of the longest palindrome within a string - get length and starting location
 *
 * http://leetcode.com/2011/11/longest-palindromic-substring-part-i.html
 */
public class PalindromicLongestSubstring {
  private String palindrom(String input) {
    boolean[][] isPalindrom = new boolean[input.length()][input.length()];

    int startPosition = -1;
    int maxLength = Integer.MIN_VALUE;

    // base condition 1: stringLength=1 -- every char is palindrom of itself - mark them
    startPosition = 0;
    maxLength = 1;
    for (int i = 0; i < input.length(); i++) {
      isPalindrom[i][i] = true;
    }

        /*
              a b c b a
            a 1 0 0 0 1
            b   1 0 1 0 <<-- column for 2nd "b" is 1 and a=a, making the whole string a palindrom
            c     1 0 0
            b       1 0
            a         1
                 ^
                 | bottom half of matrix not used.
         */

    // stringLength=2, 3, 4, ... input.length()
    for (int stringLength = 2; stringLength <= input.length(); stringLength++) {

      // find if a char is palindrom of its stringLength'th neighbour?
      for (int row = 0; row <= input.length() - stringLength; row++) {

        // row is the left side char, col is the right side char
        int col = row + stringLength - 1;

        isPalindrom[row][col] = (input.charAt(row) == input.charAt(col));
        // characters at two ends of this string matches
        // check the string between them is also palindrom
        if ( stringLength > 2 && isPalindrom[row][col]) {
          isPalindrom[row][col] = isPalindrom[row + 1][col - 1];
        }

        if (isPalindrom[row][col]) {
          startPosition = row;
          maxLength = stringLength;
        }
      }
    }

    printMatrix(input, isPalindrom);

    return input.substring(startPosition, startPosition + maxLength);
  }

  private void printMatrix(String input, boolean[][] isPalindrom) {

    System.out.print("  ");
    for (int col = 0; col < input.length(); col++) {
      System.out.print(input.charAt(col) + " ");
    }
    System.out.println();

    for (int row = 0; row < isPalindrom[0].length; row++) {
      System.out.print(input.charAt(row) + " ");
      for (int col = 0; col < isPalindrom[0].length; col++) {
        if (row <= col) {
          System.out.print((isPalindrom[row][col] ? 1 : ".") + " ");
        } else {
          System.out.print("- ");
        }
      }
      System.out.println();
    }
  }

  @Test
  public void palindrom() {
    //String input = "abcba";
    //String expected = "abcba";
    //String input = "abba";
    //String expected = "abba";
    String input = "mabcbadeedabcbayxxx";
    String expected = "abcbadeedabcba";

    String longestPalindrome = palindrom(input);

    Assert.assertEquals(longestPalindrome, expected);
  }
}
