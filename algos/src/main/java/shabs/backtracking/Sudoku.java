package com.shabs.backtracking;

import com.shabs.datastructures.node.Point;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Create a sudoku board
 * <p>
 * start with #1 and fill it in 9 valid places, then increment and repeat with #2,
 * if no valid position to proceed, backtrack and try another valid position
 * <p>
 * find valid positions for a number and loop thru it
 * on every loop mark it on the board and solve for next instance of that number
 * or if more than 9 instance already, solve for next number
 * if not able to solve, unmark and try next valid location.
 * <p>
 * finding valid location for a number:
 * - loop thru n*n matrix for every point
 * - if point already in use, cant use it
 * - if points row has that number, cant use it
 * - if points column has that number, cant use it
 * - for checking in the small box, find top left cell (row/3 * 3, col/3 * 3)
 * and loop thru that 3x3 box to see if number already there
 */
public class Sudoku {
  private static final int MAX_FREQUENCY_OF_A_NUMBER = 9;
  private static final int MAX_NUMBER = 9;

  private static boolean solveSudoku(int[][] board, int number) {
    //printBoard(board);
    int frequency = findFrequency(board, number);

    if (number == MAX_NUMBER && frequency == MAX_FREQUENCY_OF_A_NUMBER) {
      printBoard(board);
      return true; // setup is now complete
    }

    if (frequency == MAX_FREQUENCY_OF_A_NUMBER) {
      number++; // previous number already setup 9 times, now use next number
    }

    List<Point> possiblePoints = findAllPossiblePoints(board, number);
    for (Point point : possiblePoints) {
      markLocation(board, point, number);

      // setup for next position of same number, we have 9 of each number
      if (solveSudoku(board, number)) {
        return true;
      }

      // revert previous choice
      unmarkLocation(board, point);
    }

    // we didnt have enough validPoints and so setup is incomplete
    return false;
  }

  private static void markLocation(int[][] board, Point point, int number) {
    board[point.row][point.col] = number;
  }

  private static void unmarkLocation(int[][] board, Point point) {
    board[point.row][point.col] = 0;
  }

  private static int findFrequency(int[][] board, int number) {
    int frequency = 0;

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        if (board[i][j] == number) {
          frequency++;
        }
      }
    }
    return frequency;
  }

  private static List<Point> findAllPossiblePoints(int[][] board, int number) {
    List<Point> validPoints = new ArrayList<>();

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        Point newPoint = new Point(i, j);
        if (isValidLocation(board, newPoint, number)) {
          validPoints.add(newPoint);
        }
      }
    }

    return validPoints;
  }

  private static boolean isValidLocation(int[][] board, Point point, int number) {
    if (board[point.row][point.col] != 0) {
      // point already in use
      return false;
    }

    // if same number exists in the whole row or whole column - cannot use that location
    for (int k = 0; k < board.length; k++) {
      if (board[point.row][k] == number || board[k][point.col] == number) {
        return false;
      }
    }

    return isValidInSmallBox(board, point, number);
  }

  private static boolean isValidInSmallBox(int[][] board, Point point, int number) {
    // number can exists only once in the 3x3 box
    int boxStartX = point.row / 3 * 3;
    int boxStartY = point.col / 3 * 3;

    for (int i = boxStartX; i < boxStartX + 3; i++) {
      for (int j = boxStartY; j < boxStartY + 3; j++) {
        if (board[i][j] == number) {
          return false;
        }
      }
    }

    return true;
  }

  private static void printBoard(int[][] board) {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        System.out.print(board[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println("-----------------");
  }

  @Test
  public void test() {
    int[][] board = new int[9][9];

        /*
        // seed numbers
        board[0][0] = 5;
        board[0][1] = 3;
        board[0][4] = 7;

        board[1][0] = 6;
        board[1][3] = 1;
        board[1][4] = 9;
        board[1][5] = 5;

        board[2][1] = 9;
        board[2][2] = 8;
        board[2][7] = 6;

        board[3][0] = 8;
        board[3][4] = 6;
        board[3][8] = 3;

        board[4][0] = 4;
        board[4][3] = 8;
        board[4][5] = 3;
        board[4][8] = 1;

        board[5][0] = 7;
        board[5][4] = 2;
        board[5][8] = 6;

        board[6][1] = 6;
        board[6][6] = 2;
        board[6][7] = 8;

        board[7][3] = 4;
        board[7][4] = 1;
        board[7][5] = 9;
        board[7][8] = 5;

        board[8][4] = 8;
        board[8][7] = 7;
        board[8][8] = 9;
        */

    solveSudoku(board, 1);
  }
}
