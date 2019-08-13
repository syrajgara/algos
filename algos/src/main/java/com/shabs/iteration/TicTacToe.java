package com.shabs.iteration;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * win when you have 3 of same color (black or white) in a row or column or diagonal
 * - 3x3 board
 * - loop thru for rows and cols
 * - loop thru for diagonals - positive and negative slopes
 * <p>
 * - NxN board
 * keep 0 for no color, 1 for player 1, ((1*N)+1) for player 2, (N*(N+1)+1) ...
 * for 3x3 matrix, 0 unassigned, 1 for player 1, 4 for player 2, 13 for player 3, etc...
 * with this assignment
 * - sum of row = 3 => player1 won
 * - sum of row = 12 => player2 won
 * similarly sum of col and diagonals will tell you which player won.
 */
public class TicTacToe {
  private static int checkWinner3x3(int[][] board, int color) {
    boolean rowWin = false;
    boolean columnWin = false;

    for (int i = 0; i < board.length; i++) {
      // reset for next row/column
      rowWin = true;
      columnWin = true;

      for (int j = 0; j < board.length; j++) {
        if (board[i][j] != color) // row constant with i, column variable with j
        {
          rowWin = false;
        }

        if (board[j][i] != color) // column constant with i, row variable with j
        {
          columnWin = false;
        }
      }

      if (rowWin || columnWin) {
        break; // we found the winner
      }
    }

    if (rowWin) {
      return 1;
    }
    if (columnWin) {
      return 2;
    }

    if (checkDiagonal(board, color)) {
      return 3;
    }

    return 0; // did not win
  }

  private static boolean checkDiagonal(int[][] board, int color) {
    boolean diagonalPositiveSlopeWin = true;
    boolean diagonalNegativeSlopeWin = true;

    for (int j = 0; j < board.length; j++) {
      // negative slope
      if (board[j][j] != color) {
        diagonalNegativeSlopeWin = false;
      }

      // positive slope
      // check diagonals from (0,0) to (n-1,n-1) or (0,n-1) to (n-1,0)
      if (board[board.length - 1 - j][j] != color) // positive slope
      {
        diagonalPositiveSlopeWin = false;
      }
    }

    return diagonalPositiveSlopeWin || diagonalNegativeSlopeWin;

  }

  @Test
  public void oneNoWin3x3Board() {
    int[][] board = {
        {2, 1, 1},
        {2, 1, 0},
        {2, 0, 2}
    };
    int color = 1; // could be 1 or 2

    int win = TicTacToe.checkWinner3x3(board, color);
    Assert.assertEquals(win, 0);
  }

  @Test
  public void oneWin3x3BoardRow() {
    int[][] board = {
        {2, 2, 1},
        {1, 1, 1},
        {0, 2, 2}
    };
    int color = 1; // could be 1 or 2

    int win = TicTacToe.checkWinner3x3(board, color);
    Assert.assertEquals(win, 1);
  }

  @Test
  public void oneWin3x3BoardColumn() {
    int[][] board = {
        {2, 1, 1},
        {2, 1, 0},
        {0, 1, 2}
    };
    int color = 1; // could be 1 or 2

    int win = TicTacToe.checkWinner3x3(board, color);
    Assert.assertEquals(win, 2);
  }

  @Test
  public void oneWin3x3BoardDiagonal() {
    int[][] board = {
        {2, 2, 1},
        {1, 1, 0},
        {1, 2, 2}
    };
    int color = 1; // could be 1 or 2

    int win = TicTacToe.checkWinner3x3(board, color);
    Assert.assertEquals(win, 3);
  }
}
