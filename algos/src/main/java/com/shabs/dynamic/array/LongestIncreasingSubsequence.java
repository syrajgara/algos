package com.shabs.dynamic.array;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Find the length of longest increasing non-contiguous sub-sequence
 * <p>
 * input unsorted array = [10, 9, 2, 5, 3, 7, 101, 18]
 *   one of the solution = 4 => [2, 3, 7, 101]
 *   - there could be multiple solution,
 *   - just need to return the number, not the actual sequence
 * <p>
 * <p>
 * - keep track of LIS size at each element in the array - using another array[n]
 * - init - each element by itself gives LIS of 1
 * <p>
 * - loop thru all elements, and within that, another loop *till* that element
 * - previous smaller values's LIS + 1, will become this elements LIS,
 * - unless LIS of this element is already larger
 * - if previous element is larger, no change to LIS of this element
 */
public class LongestIncreasingSubsequence {

  private int findLengthLCS(int[] input) {

    int maxLIS = 0;

    if (input.length == 0) {
      return maxLIS;
    }

    // keep LIS at every index
    int[] lis = new int[input.length];

    for (int curr = 0; curr < input.length; curr++) {
      //init, each element by itself gives LIS of 1
      // and then we will update LIS at this point based on previous LIS
      lis[curr] = 1;

      // LIS of this element increases, based on LIS of previous element smaller than this
      for (int prev = 0; prev < curr; prev++) {
        if (input[prev] <= input[curr] && lis[prev] >= lis[curr]) {
          lis[curr] = lis[prev] + 1;
        }
      }

      maxLIS = Math.max(maxLIS, lis[curr]);
    }

    return maxLIS;
  }

  @Test
  public void test() {
    int[] input = {10, 9, 2, 5, 3, 7, 101, 18};

    int expected = 4;
    int actual = findLengthLCS(input);

    Assert.assertEquals(actual, expected);
  }
}
