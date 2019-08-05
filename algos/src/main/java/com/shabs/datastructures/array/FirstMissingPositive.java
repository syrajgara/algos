package com.shabs.datastructures.array;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TRICK
 * <p>
 * find first positive missing number in an un-sorted array in O(n) time, O(1) space
 * - since O(n) -> no sorting
 * - O(1) space -> have to reuse the array itself
 * <p>
 * - since we just need the first positive number
 * - we can ignore any number greater than n = length of array
 * - also ignore any negative numbers
 * - mark ignored numbers as zero
 * - keep swapping numbers to put them in its correct index location - decrement counter on swap
 * - at the end the array will have all number in its correct index location,
 * - with 0 indicating a missing number ... you want the first of these zeros.
 */
public class FirstMissingPositive {

  public int find(int[] input) {

    for (int i = 0; i < input.length; i++) {
      if (input[i] <= 0 || input[i] > input.length) {
        input[i] = 0; //ignore
      } else if (input[i] == i + 1) {
        // input already in the correct location
        // 0 index should have 1
        // 1 index should have 2 ... etc
      } else {
        // this value will stay in our array, move it to right location
        int temp = input[i];
        input[i] = input[temp - 1];
        input[temp - 1] = temp;

        i--; // redo this index
      }
    }

    int missing = 0;
    for (int i = 0; i < input.length; i++) {
      if (input[i] == 0) {
        missing = i + 1;
        break;
      }
    }

    return missing;
  }

  @Test
  public void test() {
    int[] input = {3, 4, -1, 1, 100};
    print("test1 before", input);
    int expected = 2;

    int actual = find(input);
    print("test1 after", input);
    Assert.assertEquals(actual, expected);
  }

  @Test
  public void test2() {
    int[] input = {1, 2, 0, 10};
    print("test2 before", input);
    int expected = 3;

    int actual = find(input);
    print("test2 after", input);
    Assert.assertEquals(actual, expected);
  }

  private void print(String prefix, int[] input) {
    System.out.print(prefix + " = ");
    for (int i : input) {
      System.out.print(i + ", ");
    }
    System.out.println();
  }
}
