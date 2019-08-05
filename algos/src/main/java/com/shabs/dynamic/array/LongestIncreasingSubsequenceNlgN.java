package com.shabs.dynamic.array;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * Find the length of longest increasing non-contiguous sub-sequence
 * <p>
 * input unsorted array = [10, 9, 2, 5, 3, 7, 101, 18]
 *   one of the solution = 4 => [2, 3, 7, 101]
 *   - there could be multiple solution,
 *   - just need to return the number, not the actual sequence
 *
 * store last element in each of the longest subsequence seen so far
 * [2, 3, 7, 18]
 * whenever a new element is lower than one of these replace it,
 * since previous larger element is no good for increasing subsequence
 * index0 => 10 replace by 9 replace by 2
 * index1 => 5 replace by 3
 * index2 => 7
 * index3 => 101 replace by 18
 * index4 => not used
 *
 * index4 => 4 length of LIS
 */

public class LongestIncreasingSubsequenceNlgN {

  public int findLengthLCS_NlogN(int[] input) {
    int[] lastElementOfAllSubsequence = new int[input.length];
    int lengthOfLongestSequence = 0;

    for (int key : input) {

      // returns index of key in the array
      // or negative number [-(index-1)] if key not found
      //    where the index of the first element in the array greater than the key
      int index = Arrays.binarySearch(lastElementOfAllSubsequence, 0, lengthOfLongestSequence, key);

      if (index < 0) {
        // index where this element should go
        // so if the array has a(2) = 3 and a(3) = 5 -- the new key 4 will replace a(3)
        // ie. reduce the longer sequence's ending number
        index = -(index + 1);
      }

      lastElementOfAllSubsequence[index] = key;
      if (index == lengthOfLongestSequence) {
        // if inserting/updating the last element of the longestSequencesSoFar, increase its length by 1
        lengthOfLongestSequence++;
      }
    }

    for (int key : lastElementOfAllSubsequence) {
      System.out.print(key + ", ");
    }
    System.out.println();

    return lengthOfLongestSequence;
  }

  @Test
  public void test2() {
    int[] input = {10, 9, 2, 5, 3, 7, 101, 18};

    int expected = 4;
    int actual = findLengthLCS_NlogN(input);

    Assert.assertEquals(actual, expected);
  }
}
