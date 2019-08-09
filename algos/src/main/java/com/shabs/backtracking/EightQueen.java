package com.shabs.backtracking;

import org.testng.annotations.Test;

/**
 * place 8 queens on the chessboard, such that they cannot kill each other.
 * <p>
 * place a queen per row,
 * get row number and figure out all the places the queen can go on that row
 * put queen on first (loop) of those valid
 * using that board, put queen on the next row
 * similarly on next row select the first valid location and then put on next row recursively
 * if next is success, this row is success too. else unwind and try the next valid location for this row
 * un-wind the recurssive calls in this manner.
 */
public class EightQueen {
  public EightQueen() {
  }

  private static boolean placeQueen(int[][] board, int row) {
    if (row == board.length) {
      // we have completed the board
      return true;
    }

    int[] possibleColumns = findAllPossibleLocationsOnRow(board, row);
    for (int j = 0; j < board.length; j++) {
      if (possibleColumns[j] == 0) {
        continue;
      }

      confirmQueenOn(board, row, j);
      if (placeQueen(board, row + 1)) {
        // we were able to confirm next row as well, so this row is good for sure.
        // this move now becomes a valid move
        return true;
      }

      // with this selection we were not able to confirm next row.
      // so revert this row and try for next valid column on this row.
      unConfirmQueenOn(board, row, j);
    }

    return false;
  }

  private static void confirmQueenOn(int[][] board, int row, int column) {
    board[row][column] = 1;
    System.out.println("Confirming Row " + row);
    printBoard(board);
  }

  private static void unConfirmQueenOn(int[][] board, int row, int column) {
    board[row][column] = 0;
    System.out.println("Un-Confirming Row " + row);
    printBoard(board);
  }

  private static int[] findAllPossibleLocationsOnRow(int[][] board, int row) {
    int[] validColumns = new int[board.length];

    // check all columns for given row
    for (int column = 0; column < board.length; column++) {
      validColumns[column] = 0;
      if (isValidLocation(board, row, column)) {
        validColumns[column] = 1;
      }
    }

    return validColumns;
  }

  private static boolean isValidLocation(int[][] board, int row, int column) {
    for (int i = 1; i <= row; i++) {
      int rowToCheck = row - i; // start from previous row, all the way to the first row

      // vertical lines
      if (board[rowToCheck][column] == 1) {
        return false;
      }

      // diagonal - negative slope
      if (column - i >= 0 && board[rowToCheck][column - i] == 1) {
        return false;
      }

      // diagonal - positive slope
      if (column + i < board.length && board[rowToCheck][column + i] == 1) {
        return false;
      }
    }

    return true;
  }

  private static void printBoard(int[][] board) {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        System.out.print(board[i][j] + " ");
      }
      System.out.println(" ");
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

    int row = 0;
    placeQueen(board, row);
  }
}
