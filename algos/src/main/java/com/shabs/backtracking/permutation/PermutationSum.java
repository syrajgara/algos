package com.shabs.backtracking.permutation;

import org.testng.annotations.Test;

/**
 * Given sum=4 find all the permutation of int that will make the sum=4
 * {1,1,1,1},{1,1,2},{1,2,1},{1,3}
 * {2,1,1},{2,2}
 * {3,1},
 * {4}...
 * <p>
 * for sum=4, max values would be 1+1+1+1, so create array[4]
 * loop from 1 to 4 - and then recursively loop for remaining sum
 */
public class PermutationSum {
  public static void calculate(int sum, int[] permutations, int colIndex) {
    if (sum == 0) {
      print(permutations);
      return;
    }

    for (int currentPossibleValue = 1; currentPossibleValue <= sum; currentPossibleValue++) {
      permutations[colIndex] = currentPossibleValue;
      calculate(sum - currentPossibleValue, permutations, colIndex + 1);
      // we are done printing combination with this currentPossibleValue, reset for next loop
      permutations[colIndex] = 0;
    }
  }

  private static void print(int[] permutations) {
    System.out.print(">> ");
    for (int value : permutations) {
      if (value != 0) {
        System.out.print(value + " ");
      }
    }
    System.out.println();
  }

  @Test
  public void test() {
    int sum = 4;
    int[] permutations = new int[sum];

    calculate(sum, permutations, 0);
  }
}
