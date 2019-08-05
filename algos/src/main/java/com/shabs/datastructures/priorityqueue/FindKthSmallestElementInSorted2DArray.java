package com.shabs.datastructures.priorityqueue;

import com.shabs.datastructures.node.Point;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * PriorityQueue - minHeap
 *
 * in m*n matrix where rows and columns are sorted
 * find the Kth smallest element
 *
 * - a cell is smaller than its right and bottom cells
 * - put the cell (0,0) in priority queue (minHeap)
 * - remove the smallest cell from the minHeap - if this is the kth pick you have your element
 * - else add the right and bottom cells of this current cell
 * since current cell is smaller than the right and bottom
 * - continue till you pick K cells
 */
public class FindKthSmallestElementInSorted2DArray {

  private int find(int[][] input, int k) {

/*
    For getting Kth Largest instead of Kth smallest
    PriorityQueue<Point> maxHeap = new PriorityQueue<>(k,
        (p1,p2) -> -1 * Integer.compare(input[p1.row][p1.col], input[p2.row][p2.col])
    );

    maxHeap.add(new Point(input.length, input[0].length));
*/

    PriorityQueue<Point> minHeap = new PriorityQueue<>(k, Comparator.comparingInt(p -> input[p.row][p.col]));

    Point p = new Point(0, 0);
    minHeap.add(p);

    for (int i = 1; i <= k; i++) {
      p = minHeap.remove();

      System.out.println(i + " - " + input[p.row][p.col]);

      if (p.row + 1 < input.length) {
        addPoint(minHeap, p.row + 1, p.col);
      }

      if (p.col + 1 < input[0].length) {
        addPoint(minHeap, p.row, p.col + 1);
      }
    }

    return input[p.row][p.col];
  }

  private void addPoint(PriorityQueue<Point> pq, int row, int col) {
    if (!pq.contains(new Point(row, col))) {
      pq.add(new Point(row, col));
    }
  }

  @Test
  public void test() {
    int[][] input = {
        {1, 2, 3, 4},
        {5, 6, 7, 8},
        {9, 10, 11, 12},
        {13, 14, 15, 16}
    };

    int k = 7;
    int expected = 7;

    int actual = find(input, k);

    Assert.assertEquals(actual, expected);
  }
}
