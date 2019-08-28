package com.shabs.datastructures.matrix;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * 2D matrix - rows in ascending, cols in ascending
 *
 * - Start with center cell, divide into 4 quadrant, eliminate one quadrant each pass.
 *
 * right bottom quadrant always bigger than center
 * left top quadrant always smaller than center
 * other 2 quadrants could be a mix
 * which means we can eliminate only right bottom or left top quadrant in each pass.
 */
public class Search2DMatrix {

  private boolean search(int[][] input, int target) {

    if(input == null || input.length < 1 || input[0].length <1) {
      return false;
    }

    return find(input, target, 0, 0, input.length - 1, input[0].length - 1);
  }

  boolean find(int[][] input, int target, int rowL, int colL, int rowR, int colR) {

    if (rowL < 0 || colL < 0 || rowR == input.length || colR == input[0].length) {
      return false;
    }

    int row = rowL + (rowR-rowL) / 2;
    int col = colL + (colR-colL) / 2;

    if (input[row][col] == target) {
      return true;
    }

    if (target > input[row][col]) {
      return find(input, target, row, col, rowR, colR) ||
             find(input, target, rowL, col, row - 1, colR) ||
             find(input, target, row, colL, rowR, col - 1);
    } else {
      return find(input, target, rowL, colL, row, col) ||
             find(input, target, rowL, col, row - 1, colR) ||
             find(input, target, row + 1, colL, rowR, col - 1);
    }
  }

  @Test
  public void test1() {
    int target = 5;

    boolean expected = true;
    boolean actual = search(getInput(), target);

    Assert.assertEquals(actual, expected);
  }

  //@Test
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
