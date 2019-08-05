package com.shabs.sort;

import org.testng.annotations.Test;

/**
 * Bubble the lightest/lowest number in each iteration to the top of the array.
 * <p>
 * i = loop from top to bottom-1
 * j = loop from bottom to i+1
 * swap if j < (lighter)  j-1
 */
public class BubbleSort {
  public int[] sort(int[] A) {
    int n = A.length;

    for (int i = 0; i < n - 1; i++)  // each iteration bring the i-th lowest to the top
    {
      for (int j = n - 1; j > i; j--) {
        if (A[j] < A[j - 1]) {
          //swap
          int temp = A[j - 1];
          A[j - 1] = A[j];
          A[j] = temp;
        }
      }
    }

    return A;
  }

  @Test
  public void test() {
    int[] A = {4, 2, 8, 5, 1, 3, 7, 9, 6};
    int[] sortedA = sort(A);

    System.out.println("SORTED: ");
    for (int i = 0; i < 9; i++) {
      System.out.print(sortedA[i] + ", ");
    }
  }
}
