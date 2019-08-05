package com.shabs.datastructures.map;

import com.shabs.datastructures.node.Point;
import com.shabs.recurssion.GCD;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an array of points, find maximum number of points that can fall on a line
 *
 * - need to compare a point with all points after it
 * - need to have same slope and y intercepts
 * - this can be checked by comparing the GCD of delta of x and GCD of delta of y between points
 */
public class MaxPointsOnALine {

  public int findPointOnLine(List<Point> points) {
    int maxOverlaps = 0;

    for (int i = 0; i < points.size(); i++) {

      Point startPoint = points.get(i);

      Map<Integer, Map<Integer, Integer>> xyOverlaps = new HashMap<>();
      int maxOverlapsForThisPoint = 0;

      for (int j = i+1; j < points.size(); j++) {

        Point endPoint = points.get(j);
        int xDelta = Math.abs(endPoint.row - startPoint.row);
        int yDelta = Math.abs(endPoint.col - startPoint.col);

        // if one of the delta is zero
        // - it is either horizontal or vertical line
        // - consider other as unit length
        // since we cannot do GCD for this case
        if (xDelta == 0) {
          yDelta = 1;
        } else if (yDelta == 0) {
          xDelta = 1;
        } else {
          // reduce the deltas to the min possible
          int gcd = GCD.compute(xDelta, yDelta);
          if (gcd != 0) {
            xDelta /= gcd;
            yDelta /= gcd;
          }
        }

        if (xyOverlaps.containsKey(xDelta)) {
          xyOverlaps.get(xDelta).put(yDelta, xyOverlaps.get(xDelta).getOrDefault(yDelta, 0) + 1);
        } else {
          Map<Integer, Integer> yOverlap = new HashMap<>();
          yOverlap.put(yDelta, 1);
          xyOverlaps.put(xDelta, yOverlap);
        }

        maxOverlapsForThisPoint = Math.max(maxOverlapsForThisPoint, xyOverlaps.get(xDelta).get(yDelta));
      }

      maxOverlaps = Math.max(maxOverlaps, maxOverlapsForThisPoint);
    }

    return maxOverlaps;
  }

  @Test
  public void test() {
    Point p1 = new Point(2,2);
    Point p2 = new Point(2,4);
    Point p3 = new Point(4,4);
    Point p4 = new Point(8,8);

    List<Point> points = new ArrayList<>();
    points.add(p1);
    points.add(p2);
    points.add(p3);
    points.add(p4);

    int expected = 3;
    int actual = findPointOnLine(points);
  }
}
