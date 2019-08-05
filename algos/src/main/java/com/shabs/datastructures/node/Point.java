package com.shabs.datastructures.node;

public class Point {
  public int row;
  public int col;

  public Point(int row, int col) {
    this.row = row;
    this.col = col;
  }

  @Override
  public boolean equals(Object that) {
    if (that == null || !(that instanceof Point)) {
      return false;
    }

    Point thatP = (Point) that;
    return this.row == thatP.row && this.col == thatP.col;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 31 * hash + row;
    hash = 31 * hash + col;

    return hash;
  }

  public void print() {
    System.out.print("row=" + row + " col=" + col + "; ");
  }
}
