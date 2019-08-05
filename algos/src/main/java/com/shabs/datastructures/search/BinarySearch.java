package com.shabs.datastructures.search;

import org.testng.annotations.Test;

/**
 * Binary Search
 * - if middle element is a match you are done
 * - else search left or right based on search value and the middle value of the array.
 */
public class BinarySearch {
  public static boolean search(int[] A, int l, int r, Integer searchItem) {
    boolean found = false;
    if (r < l) {
      return found;
    }

    int m = l + (r - l) / 2;
    if (searchItem == A[m]) {
      System.out.println("FOUND: " + searchItem);
      found = true;
    } else if (searchItem < A[m]) {
      found = search(A, l, m - 1, searchItem);
    } else {
      found = search(A, m + 1, r, searchItem);
    }

    return found;
  }

  @Test
  public void test() {
    int[] A = {0, 2, 4, 6, 8, 9, 13, 17, 20}; // has to be sorted, else sort first.

    search(A, 0, 8, -1);
    search(A, 0, 8, 4);
    search(A, 0, 8, 8);
    search(A, 0, 8, 12);
    search(A, 0, 8, 22);
  }
}
