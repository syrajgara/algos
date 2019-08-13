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
  public static int lcsIterative(char[] A, char[] B) {
    // add additional column and row
    // all elements of len, initialized to zero
    int[][] len = new int[A.length + 1][B.length + 1];

    // the 0th column and row of len, is not used for count - it is always set to zeros
    for (int row = 1; row <= A.length; row++) {
      for (int col = 1; col <= B.length; col++) {
        if (A[row - 1] == B[col - 1]) {
          // System.out.println("Index EQUAL: row=" + row + " col=" + col);
          len[row][col] = 1 + len[row - 1][col - 1];
        } else {
          // System.out.println("Index MISMATCH: row=" + row + " col=" + col);
          len[row][col] = Math.max(len[row][col - 1], len[row - 1][col]);
        }
      }
    }

    System.out.print("    ");
    for (int b = 0; b < B.length; b++) {
      System.out.print(B[b] + ",");
    }
    System.out.println();

    for (int row = 0; row < len.length; row++) {
      if (row == 0) {
        System.out.print("  ");
      } else {
        System.out.print(A[row-1] + " ");
      }

      for (int col = 0; col < len[0].length; col++) {
        System.out.print(len[row][col] + ",");
      }
      System.out.println();
    }
    System.out.println();

    return len[A.length][B.length];
  }

  @Test
  public void test() {
    char[] A = {'a', 'b', 'c', 'b', 'd', 'a', 'b'};
    char[] B = {'b', 'd', 'c', 'a', 'b', 'a'};

    int lenIterative = lcsIterative(A, B);
    System.out.println("LCS length = " + lenIterative);
  }
}

