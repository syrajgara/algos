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

    int[][] paths = new int[input.length][input[0].length];

    for (int row = 0; row < input.length; row++) {
      for (int col = 0; col < input[0].length; col++) {
        calcPaths(input, paths, row, col);
        overallMaxLength = Math.max(overallMaxLength, paths[row][col]);
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

  private void calcPaths(int[][] input, int[][] paths, int row, int col) {
    if (paths[row][col] != 0) {
      return;
    }

    Queue<Cell> queue = new Queue<>();
    queue.enqueue(new Cell(row, col, 1));

    while (!queue.isEmpty()) {
      Node<Cell> node = queue.dequeue();
      Cell currCell = node.getData();
      paths[row][col] = Math.max(paths[row][col], currCell.len);

      int alreadyCalcLen;
      alreadyCalcLen = enqueue(input, input[currCell.row][currCell.col], paths, currCell.row+1, currCell.col, currCell.len, queue);
      paths[row][col] = Math.max(paths[row][col], alreadyCalcLen);

      alreadyCalcLen = enqueue(input, input[currCell.row][currCell.col], paths, currCell.row-1, currCell.col, currCell.len, queue);
      paths[row][col] = Math.max(paths[row][col], alreadyCalcLen);

      alreadyCalcLen = enqueue(input, input[currCell.row][currCell.col], paths, currCell.row, currCell.col+1, currCell.len, queue);
      paths[row][col] = Math.max(paths[row][col], alreadyCalcLen);

      alreadyCalcLen = enqueue(input, input[currCell.row][currCell.col], paths, currCell.row, currCell.col-1, currCell.len, queue);
      paths[row][col] = Math.max(paths[row][col], alreadyCalcLen);
    }
  }

  private int enqueue(int[][] input, int prevValue, int[][] paths, int row, int col, int newLen, Queue<Cell> queue) {
    int alreadyCalcLen = 0;

    if (row < 0 || row >= input.length || col < 0 || col >= input[0].length) {
      return alreadyCalcLen;
    }

    if (input[row][col] <= prevValue) {
      return alreadyCalcLen;
    }

    if (paths[row][col] != 0) {
      return paths[row][col];
    }

    queue.enqueue(new Cell(row, col, newLen + 1));

    return alreadyCalcLen;
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

