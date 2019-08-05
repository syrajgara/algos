package com.shabs.dynamic.sum_this_or_that;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Climb stairs in 1 or 2 steps to the top n
 * find number of ways you can climb to reach to top nth step
 *
 * <p>
 * - the last step is going to return 1, steps beyond that is going to return 0
 * - all other steps are going to need calculations
 * - remember to memoize, else wont be able to run more than few steps
 */
public class ClimbingStairs {

  public int countSteps(int currentStep, int finalStep, Map<Integer, Integer> memoization) {
    if (currentStep == finalStep) {
      return 1; // reached the top
    }

    if (currentStep > finalStep) {
      return 0; // overstepped
    }

    if (!memoization.containsKey(currentStep)) {
      memoization.put(currentStep,
          countSteps(currentStep + 1, finalStep, memoization) +
              countSteps(currentStep + 2, finalStep, memoization));
    }

    return memoization.get(currentStep);
  }

  @Test
  public void test() {
    int steps = 10;
    Map<Integer, Integer> memoization = new HashMap<>();

    int expectedWays = 89; // one way - stay put
    int actual = countSteps(0, steps, memoization);
    Assert.assertEquals(actual, expectedWays);
  }

  @Test
  public void test1() {
    int steps = 0;
    Map<Integer, Integer> memoization = new HashMap<>();

    int expectedWays = 1; // one way - stay put
    int actual = countSteps(0, steps, memoization);
    Assert.assertEquals(actual, expectedWays);
  }

  @Test
  public void test2() {
    int steps = 1;
    Map<Integer, Integer> memoization = new HashMap<>();

    int expectedWays = 1;
    int actual = countSteps(0, steps, memoization);
    Assert.assertEquals(actual, expectedWays);
  }

  @Test
  public void test3() {
    int steps = 2;
    Map<Integer, Integer> memoization = new HashMap<>();

    int expectedWays = 2;
    int actual = countSteps(0, steps, memoization);
    Assert.assertEquals(actual, expectedWays);
  }

  @Test
  public void test4() {
    int steps = 4;
    Map<Integer, Integer> memoization = new HashMap<>();

    int expectedWays = 5;
    int actual = countSteps(0, steps, memoization);
    Assert.assertEquals(actual, expectedWays);
  }

  @Test
  public void test5() {
    int steps = 100;
    Map<Integer, Integer> memoization = new HashMap<>();

    int expectedWays = -1869596475; //overflow -- will not be able to get this without memoization
    int actual = countSteps(0, steps, memoization);
    Assert.assertEquals(actual, expectedWays);
  }
}
