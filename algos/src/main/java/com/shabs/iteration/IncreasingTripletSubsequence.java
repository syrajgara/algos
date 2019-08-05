package com.shabs.iteration;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * does a list of number have 3 non-consecutive increasing numbers
 */
public class IncreasingTripletSubsequence {

  public boolean check(int[] input) {
    int small = Integer.MAX_VALUE;
    int medium = Integer.MAX_VALUE;

    for (int i : input) {
      if (i <= small) {
        small = i;
        medium = Integer.MAX_VALUE;
      } else if (i <= medium) {
        medium = i;
      } else {
        System.out.println(small + " -> " + medium + " -> " + i);
        return true;
      }
    }

    System.out.println(small + " -> " + medium + " -> FALSE");
    return false;
  }

  @Test
  public void test1() {
    int[] input = {1,2,3,4,5};

    boolean expected = true;
    boolean actual = check(input);

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void test2() {
    int[] input = {5,4,3,2,1};

    boolean expected = false;
    boolean actual = check(input);

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void test3() {
    int[] input = {1,1,1,1};

    boolean expected = false;
    boolean actual = check(input);

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void test4() {
    int[] input = {5,7,3,9};

    boolean expected = false;
    boolean actual = check(input);

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void test5() {
    int[] input = {5,7,3,9,10};

    boolean expected = true;
    boolean actual = check(input);

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void test6() {
    int[] input = {5,7,6,9};

    boolean expected = true;
    boolean actual = check(input);

    Assert.assertEquals(actual, expected);
  }
}
