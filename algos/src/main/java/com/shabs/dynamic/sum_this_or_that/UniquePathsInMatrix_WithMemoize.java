package com.shabs.dynamic.sum_this_or_that;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Find number of ways to move from (0,0) to (row,col), if you can only move right or down.
 * we will consider (row,col) to be the bottom right corner of the grid
 * and will stop if we are going out of the grid.
 */
public class UniquePathsInMatrix_WithMemoize {

  private static class Point {
    public int x;
    public int y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
      return (this.x == ((Point) obj).x && this.y == ((Point) obj).y);
    }

    @Override
    public int hashCode() {
      // have to override hashCode for HashMap containsKey to work correctly.
      return 10000 * this.x + this.y;
    }
  }

  public static int moveToXY(Point fromPoint, Point toPoint, Map<Point, Integer> memoize) {
    if (fromPoint.equals(toPoint)) {
      return 1; // we found the path
    }

    if (fromPoint.x > toPoint.x || fromPoint.y > toPoint.y) {
      return 0; // we are out of the grid, not valid
    }

    if (!memoize.containsKey(fromPoint)) {
      int waysIfWeGoRight = moveToXY(new Point(fromPoint.x + 1, fromPoint.y), toPoint, memoize);
      int waysIfWeGoDown = moveToXY(new Point(fromPoint.x, fromPoint.y + 1), toPoint, memoize);

      memoize.put(fromPoint, waysIfWeGoRight + waysIfWeGoDown);
    }

    return memoize.get(fromPoint);
  }

  @Test
  public void test() {
    Point fromPoint = new Point(0, 0);
    Point toPoint = new Point(3, 3);
//        Point toPoint = new Point(20,20);

    Map<Point, Integer> memoize = new HashMap<>();

    int numberOfWays = UniquePathsInMatrix_WithMemoize.moveToXY(fromPoint, toPoint, memoize);
    System.out.println("Ways = " + numberOfWays);
  }
}
