package com.shabs.datastructures.matrix;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * given a n*n matrix, with zero's and one's
 * find the biggest square made in this matrix with ones.
 * <p>
 * first row and col, values wont change.
 * if cell value is zero, ignore calculations
 * if cell value is one, find min of previous/top adjacent cells and add one to it.
 * keep max square size and update if new calc value of cell is more.
 */
public class FindBiggestSquare {

  public FindBiggestSquare() {
  }

  public int solve(int[][] matrix) {

    int maxSquare = 0;
    // first row and col, values wont change.
    for (int row = 0; row < matrix.length; row++) {
      for (int col = 0; col < matrix[0].length; col++) {

        if (matrix[row][col] == 0) {
          continue;
        }

        if (row == 0 || col == 0) {
          if (maxSquare == 0) {
            maxSquare = matrix[row][col];
          }
          continue;
        }

        matrix[row][col] = 1 + Math.min(Math.min(matrix[row - 1][col - 1],
                                                 matrix[row - 1][col]),
                                                 matrix[row][col - 1]);

        if (matrix[row][col] > maxSquare) {
          maxSquare = matrix[row][col];
        }
      }
    }

    return maxSquare;
  }

  @Test
  public void testSize1() {
    int[][] matrix = {
        {0, 1, 0, 0, 0},
        {1, 1, 0, 1, 0},
        {1, 0, 0, 1, 0},
        {0, 0, 1, 1, 0},
        {0, 0, 1, 0, 0}
    };

    int expected = 1;
    int actual = solve(matrix);

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void testSize2() {
    int[][] matrix = {
        {0, 1, 0, 0, 0},
        {1, 1, 0, 1, 0},
        {1, 1, 1, 1, 0},
        {0, 0, 1, 1, 0},
        {0, 0, 1, 0, 0}
    };

    int expected = 2;
    int actual = solve(matrix);

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void testSize3() {
    int[][] matrix = {
        {0, 1, 0, 0, 0},
        {1, 1, 1, 1, 0},
        {1, 1, 1, 1, 0},
        {0, 1, 1, 1, 0},
        {0, 0, 1, 0, 0}
    };

    int expected = 3;
    int actual = solve(matrix);

    Assert.assertEquals(actual, expected);
  }

}
