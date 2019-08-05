package com.shabs.sort;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Deterministic Select - Finding a good Pivot for QuickSort
 * median of medians
 * <p>
 * - divide array into sub arrays of size 5 each
 * - find median in each of these sub arrays
 * - use these array of medians to find median again
 */
public class QuickSelectMedianOfMedian {

  private int findPivot(int[] input, int l, int r) {

    int sampleSize = r - l + 1;

    if (sampleSize <= 5) {
      MergeSort.sort(input, l, r);
      int median = l + sampleSize / 2;

      return input[median];
    }

    int[] medians = new int[sampleSize / 5];

    for (int i = 0; i < medians.length; i++) {
      int fromIndex = l + i * 5;
      int toIndex = fromIndex + 5 - 1;

      if (toIndex > r) {
        // for the last set of numbers
        toIndex = r; // toIndex exclusive for copy
      }

      medians[i] = findPivot(input, fromIndex, toIndex);
    }

    print(medians);

    return findPivot(medians, 0, medians.length - 1);
  }

  private void print(int[] input) {
    for (int i : input) {
      System.out.print(i + " ");
    }
    System.out.println();
  }

  @Test
  public void test() {
    int[] input = {4, 1, 2, 8, 3, 7, 5, 9, 15, 17, 13, 24, 25, 26, 27, 16, 18, 20, 65, 66, 67, 68, 69, 70, 50};
    int expected = 20;

    int actual = findPivot(input, 0, input.length - 1);
    System.out.println("final median = " + actual + "\n=======================");

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void test2() {
    int[] input = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
    int expected = 8;

    int actual = findPivot(input, 0, input.length - 1);
    System.out.println("final median = " + actual + "\n=======================");

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void test3() {
    int[] input = {1, 2, 3, 4, 5, 6};
    int expected = 3;

    int actual = findPivot(input, 0, input.length - 1);
    System.out.println("final median = " + actual + "\n=======================");

    Assert.assertEquals(actual, expected);
  }
}
