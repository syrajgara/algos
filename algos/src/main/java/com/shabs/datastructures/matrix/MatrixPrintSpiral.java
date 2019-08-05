package com.shabs.datastructures.matrix;

import org.testng.annotations.Test;

/**
 * Given a m*n matrix, print the cells in a clockwise spiral
 * <p>
 * keep row/col start/end positions and increment/decrement as you go thru the spiral
 * when start is after end, we have covered all elements.
 */
public class MatrixPrintSpiral {
  private void print(int[][] input) {
    if (input == null || input.length == 0) {
      return;
    }

    int colStart = 0;
    int rowStart = 0;

    int colEnd = input[0].length - 1;
    int rowEnd = input.length - 1;

    //System.out.println(xStart + " - " + xEnd);
    //System.out.println(yStart + " - " + yEnd);

    while (colStart <= colEnd && rowStart <= rowEnd) {
      for (int col = colStart; col <= colEnd; col++) {
        System.out.print(input[rowStart][col] + " ");
      }
      rowStart++;
      System.out.println();

      for (int row = rowStart; row <= rowEnd; row++) {
        System.out.print(input[row][colEnd] + " ");
      }
      colEnd--;
      System.out.println();

      for (int col = colEnd; col >= colStart; col--) {
        System.out.print(input[rowEnd][col] + " ");
      }
      rowEnd--;
      System.out.println();

      for (int row = rowEnd; row >= rowStart; row--) {
        System.out.print(input[row][colStart] + " ");
      }
      colStart++;
      System.out.println();
    }
  }

  @Test
  public void print() {
    int[][] input = {
        {1, 2, 3, 4, 20},
        {5, 6, 7, 8, 21},
        {9, 10, 11, 12, 22},
        {13, 14, 15, 16, 23}
    };

    print(input);
  }
}
