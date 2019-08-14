package com.shabs.dynamic.array;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MaximumSubArraySum {
  /**
   * max sum which could be negative.
   * <p>
   * save first element as max, loop thru other elements
   * add element if it increases the sum ... result could still be smaller negative number
   */
  private int findMaxSumWorksWithNegativeNumbers(int[] input) {
    int maxSum = input[0];
    int previousSum = input[0];

    for (int i = 1; i < input.length; i++) {
      // we have to keep input[i], with or without the previousSum
      // if previousSum is -ve ignore it, (previousSum + input[i] < input[i])
      // start from current input - which could be negative, dont zero it out
      previousSum = Math.max(previousSum + input[i], input[i]);

      // reset the max
      maxSum = Math.max(maxSum, previousSum);
    }

    return maxSum;
  }

  /**
   * Same as above but store the range of indexes that resulted in the max sum
   */
  private String findMaxSumWorksWithNegativeNumbersRange(int[] input) {
    int maxSumSoFar = input[0];
    int maxStartIndex = 0;
    int maxEndIndex = 0;

    int currentSum = input[0];
    int startIndex = 0;
    int endIndex = 0;

    for (int i = 1; i < input.length; i++) {
      // we have to keep input[i], with or without the previousSum
      // if previousSum is -ve ignore it, start from current input
      if (currentSum + input[i] <= input[i]) {
        // reset, we will ignore all previous numbers
        startIndex = i;
      }
      endIndex = i;
      currentSum = Math.max(currentSum + input[i], input[i]);


      // reset the max
      if (maxSumSoFar <= currentSum) {
        maxStartIndex = startIndex;
        maxEndIndex = endIndex;
      }
      maxSumSoFar = Math.max(maxSumSoFar, currentSum);
    }

    return maxStartIndex + "-" + maxEndIndex;
  }

  @Test
  public void findSubArrayWithMaxSum() {
    int[] input = {-2, -5, 4, 10, -1, 1, 6, -2, -3, 1, 5, -6};
    int expectedWithNegativeNumbers = 21;
    String expectedRange = "2-10";

    callAndVerify(input, expectedWithNegativeNumbers, expectedRange);
  }

  @Test
  public void findSubArrayWithMaxSumNegativeNumbers() {
    int[] input = {-2, -5, -4, -10, -1, -1, -6, -2, -3, -1, -5, -6};
    int expectedWithNegativeNumbers = -1;
    String expectedRange = "9-9";

    callAndVerify(input, expectedWithNegativeNumbers, expectedRange);
  }

  @Test
  public void findSubArrayWithMaxSumIsNegative() {
    int[] input = {-2};
    int expectedWithNegativeNumbers = -2;
    String expectedRange = "0-0";

    callAndVerify(input, expectedWithNegativeNumbers, expectedRange);
  }

  private void callAndVerify(int[] input, int expectedWithNegativeNumbers, String expectedRange) {
    int actualWithNegativeNumbers = findMaxSumWorksWithNegativeNumbers(input);
    Assert.assertEquals(actualWithNegativeNumbers, expectedWithNegativeNumbers);

    String actualRange = findMaxSumWorksWithNegativeNumbersRange(input);
    Assert.assertEquals(actualRange, expectedRange);
  }
}
