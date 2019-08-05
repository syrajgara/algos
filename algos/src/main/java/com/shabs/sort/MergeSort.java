package com.shabs.sort;

import org.testng.annotations.Test;

/**
 * divide & conquer
 * <p>
 * divide into left and right array, sort left and sort right independently
 * and then merge the results.
 * <p>
 * - copy the range to separate array for convenience
 * - in a loop compare left element with right
 * - if left less - move it to original array and continue
 * - if right less - move that to original array and continue
 * - at the end, move leftover left array elements to original
 * - no need to move leftover right array elements, since they are in the right order anyways.
 */
public class MergeSort {
  public static void sort(int[] A, int l, int r) {
    if (l == r) {
      return;
    }

    int m = l + (r - l) / 2;

    sort(A, l, m);
    sort(A, m + 1, r);
    merge(A, l, m, r);
  }

  private static void merge(int[] A, int l, int m, int r) {
    int[] lCopy = copy(A, l, m);

    int ll = 0;
    int rl = m + 1;

    int aIndex = l;

    while (ll < lCopy.length && rl <= r) {
      if (lCopy[ll] <= A[rl]) {
        A[aIndex] = lCopy[ll];
        ll++;
      } else {
        A[aIndex] = A[rl];
        rl++;
      }
      aIndex++;
    }

    while (ll < lCopy.length) {
      A[aIndex] = lCopy[ll];
      ll++;
      aIndex++;
    }
  }

  private static int[] copy(int[] A, int l, int r) {
    int[] copy = new int[r - l + 1];

    for (int i = l; i <= r; i++) {
      copy[i - l] = A[i];
    }
    return copy;
  }

  @Test
  public void test() {
    int[] A = {4, 2, 8, 5, 1, 3, 7, 9, 6};

    MergeSort.sort(A, 0, 8);

    System.out.println("SORTED: ");
    for (int i = 0; i < 9; i++) {
      System.out.print(A[i] + ", ");
    }
  }
}
