package com.shabs.datastructures.search;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * search number in sorted but rotated array, rotated by unknown number
 * <p>
 * - recursive searches
 * - binary search, if sub-array is sorted and not-rotated
 * - search left and right parts, if sub-array is rotated
 */
public class SearchInRotatedSortedArray {

  public int search(int target, int[] input, int lowIndex, int highIndex) {

    if (lowIndex >= input.length || highIndex < 0 || lowIndex > highIndex) {
      return -1;
    }

    int midIndex = lowIndex + (highIndex - lowIndex) / 2;

    if (target == input[midIndex]) {
      return midIndex;
    }

    // no rotation, binary search
    if (input[lowIndex] < input[highIndex]) {
      if (target < input[midIndex]) {
        return search(target, input, lowIndex, midIndex - 1);
      }

      return search(target, input, midIndex + 1, highIndex);
    }

    // rotation, check both parts
    int lowSearch = search(target, input, lowIndex, midIndex - 1);
    if (lowSearch != -1) {
      return lowSearch;
    }

    return search(target, input, midIndex + 1, highIndex);
  }

  @Test
  public void numberExistsInLower() {
    int[] input = {12, 13, 14, 15, 16, 17, 18, 19, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};

    int expected = 2;
    int actual = search(14, input, 0, input.length - 1);

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void numberExistsInHigher() {
    int[] input = {12, 13, 14, 15, 16, 17, 18, 19, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};

    int expected = 8;
    int actual = search(1, input, 0, input.length - 1);

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void numberDoesntExistLow() {
    int[] input = {12, 13, 14, 15, 16, 17, 18, 19, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};

    int expected = -1;
    int actual = search(0, input, 0, input.length - 1);

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void numberDoesntExistHigh() {
    int[] input = {12, 13, 14, 15, 16, 17, 18, 19, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};

    int expected = -1;
    int actual = search(100, input, 0, input.length - 1);

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void rotationButLowHighSame() {
    int[] input = {4,4,4,4,4,1,2,3,4};

    int expected = 5;
    int actual = search(1, input, 0, input.length - 1);

    Assert.assertEquals(actual, expected);
  }
}
