package com.shabs.dynamic.array;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * MATRIX [not similar to comparing multiple dimensions - so putting in array package]
 * (matrix is same dimension as input)
 * to keep track of calculated paths at each cell.
 *
 * Give a m*n matrix of integers, find the length of the max increasing contiguous sequence.
 * - loop thru each cell
 * - for each cell keep length of sequence from that cell, in separate storage/matrix
 * - move left/right/up/down only if increasing number
 * - take care of out of bound, calc value already stored (memoize)
 *
 * - take longer path, rather than short cut - since we want to maximize the length
 */
public class LongestIncreasingPathInAMatrix {

  private int findPaths(int[][] input) {
    int overallMaxLength = 0;
    int[][] paths = new int[input.length][input[0].length];

    for (int row = 0; row < input.length; row++) {
      for (int col = 0; col < input[0].length; col++) {
        int maxLengthFromCurrent = calcPaths(input, paths, row, col, Integer.MIN_VALUE);
        overallMaxLength = Math.max(overallMaxLength, maxLengthFromCurrent);
      }
    }

    return overallMaxLength;
  }

  private int calcPaths(int[][] input, int[][] paths, int row, int col, int previousValue) {
    if (row < 0 || row >= input.length || col < 0 || col >= input[0].length) {
      return 0; // stepping out of bound
    }

    int currentValue = input[row][col];
    if ( currentValue <= previousValue) {
      return 0; // this element is not increasing
    }

    if (paths[row][col] != 0) {
      return paths[row][col]; // we have already calculated it
    }

    int pathLeft = calcPaths(input, paths, row, col-1, currentValue);
    int pathRight = calcPaths(input, paths, row, col+1, currentValue);
    int pathUp = calcPaths(input, paths, row-1, col, currentValue);
    int pathDown = calcPaths(input, paths, row+1, col, currentValue);

    paths[row][col] = 1 + Math.max(Math.max(Math.max(pathLeft,
                                                     pathRight),
                                                     pathUp),
                                                     pathDown);

    return paths[row][col];
  }

  @Test
  public void test1() {
    int[][] input = {
                      {9,9,4},
                      {6,6,8},
                      {2,1,1}
                    };

    int expected = 4;
    int actual = findPaths(input);

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void test2() {
    int[][] input = {
                      {3,4,5},
                      {3,2,6},
                      {2,2,1}
                    };

    int expected = 4;
    int actual = findPaths(input);

    Assert.assertEquals(actual, expected);
  }
}

