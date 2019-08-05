package com.shabs.datastructures.map;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Map<number, length at that number> current length = prev + next + 1
 *
 * Given an array of unsorted integers, *without* sorting or in O(n)
 * find the longest consecutive sequence of numbers
 *
 * {100, 4, 200, 1, 3, 2} => {1,2,3,4,100,200} => length of 4 for longest consecutive numbers
 * but have to find this without sorting
 *
 * - no sorting allowed
 * - use hashmap, to store the number and sequence length at that number
 * - do a simple loop thru the array
 * - check for number-1 and number+1 in the array
 * - if they exists use the length on those + 1 for this number
 * - also fix lengths at the edges, since edge will be used to extend this sequence
 *
 * k5->v1->v2->v3(edge updated)
 *     k6->v2->(no update)
 *         k7->v3
 *             k7(repeat, ignore)
 */
public class LongestConsecutiveSequence {

  private int findLength(int[] input) {
    int maxLength = 0;
    Map<Integer, Integer> map = new HashMap<>();

    for (int i : input) {
      if (map.containsKey(i)) {
        continue; // duplicate
      }

      int leftSequenceLength = map.containsKey(i-1) ? map.get(i-1) : 0;
      int rightSequenceLength = map.containsKey(i+1) ? map.get(i+1) : 0;

      int currentLength = leftSequenceLength + rightSequenceLength + 1;

      // fix the edges,
      // no need to fix itself - if left or right is not present below fixes itself
      map.put(i - leftSequenceLength, currentLength);
      map.put(i + rightSequenceLength, currentLength);

      //also save the max seen
      maxLength = Math.max(maxLength, currentLength);
    }

    return maxLength;
  }

  @Test
  public void test() {
    int[] input = {100, 4, 200, 1, 3, 2};

    int expected = 4;
    int actual = findLength(input);

    Assert.assertEquals(actual, expected);
  }
}
