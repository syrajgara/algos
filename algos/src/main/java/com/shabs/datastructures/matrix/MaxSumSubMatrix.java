package com.shabs.datastructures.matrix;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Find the sub-matrix with max sum.
 * Note: this matrix could include negative numbers,
 * as long as the addition of negative is compensated by other bigger postives,
 * to give large total sum
 *
 * rectangles rowTop to rowBottom + colLeft to colRight
 * rowTop moves from 0 to height
 *   rowBottom moves from rowTop to height
 * colLeft starts at 0 - reset only if sum becomes < 0
 *   colRight moves from colLeft to width
 *
 * keep track of max sum
 * keep track of indexes at max sum
 */
public class MaxSumSubMatrix {

  private int matrixSum(int[][] input) {
    int height = input.length;   // Y axis
    int width = input[0].length; // X axis

    // keeps track of max values across rows and columns
    int overallMaxSum = 0;

    // start cell
    int overallMaxSumRowIndexStart = 0;
    int overallMaxSumColumnIndexStart = 0;

    // end cell
    int overallMaxSumRowIndexEnd = 0;
    int overallMaxSumColumnIndexEnd = 0;

    // loop from top row
    for (int rowTop = 0; rowTop < height; rowTop++) {
      // to bottom row
      for (int rowBottom = rowTop; rowBottom < height; rowBottom++) {
        int startColumnIndex = 0;
        int columnSums = 0;

        int startColumnIndexForMaxSum = startColumnIndex;
        int endColumnIndexForMaxSum = startColumnIndex;
        int columnSumsForMaxSum = 0;

        // for each combination of topRow to bottomRow, loop thru each column
        for (int column = 0; column < width; column++) {
          // for all combination of (rowTop, rowBottom) get columnwise sum
          int currentColumnSum = 0;

          // for each column, find sum from topRow to bottomRow
          for (int rowToBeSummed = rowTop; rowToBeSummed <= rowBottom; rowToBeSummed++) {
            currentColumnSum += input[rowToBeSummed][column];
          }

          // and either use this column or reset based on whether adding this column keeps the sum positive
          if (columnSums + currentColumnSum > 0) {
            // if adding this column increases the sum, add it to our sum
            columnSums = columnSums + currentColumnSum;
            if (columnSums > columnSumsForMaxSum) {
              startColumnIndexForMaxSum = startColumnIndex;
              endColumnIndexForMaxSum = column;

              columnSumsForMaxSum = columnSums;
            }
          } else {
            // adding this column makes the sum negative, stop at previous column
            startColumnIndex = column + 1;
            columnSums = 0;
          }
        }

        // for given topRow to bottomRow combination we have found the start and end column
        // if this combination of [x1, y1] to [x2, y2] is greater than overall sum we already have
        // then update the indexes and sum
        if (columnSumsForMaxSum > overallMaxSum) {
          overallMaxSum = columnSumsForMaxSum;
          overallMaxSumRowIndexStart = rowTop;
          overallMaxSumRowIndexEnd = rowBottom;
          overallMaxSumColumnIndexStart = startColumnIndexForMaxSum;
          overallMaxSumColumnIndexEnd = endColumnIndexForMaxSum;
        }
      }
    }

    // we have loop thru all combinations of start/end rows and start/end columns - so print max.

    System.out.println("Max Sum = " + overallMaxSum + " for [" + overallMaxSumRowIndexStart + ", " + overallMaxSumColumnIndexStart + "] - [" + overallMaxSumRowIndexEnd + ", " + overallMaxSumColumnIndexEnd + "]");

    return overallMaxSum;
  }

  @Test
  public void matrixSum() {
    int[][] input = {
        { 1,    2, -1, -4,   -20}, //-2

        {-8,   -3,  4,  2,     1}, // 3
        { 3,    8, 10,  1,     3}, // 19
        {-4,   -1,  1,  7,    -6}  // 7
    };

    int expected = 29;
    int actual = matrixSum(input);

    Assert.assertEquals(actual, expected);
  }
}
