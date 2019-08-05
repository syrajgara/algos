package com.shabs.datastructures.array;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * 2 Pointers
 * In a sorted array - remove duplicates, in O(n) time and O(1) space
 * <p>
 * - keep track of the index to write to
 * - write is needed only when an index is not equal to its previous
 * - increment writeIndex after each write
 * - writeIndex at the end should give you the new length of the array
 */
public class RemoveDuplicatesFromSortedArray {

  public int remove2(int[] input) {
    if (input.length < 2) {
      return input.length;
    }

    int lastIndex = 0;
    for (int i = 1; i < input.length; i++) {
      if (input[i] != input[lastIndex]) {
        input[++lastIndex] = input[i];
      }
    }

    return ++lastIndex;
  }

  public int remove(int[] input) {
    if (input.length < 2) {
      return input.length;
    }

    int writeIndex = 1;
    for (int i = 1; i < input.length; i++) {
      if (input[i] != input[i - 1]) {
        input[writeIndex++] = input[i];
      }
    }

    return writeIndex;
  }

  @Test
  public void test() {
    int[] input = {1, 1, 2};
    int expected = 2;
    int actual = remove2(input);
    Assert.assertEquals(actual, expected);
  }

  @Test
  public void testNoDups() {
    int[] input = {1, 2, 3};
    int expected = 3;
    int actual = remove2(input);
    Assert.assertEquals(actual, expected);
  }
}
