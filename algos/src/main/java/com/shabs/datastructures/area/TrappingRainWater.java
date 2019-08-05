package com.shabs.datastructures.area;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * given a 1d array, with height of walls, find how much water this structure will retain in case of rain on it
 * <p>
 * [0,1,0,2,1,0,1,3,2,1,2,1] should result in 6 units of rain storage
 * <p>
 * in graphical, - means rain, number show height of wall
 * [ , , , , , , ,3, , , , ]
 * [ , , ,2,-,-,-,3,2,-,2, ]
 * [ ,1,-,2,1,-,1,3,2,1,2,1]
 * <p>
 * start from the 2 ends (left and right), move the smaller of these two walls in,
 * on every iteration smaller could switch sides.
 * once you have the smaller side, compare with next wall on that side
 * if higher, move to the new wall
 * if lower, add diff between the higher and current wall to the storage count.
 * once left right crosses - you will have your storage value.
 */
public class TrappingRainWater {

  public static int findRainStorage(int[] wallHeight) {

    int leftWallIndex = 0;
    int rightWallIndex = wallHeight.length - 1;

    int leftmostTallestWallHeight = wallHeight[leftWallIndex];
    int rightmostTallestWallHeight = wallHeight[rightWallIndex];

    int rainCollected = 0;

    while (leftWallIndex < rightWallIndex) {
      if (leftmostTallestWallHeight < rightmostTallestWallHeight) {
        leftWallIndex++;
        if (wallHeight[leftWallIndex] > leftmostTallestWallHeight) {
          leftmostTallestWallHeight = wallHeight[leftWallIndex];
        } else {
          rainCollected += leftmostTallestWallHeight - wallHeight[leftWallIndex];
        }
      } else {
        rightWallIndex--;
        if (wallHeight[rightWallIndex] > rightmostTallestWallHeight) {
          rightmostTallestWallHeight = wallHeight[rightWallIndex];
        } else {
          rainCollected += rightmostTallestWallHeight - wallHeight[rightWallIndex];
        }
      }
    }

    return rainCollected;
  }

  @Test
  public void test() {
    int[] wallHeight = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};

    int expectedRainStorage = 6;
    int actual = findRainStorage(wallHeight);
    Assert.assertEquals(actual, expectedRainStorage);
  }
}
