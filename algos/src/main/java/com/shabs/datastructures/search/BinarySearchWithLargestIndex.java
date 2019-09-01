package com.shabs.datastructures.search;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Binary search for a value in an array
 * Variation: array has duplicate values, get the largest index for the value
 * <p>
 * - binary search for the value
 * - once found - store its index
 * - continue binary searching for the value in the upper array to find the largest index of that value
 * <p>
 * ex: {1,1,2,2,2,2,5} - binary search for value 2
 * normal binary search will find 2 at index 3 - then continue to binary search it again till you reach index 5
 */
public class BinarySearchWithLargestIndex {
  public static int search(int[] A, int l, int r, Integer searchItem, Integer foundAtIndex) {
    if (r < l) {
      return foundAtIndex;
    }
    int m = l + (r - l) / 2;

    if (searchItem == A[m]) {
      foundAtIndex = m;
      System.out.println("FOUND: " + searchItem + " at " + foundAtIndex + "\n");
      foundAtIndex = search(A, m + 1, r, searchItem, foundAtIndex);
    } else if (searchItem < A[m]) {
      foundAtIndex = search(A, l, m - 1, searchItem, foundAtIndex);
    } else {
      foundAtIndex = search(A, m + 1, r, searchItem, foundAtIndex);
    }

    return foundAtIndex;
  }

  @Test
  public void test() {
    int[] A = {4, 4, 4, 4, 8, 9, 13, 17, 20}; // has to be sorted, else sort first.

    Integer foundAtIndex = -1;
    Integer actualFoundAtIndex = -2;

    Integer expectedFoundAtIndex = -1;
    actualFoundAtIndex = search(A, 0, 8, -1, foundAtIndex);
    Assert.assertEquals(actualFoundAtIndex, expectedFoundAtIndex);

    expectedFoundAtIndex = 4;
    actualFoundAtIndex = search(A, 0, 8, 8, foundAtIndex);
    Assert.assertEquals(actualFoundAtIndex, expectedFoundAtIndex);

    expectedFoundAtIndex = 6;
    actualFoundAtIndex = search(A, 0, 8, 13, foundAtIndex);
    Assert.assertEquals(actualFoundAtIndex, expectedFoundAtIndex);

    expectedFoundAtIndex = 3;
    actualFoundAtIndex = search(A, 0, 8, 4, foundAtIndex);
    Assert.assertEquals(actualFoundAtIndex, expectedFoundAtIndex);
  }
}
