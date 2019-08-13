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
  public static int lcsRecurssive(char[] A, int indexA, char[] B, int indexB) {
    int len;

    if (indexA == -1 || indexB == -1) {
      // reached the end of one or both strings
      //System.out.println("Index OUT: indexA=" + indexA + " indexB=" + indexB);
      len = 0;
    } else if (A[indexA] == B[indexB]) {
      //System.out.println("Index EQUAL: indexA=" + indexA + " indexB=" + indexB);
      len = 1 + lcsRecurssive(A, indexA - 1, B, indexB - 1);
    } else {
      //System.out.println("Index MISMATCH: indexA=" + indexA + " indexB=" + indexB);
      len = Math.max(lcsRecurssive(A, indexA - 1, B, indexB),
                     lcsRecurssive(A, indexA, B, indexB - 1));
                     // no need for 3rd option of reducing both indexes by 1, since that will get covered in next recursive call
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
