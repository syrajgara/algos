package com.shabs.dynamic.min_max_imize;

import org.testng.annotations.Test;

/**
 * Given two sequences, find the length of longest subsequence present in both of them
 * LCS for input Sequences “ABCDGH” and “AEDFHR” is “ADH” of length 3.
 * <p>
 * start from the last char of the 2 strings
 * on every iteration/recurrsive call
 * - check if char is same, +1 to the count and go to next char of both strings
 * - else max of (string1 - 1char) and (string2 - 1char)
 * <p>
 * no need to do both -1char together,
 * since next iteration will take care of comparing the -1char of both strings.
 * <p>
 * iterative approach will need to maintain a matrix of previous calculations.
 */
public class LongestCommonSubsequence {
  public static int lcsRecurssive(char[] A, int a, char[] B, int b) {
    int len;

    if (a == -1 || b == -1) {
      // reached the end
      //System.out.println("Index OUT: a=" + a + " b=" + b);
      len = 0;
    } else if (A[a] == B[b]) {
      //System.out.println("Index EQUAL: a=" + a + " b=" + b);
      len = 1 + lcsRecurssive(A, a - 1, B, b - 1);
    } else {
      //System.out.println("Index MISMATCH: a=" + a + " b=" + b);
      len = Math.max(lcsRecurssive(A, a - 1, B, b), lcsRecurssive(A, a, B, b - 1));
    }

    return len;
  }

  @Test
  public void test() {
    char[] A = {'a', 'b', 'c', 'b', 'd', 'a', 'b'};
    char[] B = {'b', 'd', 'c', 'a', 'b', 'a'};

    int lenRecurssive = lcsRecurssive(A, A.length - 1, B, B.length - 1);
    System.out.println("LCS length=" + lenRecurssive);
  }
}
