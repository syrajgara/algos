package com.shabs.datastructures.priorityqueue;

import com.shabs.datastructures.node.Point;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * http://www.cs.princeton.edu/courses/archive/spr10/cos226/assignments/8puzzle.html
 * <p>
 * Code is for N-Puzzle
 * N row N matrix with (N*N-1) numbers, 0 represents a blank cell.
 * <p>
 * create a board
 * - that can clone itself
 * - keep track of HAMMING DISTANCE (every element not in the correct location, hamming distance increases by 1)
 * - keep track of HAMMING PRIORITY (hamming distance + moves to reach this hamming distance on this board)
 * - build new boards for next round of exploration - and put them in priority queue
 * <p>
 * - priority queue based on the board's hamming priority
 * - hamming distance should decrease with each round, ie. we should converge to a solution.
 * - if not, there is an issue with input data - and this board is not solvable
 */
public class HammingEightPuzzle {
  private static boolean solve(Board board) {

    // comparator based on hamming priority
    PriorityQueue<Board> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(b -> b.getHammingPriority()));
    priorityQueue.add(board);

    int previousHammingDistance = Integer.MAX_VALUE;
    while (!priorityQueue.isEmpty()) {
      Board currentBoard = priorityQueue.remove();
      currentBoard.print();

            /*
            // other items in the queue
            System.out.println("vvvvvvvvvvvvvvvvvvvvvv");
            for (Board b : priorityQueue)
            {
                b.print();
            }
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^");
            */

      if (currentBoard.getHammingDistance() == 0) {
        // we have solved it
        return true;
      }

      if (currentBoard.getHammingDistance() >= previousHammingDistance) {
        // hamming distance will always decrease with iterations
        // if it increases that means we wont be able to solve this puzzle, probably it is incorrect.
        // for ex: replace 6 with 10 in the input.
        return false;  // we couldnt solve it
      }

      previousHammingDistance = currentBoard.getHammingDistance();

      List<Board> newBoards = currentBoard.buildBoardsForNextState();

      priorityQueue.addAll(newBoards);
    }

    return false; // we wont reach here
  }

  private static class Board {
    private int[][] board;

    // we keep moving numbers to zero location - so for convenience save the location
    private Point locationOfZero = null;

    // store previous state, so that we dont keep looping to old states.
    private Board previousStateOfThisBoard;

    // keep track of move that took to get us here, this adds to hamming priority for future boards
    private int movesToReachThisBoard;

    // hamming priority is going to be hamming distance + moves to reach this board

    // hamming distance for this board.
    // for every element not in the correct location, hamming distance increases by 1.
    private int hammingDistance;


    public Board(int[][] board, int movesToReachThisBoard) {
      this.board = board;
      setLocationOfZero();

      this.movesToReachThisBoard = movesToReachThisBoard;
      setHammingDistance();
    }

    public void setLocationOfZero() {
      boolean foundZero = false;
      // location of the empty space on the board
      for (int row = 0; row < board.length; row++) {
        for (int col = 0; col < board.length; col++) {
          if (board[row][col] == 0) {
            locationOfZero = new Point(col, row);
            foundZero = true;
            break;
          }
        }
        if (foundZero) {
          break;
        }
      }
    }

    private void setHammingDistance() {
      hammingDistance = 0;

      // find hamming distance from the goal
      int cellValueInSolvedMatrix = 0;

      for (int row = 0; row < board.length; row++) {
        for (int col = 0; col < board.length; col++) {
          cellValueInSolvedMatrix++; // 1st cell is 1, 2nd is 2 ...

          if (board[row][col] != cellValueInSolvedMatrix) {
            // every mismatch increases the hamming distance
            hammingDistance++;
          }
        }
      }

      // in this grid of 9 cells, only 8 are real positions, one is zero/blank location
      // disregard the increase in hamming distance due to zero/blank
      hammingDistance--;
    }

    public int getHammingDistance() {
      return hammingDistance;
    }

    public int getHammingPriority() {
      return hammingDistance + movesToReachThisBoard;
    }

    public void setPreviousStateOfThisBoard(Board previousStateOfThisBoard) {
      this.previousStateOfThisBoard = previousStateOfThisBoard;
    }

    public Board getPreviousStateOfThisBoard() {
      return previousStateOfThisBoard;
    }

    public List<Board> buildBoardsForNextState() {
      // swap zero with its neighbours
      // create as many new boards as possible, we need to try out each, one by one.

      // one of these boards will be same as the previous state of current board
      // we should ignore it else we will run into loops

      List<Board> newBoards = new ArrayList<>();

      // move left neighbour to zero location
      if (locationOfZero.row != 0) {
        Board xMinus1 = cloneForNextStage();

        xMinus1.swap(locationOfZero.row - 1, locationOfZero.col, locationOfZero.row, locationOfZero.col);

        newBoards.add(xMinus1);
      }

      // move top neighbour to zero location
      if (locationOfZero.col != 0) {
        Board yMinus1 = cloneForNextStage();

        yMinus1.swap(locationOfZero.row, locationOfZero.col - 1, locationOfZero.row, locationOfZero.col);

        newBoards.add(yMinus1);
      }

      // move right neighbour to zero location
      if (locationOfZero.row != board.length - 1) {
        Board xPlus1 = cloneForNextStage();

        xPlus1.swap(locationOfZero.row + 1, locationOfZero.col, locationOfZero.row, locationOfZero.col);

        newBoards.add(xPlus1);
      }

      // move bottom neighbour to zero location
      if (locationOfZero.col != board.length - 1) {
        Board yPlus1 = cloneForNextStage();

        yPlus1.swap(locationOfZero.row, locationOfZero.col + 1, locationOfZero.row, locationOfZero.col);

        newBoards.add(yPlus1);
      }

      if (getPreviousStateOfThisBoard() != null) {
        // optimize: no point going back to the previous state
        newBoards.remove(getPreviousStateOfThisBoard());
      }


      return newBoards;
    }

    private Board cloneForNextStage() {
      int[][] clone = new int[board.length][board.length];

      // deep copy elements
      for (int y = 0; y < board.length; y++) {
        for (int x = 0; x < board.length; x++) {
          clone[y][x] = board[y][x];
        }
      }

      Board newBoard = new Board(clone, movesToReachThisBoard + 1);
      newBoard.setPreviousStateOfThisBoard(this);

      return newBoard;
    }

    private void swap(int fromX, int fromY, int toX, int toY) {
      // board[toY][toX] is the current location of 0/space
      board[toY][toX] = board[fromY][fromX];
      board[fromY][fromX] = 0;

      setLocationOfZero();
      setHammingDistance();
    }

    public void print() {
      System.out.println("Hamming Distance/Priority : " + hammingDistance + " / " + getHammingPriority());

      for (int y = 0; y < board.length; y++) {
        for (int x = 0; x < board.length; x++) {
          System.out.print(board[y][x] + " ");
        }
        System.out.println();
      }
    }
  }

  @Test
  public void test() {
    // X and Y axis are swapped
    int[][] intBoard = {
        {0, 1, 3},
        {4, 2, 5},
        {7, 8, 6}
    };
    int initialMovesToReachThisBoard = 0;

    Board board = new Board(intBoard, initialMovesToReachThisBoard);

    boolean solved = solve(board);
    System.out.println("Solved = " + solved);
  }
}
