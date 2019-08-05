package com.shabs.datastructures.graph;

import com.shabs.datastructures.node.Point;
import com.shabs.datastructures.queue.Queue;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * given a 2d matrix, with 0 for water and 1 for land.
 * find number of islands.
 * land connects another land if they are linked horizontally or vertically, not diagonally.
 * <p>
 * loop thru each cell to find cell with 1 (land)
 * for each find
 * - do a DFS or BFS and re-mark these cells to the island number, increment island on each start
 * - once a cell is used for an island it wont get reused
 * <p>
 * for DFS or BFS, need to add all horizontal and vertical cells - on each of the 4 sides of current cell,
 * if it is still 1.
 */
public class NumberOfIslands {

  public static int countIsland(int[][] world) {

    printWorld(world);

    int islandNumber = 10; // since 1 is used to in original matrix, starting from 10 for marking purpose

    for (int row = 0; row < world.length; row++) {
      for (int col = 0; col < world[0].length; col++) {
        if (world[row][col] == 1) {
          islandNumber++;

          Queue<Point> queue = new Queue<>();
          queue.enqueue(new Point(row, col));
          markIsland(world, queue, islandNumber);
        }
      }
    }

    printWorld(world);

    return islandNumber-10;
  }

  private static void printWorld(int[][] world) {
    for (int row = 0; row < world.length; row++) {
      for (int col = 0; col < world[0].length; col++) {
        System.out.print(world[row][col] + ", ");
      }
      System.out.println();
    }
    System.out.println();
  }

  private static void markIsland(int[][] world, Queue<Point> queue, int islandNumber) {

    if (queue.isEmpty()) {
      return;
    }

    Point currentPoint = queue.dequeue().getData();

    if (world[currentPoint.row][currentPoint.col] == 1) { // else point already in use or water
      world[currentPoint.row][currentPoint.col] = islandNumber;

      if (currentPoint.row + 1 < world.length) {
        queue.enqueue(new Point(currentPoint.row + 1, currentPoint.col));
      }

      if (currentPoint.row - 1 >= 0) {
        queue.enqueue(new Point(currentPoint.row - 1, currentPoint.col));
      }

      if (currentPoint.col + 1 < world[0].length) {
        queue.enqueue(new Point(currentPoint.row, currentPoint.col + 1));
      }

      if (currentPoint.col - 1 >= 0) {
        queue.enqueue(new Point(currentPoint.row, currentPoint.col - 1));
      }
    }

    markIsland(world, queue, islandNumber);
  }

  @Test
  public void testOneIsland() {
    int[][] world = {
        {1, 1, 1, 1, 0},
        {1, 1, 0, 1, 0},
        {1, 1, 0, 0, 0},
        {0, 0, 0, 0, 0}
    };

    int expected = 1;

    int actual = countIsland(world);

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void testTwoIsland() {
    int[][] world = {
        {1, 1, 0, 0, 0},
        {1, 1, 0, 0, 0},
        {0, 0, 1, 0, 0},
        {0, 0, 0, 1, 1},
        {0, 0, 0, 1, 1}
    };

    int expected = 3;

    int actual = countIsland(world);

    Assert.assertEquals(actual, expected);
  }
}
