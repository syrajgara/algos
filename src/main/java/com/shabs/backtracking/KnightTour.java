package com.shabs.backtracking;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Knight on a chess board needs to move to cover the entire chess board,
 * but stepping on each cell only once.
 * <p>
 * put knight on one location, find all valid moves based on that (max 8 moves)
 * pick first move and move the knight, recursively go to next move
 * if no moves valid, un-wind the call-stack and try next valid move
 */
public class KnightTour {
  private static boolean moveKnight(int[][] board, Point previousPoint, int previousStepNumber) {
    List<Point> allPossibleNextMoves = findAllPossibleNextMoves(board, previousPoint);

    int currentStep = previousStepNumber + 1;

    if (currentStep > 62) {
      // takes too long to run beyond 62 steps
      // TODO probably no solution beyond this - need to check.
      return true;
    }

    for (Point currentMovePoint : allPossibleNextMoves) {
      recordMove(board, currentMovePoint, currentStep);

      if (moveKnight(board, currentMovePoint, currentStep)) {
        // this move now becomes a valid move
        return true;
      }

      unRecordMove(board, currentMovePoint, currentStep);
    }

    return false;
  }

  private static List<Point> findAllPossibleNextMoves(int[][] board, Point fromPoint) {
    // a knight will have max of 8 valid moves from its current location
    List<Point> validMoves = new ArrayList<>();

    int row;
    int col;
    int bLen = board.length;

    // Knight Move +2 and then +1 or -1 ... in each of the 4 directions

    row = fromPoint.row + 2;
    col = fromPoint.column + 1;
    if (row < bLen && col < bLen && board[row][col] == 0) {
      validMoves.add(new Point(row, col));
    }

    row = fromPoint.row + 2;
    col = fromPoint.column - 1;
    if (row < bLen && col >= 0 && board[row][col] == 0) {
      validMoves.add(new Point(row, col));
    }

    row = fromPoint.row - 2;
    col = fromPoint.column + 1;
    if (row >= 0 && col < bLen && board[row][col] == 0) {
      validMoves.add(new Point(row, col));
    }

    row = fromPoint.row - 2;
    col = fromPoint.column - 1;
    if (row >= 0 && col >= 0 && board[row][col] == 0) {
      validMoves.add(new Point(row, col));
    }

    col = fromPoint.column + 2;
    row = fromPoint.row + 1;
    if (row < bLen && col < bLen && board[row][col] == 0) {
      validMoves.add(new Point(row, col));
    }

    col = fromPoint.column + 2;
    row = fromPoint.row - 1;
    if (row >= 0 && col < bLen && board[row][col] == 0) {
      validMoves.add(new Point(row, col));
    }

    col = fromPoint.column - 2;
    row = fromPoint.row + 1;
    if (row < bLen && col >= 0 && board[row][col] == 0) {
      validMoves.add(new Point(row, col));
    }

    col = fromPoint.column - 2;
    row = fromPoint.row - 1;
    if (row >= 0 && col >= 0 && board[row][col] == 0) {
      validMoves.add(new Point(row, col));
    }

    return validMoves;
  }

  private static void recordMove(int[][] board, Point nextMovePoint, int stepNumber) {
    board[nextMovePoint.row][nextMovePoint.column] = stepNumber;
    //printBoard(board);
  }

  private static void unRecordMove(int[][] board, Point nextMovePoint, int stepNumber) {
    board[nextMovePoint.row][nextMovePoint.column] = 0;
    //printBoard(board);
  }

  private static void printBoard(int[][] board) {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        System.out.print(board[i][j] + " ");
      }
      System.out.println(" ");
    }
    System.out.println("---------------------------");
  }

  private static class Point {
    int row;
    int column;

    Point(int row, int column) {
      this.row = row;
      this.column = column;
    }
  }

  @Test
  public void test() {
    int[][] board = {
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0}
    };

    // start the first step
    int currentStep = 1;
    board[0][0] = 1;
    Point currentPoint = new Point(0, 0);

    moveKnight(board, currentPoint, currentStep);
    printBoard(board);
  }
}
