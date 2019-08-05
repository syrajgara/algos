package com.shabs.datastructures.area;

import org.testng.annotations.Test;

/**
 * 2 Pointer
 * Given and array with height of lines, find the 2 lines that will contain the most water.
 * - lines themselves do not occupy space,
 * - also ignore all other lines in between when calculation area. ie. they wont be part of the container.
 * <p>
 * Have 2 pointers at the end
 * - calc water area
 * - move the smaller height pointer on each iteration, till they cross
 */
public class WaterContainer {

  private int calcArea(int[] heights) {
    int area = 0;

    int lowIndex = 0;
    int highIndex = heights.length - 1;

    while (lowIndex < highIndex) {
      int newArea = (highIndex - lowIndex + 1) * Math.min(heights[lowIndex], heights[highIndex]);
      area = Math.max(area, newArea);

      if (heights[lowIndex] < heights[highIndex]) {
        lowIndex++;
      } else {
        highIndex--;
      }
    }

    return area;
  }

  @Test
  public void test() {
    int[] heights = {2, 5, 1, 10, 4};

    int expected = 16;
    int actual = calcArea(heights);
  }
}
