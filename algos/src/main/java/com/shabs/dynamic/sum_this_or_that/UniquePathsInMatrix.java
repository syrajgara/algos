package com.shabs.dynamic.sum_this_or_that;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * given a matrix m*n, move from (0,0) to (m,n) by going right or down
 * how many unique paths can be found.
 * <p>
 * - recurse findPaths by moving right + moving down
 * - zero path if stepping out of matrix
 * - one path when you reach the destination - ie. only the destination cell increments the path counter
 * - all other cells are just on the way to the destination - they are on the path,
 * they dont increment path counter
 */
public class UniquePathsInMatrix {

  public int findPaths(int currentRow, int currentCol, int endRow, int endCol, int[][] memoize, boolean doMemoize) {

    if (currentRow > endRow || currentCol > endCol) {
      // stepping out of the matrix, no paths
      return 0;
    }

    if (currentRow == endRow && currentCol == endCol) {
      // reached destination,
      // this is the only cell that increments the number of path
      // remember rest of the cells are just on the path to this cell - they dont increase the number of paths.
      return 1;
    }

    if (doMemoize && memoize[currentRow][currentCol] == 0) {
      memoize[currentRow][currentCol] =
            findPaths(currentRow + 1, currentCol, endRow, endCol, memoize, doMemoize)
          + findPaths(currentRow, currentCol + 1, endRow, endCol, memoize, doMemoize);
    }

    if (!doMemoize) {
      return findPaths(currentRow + 1, currentCol, endRow, endCol, memoize, doMemoize)
          + findPaths(currentRow, currentCol + 1, endRow, endCol, memoize, doMemoize);
    }

    return memoize[currentRow][currentCol];
  }

  @Test
  public void test() {
    int expected = 2;
    int actual = findPaths(0, 0, 1, 1, new int[2][2], false);
    Assert.assertEquals(actual, expected);
  }

  @Test
  public void test2() {
    int expected = 6;
    int actual = findPaths(0, 0, 2, 2, new int[3][3], true);
    Assert.assertEquals(actual, expected);
  }

  @Test
  public void test3() {
    int expected = 985525432;

    // will not work if doMemoize=false

    int actual = findPaths(0, 0, 19, 19, new int[20][20], true);
    Assert.assertEquals(actual, expected);
  }
}
