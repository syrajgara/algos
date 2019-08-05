package com.shabs.datastructures.matrix;

/**
 * TODO
 * reshape m*n matrix to r*c -- like the matlab reshape function
 */
public class ReshapeMatrix {
  public int[][] matrixReshape(int[][] nums, int r, int c) {
    int n = nums.length, m = nums[0].length;
    if (r*c != n*m) return nums;
    int[][] res = new int[r][c];
    for (int i=0;i<r*c;i++)
      res[i/c][i%c] = nums[i/m][i%m];

    return res;
  }
}
