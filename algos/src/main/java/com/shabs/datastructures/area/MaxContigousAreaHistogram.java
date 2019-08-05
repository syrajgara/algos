package com.shabs.datastructures.area;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * given a multimodal histogram - find the biggest rectangular area.
 * consider width to be always 1 for the bars.
 * height varies as you move along the row axis.
 * <p>
 * eg bar heights: {3,1,0,1,3,3,4,5,1}
 * max rectangular area would be 3*4=12 <= {3,3,4-1=3,5-2=3}
 * <p>
 * level = height
 * level3 -> 0 0 0 3 0
 * level2 -> 0 2 0 A=2 B=4
 * level1 -> 0 1 2 3 4
 * <p>
 * - keep track of area, at each height/level
 * - keep largest area seen so far
 * - when adding a new bar, increase area at each level, previousAreaAtlevel + level
 * - for ex: A+2=B
 */
public class MaxContigousAreaHistogram {

  private List<Integer> areaWithHeight;
  private int maxArea;
  private int maxHeight;

  public MaxContigousAreaHistogram() {
    areaWithHeight = new ArrayList<>();
    maxArea = 0;
    maxHeight = 0;
  }

  public int getMaxArea(int[] histogram, int index) {
    // method called for bar in histogram at index

    if (index >= histogram.length) {
      return maxArea; // done iterating thru all bars
    }

    if (areaWithHeight.size() == 0) {
      areaWithHeight.add(0, 0);
    }

    int currentHeight = histogram[index];

    if (currentHeight > maxHeight) {
      for (int i = (maxHeight + 1); i <= currentHeight; i++) {
        areaWithHeight.add(i, 0); //seed the area for new heights
      }
      maxHeight = currentHeight;
    }

    for (int i = 0; i <= maxHeight; i++) {
      int newArea = 0;

      if (i <= currentHeight) {
        int currentArea = areaWithHeight.get(i);
        newArea = currentArea + i; // area increased with additional of this bar
      } else {
        // adding this smaller bar, zeros out the area related to higher bars
        newArea = 0;
      }

      if (newArea > maxArea) {
        maxArea = newArea;
      }

      areaWithHeight.set(i, newArea);
    }

    return getMaxArea(histogram, index + 1);
  }

  public int getMaxArea2(int[] histogram) {
    List<Integer> area = new ArrayList<>();

    for (int h : histogram) {
      for (int i = area.size(); i <= h; i++) {
        area.add(i, 0 );
      }

      for (int i = h + 1; i < area.size(); i++) {
        area.set(i, 0);
      }

      for (int i = 0; i <= h; i++) {
        area.set(i, area.get(i) + i);
        maxArea = Math.max(maxArea, area.get(i));
      }
    }

    return maxArea;
  }

  @Test
  public void test() {

    int[] histogram = {3, 1, 1, 3, 3, 4, 5, 1};
    int expected = 12;

    MaxContigousAreaHistogram m = new MaxContigousAreaHistogram();
    int actual = m.getMaxArea(histogram, 0);

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void test2() {

    int[] histogram = {3, 1, 1, 1, 1, 1, 3, 3, 4, 5, 1, 1, 1, 2, 3};
    int expected = 15;

    MaxContigousAreaHistogram m = new MaxContigousAreaHistogram();
    int actual = m.getMaxArea(histogram, 0);

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void test3() {

    int[] histogram = {3, 1, 1, 3, 3, 4, 5, 1};
    int expected = 12;

    MaxContigousAreaHistogram m = new MaxContigousAreaHistogram();
    int actual = m.getMaxArea2(histogram);

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void test4() {

    int[] histogram = {3, 1, 1, 1, 1, 1, 3, 3, 4, 5, 1, 1, 1, 2, 3};
    int expected = 15;

    MaxContigousAreaHistogram m = new MaxContigousAreaHistogram();
    int actual = m.getMaxArea2(histogram);

    Assert.assertEquals(actual, expected);
  }
}
