package com.shabs.datastructures.matrix;

import org.testng.annotations.Test;

/**
 * reshape m*n matrix to r*c -- like the matlab reshape function
 */
public class ReshapeMatrix {
  public int[][] matrixReshape(int[][] nums, int r, int c) {
    int n = nums.length;
    int m = nums[0].length;

    if (r*c != n*m) return nums;

    int[][] res = new int[r][c];

    for (int i=0;i<r*c;i++) {
      res[i/c][i%c] = nums[i/m][i%m];
    }

    return res;
  }

  @Test
  public void print() {
    int[][] input = {
        {1, 2, 3, 4, 20},
        {5, 6, 7, 8, 21},
        {9, 10, 11, 12, 22},
        {13, 14, 15, 16, 23}
    };

    int r = 5;
    int c = 4;
    int[][] res = matrixReshape(input, r, c);

    for (int row = 0; row < r; row++) {
      for (int col = 0; col < c; col++) {
        System.out.print(res[row][col] + ", ");
      }
      System.out.println();
    }
  }
}
