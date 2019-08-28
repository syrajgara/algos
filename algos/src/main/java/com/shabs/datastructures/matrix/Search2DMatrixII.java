package com.shabs.datastructures.matrix;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * 2D matrix - rows in ascending, cols in ascending
 *
 * - Start with the right top corner (or left corner) and eliminate one row or col - to leave resulting matrix as a single rectangle
 *
 * - if equal to target - you found it
 * - if target is less - move column left
 * - if target is more - move row down
 * - loop till you step out of the matrix
 * - col becomes < 0
 * - or row > number of rows
 */
public class Search2DMatrixII {

  private boolean search(int[][] input, int target) {

    if(input == null || input.length < 1 || input[0].length <1) {
      return false;
    }

    // start search with following - so as to eliminate one row or one col
    int row = 0;
    int col = input[0].length-1;

    // we are moving left for columns (target less than current)
    // and moving down with rows (target more than current)
    // if we step out of matrix - we have completed the search
    while (col >= 0 && row < input.length) {
      if (input[row][col] == target) {
        return true;
      }

      if (target < input[row][col]) {
        col--;
      } else {
        row++;
      }
    }

    return false;
  }

  @Test
  public void test1() {
    int target = 5;

    boolean expected = true;
    boolean actual = search(getInput(), target);

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void test2() {
    int target = 20;

    boolean expected = false;
    boolean actual = search(getInput(), target);

    Assert.assertEquals(actual, expected);
  }

  private int[][] getInput() {
    int[][] input = {
        {1,   4,  7, 11, 15},
        {2,   5,  8, 12, 19},
        {3,   6,  9, 16, 22},
        {10, 13, 14, 17, 24},
        {18, 21, 23, 26, 30}
    };
    return input;
  }
}
