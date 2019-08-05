package com.shabs.dynamic.matrix;

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
public class LongestCommonSubsequence_Matrix {
  public static int lcsIterative(char[] A, int lenA, char[] B, int lenB) {
    // array that will keep count of matches will need to be len[lenA+1][lenB+1]
    // this is to add a len[0][0] - additional column and row
    // to account for null element at the end of the array.

    // all elements of len, initialized to zero
    int[][] len = new int[lenA + 1][lenB + 1];

    // the 0th column and row of len, is not used for count - it is always set to zeros
    for (int row = 1; row <= lenA; row++) {
      for (int col = 1; col <= lenB; col++) {
        // i-1 and j-1 since we are using i, j to be length of the arrays and not index starting at zero.
        if (A[row - 1] == B[col - 1]) {
          //System.out.println("Index EQUAL: a=" + i + " b=" + j);
          len[row][col] = 1 + len[row - 1][col - 1];
        } else {
          //System.out.println("Index MISMATCH: a=" + i + " b=" + j);
          len[row][col] = Math.max(len[row][col - 1], len[row - 1][col]);
        }
      }
    }

    return len[lenA][lenB];
  }

  @Test
  public void test() {
    char[] A = {'a', 'b', 'c', 'b', 'd', 'a', 'b'};
    char[] B = {'b', 'd', 'c', 'a', 'b', 'a'};

    int lenIterative = lcsIterative(A, A.length, B, B.length);
    System.out.println("LCS length=" + lenIterative);
  }
}

