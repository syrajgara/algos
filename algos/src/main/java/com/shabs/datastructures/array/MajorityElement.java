package com.shabs.datastructures.array;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * https://en.wikipedia.org/wiki/Boyer%E2%80%93Moore_majority_vote_algorithm
 *
 * Given an array, find the majority element.
 * Majority element is the one that exists atleast n/2 times in the array
 * given, majority element always exists in given array
 *
 * ie out of say 100 elements, 51 elements are same - other 49 could be same as well or different
 * - pick one as major - increment its counter if same number seen again, decrement if some other number seen
 * - when counter is at zero, use the next number as major.
 */
public class MajorityElement {

  public int findMajority(int[] input) {
    // init
    int major = 0;
    int countOfMajor = 0;

    for (int i = 0; i < input.length; i++) {
      if (countOfMajor == 0) {
        // no current major, make this major
        major = input[i];
        countOfMajor = 1;
      } else if (input[i] == major) {
        countOfMajor++;
      } else {
        // cancel out other majors lead
        countOfMajor--;
      }
    }

    return major;
  }

  @Test
  public void test() {
    int[] input = {2, 1, 2, 1, 1, 1};
    int expected = 1;

    int actual = findMajority(input);
    Assert.assertEquals(actual, expected);
  }
}
