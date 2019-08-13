package com.shabs.dynamic.matrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Partition problem is to determine whether a given set can be partitioned
 * into two subsets such that the sum of elements in both subsets is same.
 *
 * MATRIX Boolean [totalSum/2 + 1] [NumberOfElements]
 * init => m[0][*] = true
 *
 * current element = sum => TRUE
 * previous element at sum was TRUE
 * previous element at (sum - current element) was TRUE
 *
 * <p>
 * arr[] = {1, 5, 11, 5}
 * <p>
 * Output: TRUE : The array can be partitioned as {1, 5, 5} and {11}
 * <p>
 * - if total sum not even, then cannot do 2 subset
 * - create a matrix of number of elements * totalSum/2 + 1 ...
 * we are basically going to see if we can select elements such that we get the half sum.
 * <p>
 * in the matrix each element can make up the sum by "being in the set" or "not being in the set"
 * for being in the set - (sum - element) should be possible by the previous element
 * for not being in the set - sum should be possible by the previous element
 */
public class BalancedPartition {
  private final Logger logger = LoggerFactory.getLogger(BalancedPartition.class);

  private boolean balancedPartition(int[] input) {
    int totalSum = totalSum(input);

    if (totalSum % 2 != 0) {
      // sum not even, so cannot partition
      return false;
    }

    // keep track if an element can be (part of OR absent from) a set with given sum.
    int sumOfOneSet = totalSum / 2;
    boolean[][] table = new boolean[sumOfOneSet + 1][input.length];

    // INIT first row
    // all elements can get you to sum of zero - by being not part of that set
    int SUM_ZERO = 0;
    for (int index = 0; index < table[SUM_ZERO].length; index++) {
      table[SUM_ZERO][index] = true;
    }

    // INIT first col
    int firstIndex = 0;
    int firstValueInInput = input[firstIndex];
    table[firstValueInInput][firstIndex] = true;

    // loop thru all sums, see if elements can be part of that sum
    for (int sum = 1; sum < table.length; sum++) {
      for (int index = 1; index < table[0].length; index++) {

        // if previous index can make this sum,
        // than this index can also make the sum by NOT being part of that same set.
        if (table[sum][index - 1]) {

          table[sum][index] = table[sum][index - 1]; // WITH ONLY previous index
          continue;
        }

        // this index has to be part of this sum, iff...
        int previousSum = sum - input[index];
        if (previousSum >= 0 // so as to not step out of the matrix
            && table[previousSum][index - 1]) {

          // since previous index is true for previous sum,
          // adding current value to previous sum will also be true
          table[sum][index] = true; // WITH this + previous index
        }
      }
    }

    printTable(input, table);

    // if the half sum and last index combination is true,
    // we were able to split the set into 2 sets of equal sum.
    return table[sumOfOneSet][input.length - 1];
  }

  private void printTable(int[] input, boolean[][] table) {
    for (int y = -1; y < table.length; y++) {
      for (int x = -1; x < table[0].length; x++) {
        if (x == -1 && y == -1) {
          System.out.print("\t\t|\t");
          continue;
        }
        if (x == -1) {
          System.out.print(y + "\t\t|\t");
          continue;
        }
        if (y == -1) {
          System.out.print(input[x] + "*\t");
          continue;
        }

        String text = table[y][x] ? "T" : "F";
        System.out.print(text + "\t");
      }
      System.out.println();
    }
  }

  private int totalSum(int[] input) {
    int sum = 0;
    for (int value : input) {
      sum += value;
    }

    return sum;
  }

  @Test
  public void balancedPartition() {
    int[] input = {5, 1, 11, 5};
    boolean actual = true;

    boolean expected = balancedPartition(input);
    Assert.assertEquals(actual, expected);
  }
}
