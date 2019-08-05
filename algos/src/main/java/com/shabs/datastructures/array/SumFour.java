package com.shabs.datastructures.array;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Give 4 arrays of int A, B, C, D find combinations of elements in these 4 arrays that will give sum of ZERO
 * <p>
 * - Complexity: n^2
 * <p>
 * - take sum of 2 arrays and keep track of the sum and number of times they occur.
 * - iterate thru other 2 arrays and check if sum in the map
 * - decrease the count of the sum in the map - so as not to over use the sums
 */
public class SumFour {

  private int find(int[] A, int[] B, int[] C, int[] D) {
    Map<Integer, Integer> sum2 = new HashMap<>();

    for (int a = 0; a < A.length; a++) {
      for (int b = 0; b < B.length; b++) {
        int currentSum = -(A[a] + B[b]); // negate - since sum is supposed to be zero

        // multiple combinations could have the same sum
        // keep track of how many combination has that sum
        // so that you can re-use that sum for other arrays iteration
        sum2.put(currentSum, sum2.getOrDefault(currentSum, 0) + 1);
      }
    }

    int countValidCombinations = 0;

    for (int c = 0; c < C.length; c++) {
      for (int d = 0; d < D.length; d++) {
        int currentSum = C[c] + D[d];
        if (sum2.containsKey(currentSum) && sum2.get(currentSum) != 0) {
          countValidCombinations++;
          sum2.put(currentSum, sum2.get(currentSum) - 1);
        }
      }
    }

    return countValidCombinations;
  }

  @Test
  public void test() {
    int[] A = {1, 2};
    int[] B = {-2, -1};
    int[] C = {-1, 2};
    int[] D = {0, 2};

    int expected = 2;
    int actual = find(A, B, C, D);

    Assert.assertEquals(actual, expected);
  }
}
