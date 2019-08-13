package com.shabs.bitmanipulation;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * https://leetcode.com/problems/missing-number/#/solutions
 *
 * A ^ A = 0 --- so (index ^ number) to cancel out the numbers with its index,
 * you will end up with index value that didnt have corresponding number
 *
 *
 * <p>
 * - assumption, in an array with index 0 - n ... the values are between 0 and n+1
 * - with one of the numbers missing
 * <p>
 * - [a^b = c] then [c^a = b] and [c^b = a]
 * - apply XOR to both index and the value, index and a value will cancel out itself
 * - at the end - xor will contain the missing number and the last index
 * - ie. the last number - whose index was not present in the array, due to one less number in the array
 * - revealing the missing number
 */
public class FindMissingIntegerUsingXor {

  // assume numbers start with 0 -- since index starts at 0
  public int find(int[] input) {
    int xor = 0;

    for (int i = 0; i < input.length; i++) {
      xor = xor ^ i ^ input[i];
    }

    if (xor == 0) {
      // nothing missing
      return 0;
    }

    // at this point, one number is missing and the last index is missing
    // take last index out, to reveal the missing number
    return xor ^ input.length;
  }

  @Test
  public void testMiddleMissing() {
    int[] input = {0, 1, 2, 3,/*4,*/5, 6};
    int expected = 4;
    int actual = find(input);

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void testFirstMissing() {
    int[] input = {1, 2, 3, 4, 5, 6};
    int expected = 0;
    int actual = find(input);

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void testNoMissing() {
    int[] input = {0, 1, 2, 3, 4, 5, 6};
    int expected = 0;
    int actual = find(input);

    Assert.assertEquals(actual, expected);
  }
}
