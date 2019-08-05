package com.shabs.iteration;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * level 0 -> 1
 * level 1 -> 1 1
 * level 2 -> 1 2 1
 * level 3 -> 1 3 3 1
 * level 4 -> 1 4 6 4 1
 * level 5 -> 1 5 10 10 5 1
 * level 6 -> 1 6 15 20 15 6 1
 * <p>
 * - first level is 1
 * - first element of each level is 1
 * - get previous level to calculate current level
 * - current value = sum of previous level's previous and current col
 * - for last element (when row==col), value is just the previous level previous col
 */
public class PascalTriangle {
  private List<List<Integer>> build(int level) {
    List<List<Integer>> triangle = new ArrayList<>();

    // add first level, to get the base case
    List<Integer> currentLevel = new ArrayList<>();
    triangle.add(currentLevel);

    // first level, the only 1
    currentLevel.add(1);

    for (int row = 1; row < level; row++) {
      List<Integer> previousLevel = triangle.get(row - 1);

      currentLevel = new ArrayList<>();
      triangle.add(row, currentLevel);

      // first number on each level is 1
      currentLevel.add(1);

      for (int col = 1; col <= row; col++) {
        int sum = previousLevel.get(col - 1);
        if (col != row) {
          // for row == col, it is just the sum of previous level's previous col == 1
          sum += previousLevel.get(col);
        }
        currentLevel.add(sum);
      }
    }

    return triangle;
  }

  public void print(List<List<Integer>> triangle) {
    for (List<Integer> level : triangle) {
      for (Integer i : level) {
        System.out.print(i + " ");
      }
      System.out.println();
    }
  }

  @Test
  public void build() {
    int level = 7;
    List<List<Integer>> triangle = build(level);
    print(triangle);
  }
}
