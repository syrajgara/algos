package com.shabs.datastructures.array;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * merge 2 sorted arrays, where big array has empty space at the end to accommodate the small array
 * <p>
 * keep track of the merge, big and small tail
 * keep moving elements from big or small tail to merge tail - till both has elements
 * - if small tail has elements, after big is exhausted, move those elements to merge tail
 * - if small tail has exhausted, big tails elements are already in place.
 */
public class MergeTwoSortedArrayInOneBiggerArray {
  private int[] merge(int[] big, int[] small) {
    int mergeTail = big.length - 1;
    int bigTail = big.length - small.length - 1;
    int smallTail = small.length - 1;

    while (bigTail >= 0 && smallTail >= 0) {
      if (big[bigTail] > small[smallTail]) {
        big[mergeTail--] = big[bigTail--];
      } else {
        big[mergeTail--] = small[smallTail--];
      }
    }

    // this while not needed, the rest of the elements of big are already in place
    while (bigTail >= 0) {
      big[mergeTail--] = big[bigTail--];
    }

    while (smallTail >= 0) {
      big[mergeTail--] = small[smallTail--];
    }

    return big;
  }

  @Test
  public void merge() {
    int[] big = {1, 3, 5, 7, 9, 0, 0, 0};
    int[] small = {-1, -2, 6};
    int[] expected = {-1, -2, 1, 3, 5, 6, 7, 9};

    int[] merged = merge(big, small);

    Assert.assertEquals(merged, expected);
  }
}
