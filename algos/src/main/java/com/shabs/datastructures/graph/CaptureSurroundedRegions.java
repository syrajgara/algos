package com.shabs.datastructures.graph;

import com.shabs.datastructures.node.Point;
import com.shabs.datastructures.queue.Queue;
import org.testng.annotations.Test;

/**
 * Give a 2d array with 0 and 1s. Mark all 0's as 1's if they are surrounded by 1's
 * - surrounded - horizontally and vertically, not diagonally
 * <p>
 * - for simplicity assuming a square matrix
 * <p>
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * <p>
 * should become
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * <p>
 * Solution:
 * Instead of finding cells that needs to be captured,
 * we will find cells that *cannot* be captured
 * <p>
 * use BFS (or DFS) queue to collect the 0 elements from outer edges of the matrix
 * from these points do a BFS, to markReachableCells all reachable elements to 2
 * anything that is marked as 2 is not captureable, anything 1 is already captured
 * whatever is left 0 at the end when queue is empty is what can be captured.
 */
public class CaptureSurroundedRegions {

  public void capture(int[][] mat) {

    int size = mat.length;

    Queue<Point> queue = new Queue<>();

    // loop thru outer cells.
    // (0,0) -> (0,n-2), (0,n-1) -> (n-2,n-1),
    // (n-1,n-1) -> (n-1,1), (n-1,0) -> (1,0),

    // assuming a square input mat
    for (int i = 0; i < mat.length - 1; i++) {
      // lets just add all outer cells to a queue
      // we will then pick each and work on them
      enqueue(queue, mat, new Point(0, i));
      enqueue(queue, mat, new Point(i, size - 1));
      enqueue(queue, mat, new Point(size - 1, size - 1 - i));
      enqueue(queue, mat, new Point(size - 1 - i, 0));
    }

    markReachableCells(mat, queue);
  }

  private void markReachableCells(int[][] mat, Queue<Point> queue) {
    if (queue.isEmpty()) {
      return; // we have marked everything
    }

    Point currPoint = queue.dequeue().getData();

    // add surrounding - could change code to check if valid point and 0 before putting in queue
    enqueue(queue, mat, new Point(currPoint.row, currPoint.col - 1));
    enqueue(queue, mat, new Point(currPoint.row, currPoint.col + 1));
    enqueue(queue, mat, new Point(currPoint.row - 1, currPoint.col));
    enqueue(queue, mat, new Point(currPoint.row + 1, currPoint.col));

    markReachableCells(mat, queue);
  }

  private void enqueue(Queue<Point> queue, int[][] mat, Point point) {
    if (point.row < 0 || point.row >= mat.length
        || point.col < 0 || point.col >= mat.length) {
      return; // not valid point
    }
    if (mat[point.row][point.col] != 0) {
      return; // this is already marked or is 1
    }

    mat[point.row][point.col] = 2; //markReachableCells visited
    queue.enqueue(point);
  }

  public void print(int[][] mat) {
    for (int row = 0; row < mat.length; row++) {
      for (int col = 0; col < mat[0].length; col++) {
        System.out.print(mat[row][col] + ", ");
      }
      System.out.println();
    }
    System.out.println("--------------------");
  }

  @Test
  public void test1() {
    int[][] mat = {
        {1, 1, 1, 1},
        {1, 0, 0, 1},
        {1, 1, 0, 1},
        {1, 0, 1, 1},
    };

    print(mat);
    capture(mat);
    print(mat);
    System.out.println("====================");
  }

  @Test
  public void test2() {
    int[][] mat = {
        {1, 1, 1, 1},
        {1, 0, 0, 1},
        {1, 1, 0, 1},
        {1, 1, 0, 1},
    };

    print(mat);
    capture(mat);
    // 1 original, 0 captured, 2 not captured
    print(mat);
    System.out.println("====================");
  }
}
