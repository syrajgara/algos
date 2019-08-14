package com.shabs.dynamic.array;

import com.shabs.datastructures.node.Node;
import com.shabs.datastructures.queue.Queue;
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
public class LongestIncreasingPathInAMatrixIterative {

  private int findPaths(int[][] input) {
    int overallMaxLength = 0;

    int[][] pathLength = new int[input.length][input[0].length];

    for (int row = 0; row < input.length; row++) {
      for (int col = 0; col < input[0].length; col++) {
        calcPaths(input, pathLength, row, col);
        overallMaxLength = Math.max(overallMaxLength, pathLength[row][col]);
      }
    }

    return overallMaxLength;
  }

  private class Cell {
    int row;
    int col;
    int len;

    public Cell(int r, int c, int l) {
      row = r;
      col = c;
      len = l;
    }
  }

  private void calcPaths(int[][] input, int[][] pathLength, int row, int col) {
    if (pathLength[row][col] != 0) {
      return;
    }

    Queue<Cell> queue = new Queue<>();
    queue.enqueue(new Cell(row, col, 1));

    while (!queue.isEmpty()) {
      Node<Cell> node = queue.dequeue();
      Cell currCell = node.getData();
      int currCellValue = input[currCell.row][currCell.col];

      // possibly increase the pathLength for the input cell
      int alreadyCalcLen = currCell.len;
      pathLength[row][col] = Math.max(pathLength[row][col], alreadyCalcLen);

      alreadyCalcLen = enqueue(input, pathLength, currCell.row+1, currCell.col, currCellValue, currCell.len, queue);
      pathLength[row][col] = Math.max(pathLength[row][col], alreadyCalcLen);

      alreadyCalcLen = enqueue(input, pathLength, currCell.row-1, currCell.col, currCellValue, currCell.len, queue);
      pathLength[row][col] = Math.max(pathLength[row][col], alreadyCalcLen);

      alreadyCalcLen = enqueue(input, pathLength, currCell.row, currCell.col+1, currCellValue, currCell.len, queue);
      pathLength[row][col] = Math.max(pathLength[row][col], alreadyCalcLen);

      alreadyCalcLen = enqueue(input, pathLength, currCell.row, currCell.col-1, currCellValue, currCell.len, queue);
      pathLength[row][col] = Math.max(pathLength[row][col], alreadyCalcLen);
    }
  }

  private int enqueue(int[][] input, int[][] pathLength, int row, int col, int prevValue, int newLen, Queue<Cell> queue) {
    if (row < 0 || row >= input.length || col < 0 || col >= input[0].length) {
      return 0;
    }

    if (input[row][col] <= prevValue) {
      return 0;
    }

    if (pathLength[row][col] != 0) {
      return pathLength[row][col];
    }

    queue.enqueue(new Cell(row, col, newLen + 1));

    return 0; // we just enqueued it, will know value later, for now return 0 to ignore
  }

  // @Test - error due to duplicate values
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

