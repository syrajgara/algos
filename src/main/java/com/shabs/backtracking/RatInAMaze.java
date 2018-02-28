package com.shabs.backtracking;

import com.shabs.datastructures.node.Point;
import org.testng.annotations.Test;

/**
 * A RatInAMaze is given as N*N binary matrix of blocks where source block is the upper left most block i.e.,
 * maze[0][0] and destination block is lower rightmost block i.e., maze[N-1][N-1].
 * with certain cells not reachable (wall of maze)
 * <p>
 * A rat starts from source and has to reach destination.
 * The rat can move only in two directions: forward and down.
 * <p>
 * put rat in first location, find all valid moves (2 moves) from here - loop thru that.
 * based on this move, recurse to the next move - if no moves valid, un-wind call-stack and try the previous levels next move
 * keep doing this till you reach destination or run out of all valid moves.
 * <p>
 * http://www.geeksforgeeks.org/backttracking-set-2-rat-in-a-maze/
 */
public class RatInAMaze {
  private static boolean findPath(int[][] maze, Point startPoint, Point endPoint) {
    if (startPoint.row == maze.length
        || startPoint.col == maze.length
        // NOTE : X Y reversed in matrix
        || maze[startPoint.col][startPoint.row] == 0) {
      // we are moving out of maze, or ran into no entry (0) ... not good!!
      return false;
    }

    // we keep tracing the path we are taking, so print this point.
    // it could have wrong advancements and backtracking.
    startPoint.print();

    if (startPoint.equals(endPoint)) {
      return true;
    }

    Point moveRight = new Point(startPoint.row + 1, startPoint.col);
    Point moveDown = new Point(startPoint.row, startPoint.col + 1);

    // since we only have 2 ways to go,
    // try going right first, if it doesnt work, backtrack and go down
    boolean found = findPath(maze, moveRight, endPoint)
        || findPath(maze, moveDown, endPoint);
    if (!found) {
      // this is an invalid path so block it
      maze[startPoint.col][startPoint.row] = 0;
    }

    return found;
  }

  public static void print(int[][] maze) {
    System.out.println();
    for (int i = 0; i < maze.length; i++) {
      for (int j = 0; j < maze.length; j++) {
        System.out.print(maze[i][j] + " ");
      }
      System.out.println();
    }
  }

  @Test
  public void test() {
    // 1's are valid path
    // NOTE : maze[Y][X] >>> X and Y are reversed when loading the matrix
    int[][] maze = {{1, 1, 0, 0},
        {1, 1, 1, 1},
        {1, 0, 1, 0},
        {0, 1, 1, 1}};

    Point startPoint = new Point(0, 0);
    Point endPoint = new Point(3, 3);

    print(maze);
    findPath(maze, startPoint, endPoint);
    print(maze);
  }
}
