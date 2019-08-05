package com.shabs.datastructures.matrix;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class FindRectangles {

  @Test
  public void test() {
    int[][] image = {
        {1, 0, 1, 1, 1, 1, 1},
        {1, 0, 0, 1, 0, 1, 1},
        {1, 1, 1, 0, 0, 0, 1},
        {1, 0, 1, 1, 0, 1, 1},
        {1, 0, 1, 1, 1, 1, 1},
        {1, 0, 0, 0, 0, 1, 1},
        {1, 1, 1, 0, 0, 1, 1},
        {1, 1, 1, 1, 1, 1, 1},
    };

    List<Rectangle> rectangles = findRectangle(image);
    for (Rectangle r : rectangles) {
      r.print();
    }

    // O(r * c)
    // O(1)
  }

  public static class Cell {
    public int row;
    public int col;

    public Cell() {
    }

    public Cell(int row, int col) {
      this.row = row;
      this.col = col;
    }
  }

  public static class Rectangle {
    Cell startCell;
    int length;
    int height;

    public Rectangle() {
    }

    public Rectangle(Cell cell, int length, int height) {
      this.startCell = cell;
      this.length = length;
      this.height = height;
    }

    public void print() {
      System.out.println(startCell.row + " " + startCell.col
          + " " + length + " " + height);
    }
  }

  public static List<Rectangle> findRectangle(int[][] image) {

    List<Rectangle> rectangles = new ArrayList<>();

    for (int row = 0; row < image.length; row++) {
      for (int col = 0; col < image[0].length; col++) {

        if (image[row][col] == 0
            && (col == 0 || image[row][col - 1] != 0)
            && (row == 0 || image[row - 1][col] != 0)
            ) {

          Cell cell = new Cell(row, col);

          int length = findLength(image, cell);
          int height = findHeight(image, cell);

          rectangles.add(new Rectangle(cell, length, height));
        }

      }
    }

    return rectangles;
  }

  public static int findLength(int[][] image, Cell cell) {
    int length = 0;

    for (int col = cell.col; col < image[0].length; col++) {
      if (image[cell.row][col] != 0) {
        break;
      }
      length++;
    }

    return length;
  }

  public static int findHeight(int[][] image, Cell cell) {
    int length = 0;
    for (int row = cell.row; row < image.length; row++) {
      if (image[row][cell.col] != 0) {
        break;
      }
      length++;
    }

    return length;
  }
}

