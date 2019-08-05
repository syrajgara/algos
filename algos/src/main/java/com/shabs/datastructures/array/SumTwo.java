package com.shabs.datastructures.array;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * <p>
 * [2, 7, 11, 15] -> 9
 * <p>
 * - soln 1 : HashMap - add (target - number[i]) and then check if number in hashmap
 * - soln 2 : 2 pointers
 */
public class SumTwo {

  private boolean findSumUsing2Pointers(int twosum, int[] input) {
    Arrays.sort(input);

    int lowIndex = 0;
    int highIndex = input.length - 1;

    while (lowIndex < highIndex) {
      int currentSum = input[lowIndex] + input[highIndex];
      if (currentSum == twosum) {
        System.out.println(lowIndex + " - " + highIndex);
        return true;
      }

      if (currentSum > twosum) {
        highIndex--;
      } else {
        lowIndex++;
      }
    }

    return false;
  }

  @Test
  public void test() {
    int[] input = {1, 2, 5, 7, 11, 15};
    int twosum = 9;

    boolean expected = true;
    boolean actual = findSumUsing2Pointers(twosum, input);

    Assert.assertEquals(actual, expected);
  }


  @Test
  public void testNoSum() {
    int[] input = {1, 5, 7, 11, 15};
    int twosum = 9;

    boolean expected = false;
    boolean actual = findSumUsing2Pointers(twosum, input);

    Assert.assertEquals(actual, expected);
  }
}
