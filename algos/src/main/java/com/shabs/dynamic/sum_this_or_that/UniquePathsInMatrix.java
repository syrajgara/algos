package com.shabs.dynamic.sum_this_or_that;

import com.shabs.datastructures.node.Point;
import com.shabs.datastructures.stack.Stack;
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

    if (!doMemoize) {
      return findPaths(currentRow + 1, currentCol, endRow, endCol, memoize, doMemoize)
          + findPaths(currentRow, currentCol + 1, endRow, endCol, memoize, doMemoize);
    }

    if (doMemoize && memoize[currentRow][currentCol] == 0) {
      memoize[currentRow][currentCol] =
            findPaths(currentRow + 1, currentCol, endRow, endCol, memoize, doMemoize)
          + findPaths(currentRow, currentCol + 1, endRow, endCol, memoize, doMemoize);
    }
    return memoize[currentRow][currentCol];
  }

  public int findPathsIterative(int startRow, int startCol, int endRow, int endCol, int[][] memoize) {

    memoize[endRow][endCol] = 1; // path at the end point is 1

    Stack<Point> pointStack = new Stack<>();
    pointStack.push(new Point(startRow, startCol));

    while (!pointStack.isEmpty()) {
      Point currentPoint = pointStack.peek().getData();

      if (currentPoint.row > endRow || currentPoint.col > endCol) {
        // stepping out of the matrix, no paths - this currentPoint not useful
        pointStack.pop();
        continue;
      }

      if (memoize[currentPoint.row][currentPoint.col] != 0) {
        // we have already calculated this
        pointStack.pop();
        continue;
      }

      if (currentPoint.row + 1 <= endRow && memoize[currentPoint.row + 1][currentPoint.col] == 0) {
        pointStack.push(new Point(currentPoint.row + 1, currentPoint.col));
        continue;
      }

      if (currentPoint.col + 1 <= endCol && memoize[currentPoint.row][currentPoint.col + 1] == 0) {
        pointStack.push(new Point(currentPoint.row, currentPoint.col + 1));
        continue;
      }

      int rightPaths = 0;
      if (currentPoint.col + 1 <= endCol) {
        rightPaths = memoize[currentPoint.row][currentPoint.col + 1];
      }

      int downPaths = 0;
      if (currentPoint.row + 1 <= endRow) {
        downPaths = memoize[currentPoint.row + 1][currentPoint.col];
      }

      memoize[currentPoint.row][currentPoint.col] = rightPaths + downPaths;

      pointStack.pop();
    }

    return memoize[startRow][startCol]; // we should have calculated this by now
  }

  private void print(int[][] memoize) {
    for (int row = 0; row < memoize.length; row++) {
      for (int col = 0; col < memoize[0].length; col++) {
        System.out.print(memoize[row][col] + ",");
      }
      System.out.println();
    }

  }

  @Test
  public void test() {
    int expected = 2;
    int[][] memoize = new int[2][2];

    int actual = findPaths(0, 0, 1, 1, memoize, false);
    Assert.assertEquals(actual, expected);
  }

  @Test
  public void test2() {
    int expected = 6;
    int[][] memoize = new int[3][3];

    int actual = findPaths(0, 0, 2, 2, memoize, true);
    Assert.assertEquals(actual, expected);
    print(memoize);
  }

  @Test
  public void test3() {
    int expected = 40116600;

    // overflows after 16
    int[][] memoize = new int[15][15];

    // will not work if doMemoize=false

    int actual = findPaths(0, 0, 14, 14, memoize, true);
    Assert.assertEquals(actual, expected);
    print(memoize);
  }

  @Test
  public void testIterative() {
    int expected = 40116600;

    // overflows after 16
    int[][] memoize = new int[15][15];

    // will not work if doMemoize=false

    int actual = findPathsIterative(0, 0, 14, 14, memoize);
    Assert.assertEquals(actual, expected);
    print(memoize);
  }
}
