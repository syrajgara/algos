package com.shabs.dynamic.array;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * ARRAY1[N+1] = strings possible with ZERO at index i
 * ARRAY2[N+1] = strings possible with ONE at index i
 * init ARRAY1/2[0] = 0 and ARRAY1/2[1] = 1
 *
 * Given N, find all possible binary strings of length N, such that there are no consecutive 1′s.
 *
 * Input:  N = 2
 * Output: 3 - The 3 strings are 00, 01, 10
 * <p>
 * create 2 arrays of length n+1
 * <p>
 * create array to track number of strings possible with zero at a particular index position.
 * create array to track number of strings possible with one at a particular index position.
 * <p>
 * to add zero at an index - you can add previous Zero and One values
 * to add one at an index - you can only add previous Zero values
 * <p>
 * for Nth index - sum zero and one values to get total strings possible at that location.
 */
public class BinaryStringWithNoConsecutiveOne {
  private final Logger logger = LoggerFactory.getLogger(BinaryStringWithNoConsecutiveOne.class);

  private int countStrings(int n) {
    // for n = 0, 1, 2 ..., n -- store number of string at each n
    int[] numberOfStringsWithZeroAtIndex = new int[n + 1];
    int[] numberOfStringsWithOneAtIndex = new int[n + 1];

    // with n = 0 - no strings possible
    numberOfStringsWithZeroAtIndex[0] = 0;
    numberOfStringsWithOneAtIndex[0] = 0;

    // with n = 1 - one string for each zero/one possible ie "0" and "1"
    numberOfStringsWithZeroAtIndex[1] = 1;
    numberOfStringsWithOneAtIndex[1] = 1;

    for (int i = 2; i <= n; i++) {
      // string can have 0 after 1 or 0
      numberOfStringsWithZeroAtIndex[i] = numberOfStringsWithZeroAtIndex[i - 1] + numberOfStringsWithOneAtIndex[i - 1];

      // for string to have 1 at this location,
      // we can duplicate all the strings that had 0 in previous location
      // so count for 1 is same as count of 0 at previous location
      numberOfStringsWithOneAtIndex[i] = numberOfStringsWithZeroAtIndex[i - 1];
    }

    // total strings of "n" length ending in zero + one
    return numberOfStringsWithZeroAtIndex[n] + numberOfStringsWithOneAtIndex[n];
  }

  @Test
  public void countStringsFor2Digits() {
    int n = 2;
    int expectedCount = 3; // 00, 01, 10 -- not 11

    int actualCount = countStrings(n);

    Assert.assertEquals(actualCount, expectedCount);
  }

  @Test
  public void countStringsFor3Digits() {
    int n = 3;
    int expectedCount = 5; // 000, 001, 010, 100, 101 -- not 011, 110, 111

    int actualCount = countStrings(n);

    Assert.assertEquals(actualCount, expectedCount);
  }
}
