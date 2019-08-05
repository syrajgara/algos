package com.shabs.datastructures.matrix;

import org.testng.annotations.Test;

/**
 * given a matrix[n][n] - rotate the elements by 90 degrees
 */
public class ImagePixelRotate {
  public static void rotate(int[][] image) {
    int n = image.length - 1; // 3 not 4
    for (int row = 0; row <= n / 2; row++) {
      // at level 0 only 3 rotation not 4
      // at level 1 only 1 rotation not 2
      // ie the cells that intersects (the 4 corner cells - {0,0},{0,3},{3,3},{3,0})
      // should not be double rotated, so ignore those from the trailing end.
      for (int col = 0 + row; col < n - row; col++) {

        int temp = image[row][col];

        // left column copy to top row
        image[row][col] = image[n - col][row];

        // bottom row copy to left column
        image[n - col][row] = image[n - row][n - col];

        // right column copy to bottom row
        image[n - row][n - col] = image[col][n - row];

        // top row copy to right column
        image[col][n - row] = temp;

        //print(image);
      }
      //print(image);
    }
    print(image);
  }

  public static void print(int[][] image) {
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image.length; j++) {
        System.out.print(image[i][j] + " ");
      }
      System.out.println(" ");
    }
    System.out.println();
  }

  @Test
  public void test() {
    rotate(buildImage(4));
    System.out.println("======================= ");
    rotate(buildImage(6));
  }

  private static int[][] buildImage(int length) {
    int[][] image = new int[length][length];
    int count = 1;
    for (int row = 0; row < length; row++) {
      for (int col = 0; col < length; col++) {
        image[row][col] = count++;
      }
    }

    print(image);

    return image;
  }
}
