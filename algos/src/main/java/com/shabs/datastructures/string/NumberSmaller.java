package com.shabs.datastructures.string;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Similar to NumberLarger
 *
 * input =  75*4*1235
 * output = 75*3*421
 *
 * from the right find the pivot where number increases (4 in above example)
 * that pivot number has to be swapped with the next smaller number, on its right (3)
 * and then take substring till the pivot index and reverse substring of right part of the string
 */
public class NumberSmaller {

  public String smallerNumber(String n) {
    char[] c = n.toCharArray();
    for (int i = c.length - 1; i > 0; i--) {
      if (c[i] >= c[i-1]) {
        continue;
      }

      int cmax = i;
      for (int j = i+1; j < c.length; j++) {
        if (c[j] < c[i-1]) {
          cmax = j;
        }
      }
      swap(c, i-1, cmax);
      int l = i, r=c.length-1;

      while (l < r) {
        swap(c, l, r);
        l++;
        r--;
      }
      break;
    }

    return String.valueOf(c);
  }

  private void swap(char[] c, int i, int j) {
    char ctemp = c[i];
    c[i] = c[j];
    c[j] = ctemp;
  }

  @Test
  public void test() {
    String input = "75346";
    String expected = "74653";

    String actual = smallerNumber(input);
    Assert.assertEquals(actual, expected);
  }

  @Test
  public void test2() {
    String input = "754";
    String expected = "745";

    String actual = smallerNumber(input);
    Assert.assertEquals(actual, expected);
  }

  @Test
  public void test3() {
    String input = "123";
    String expected = "123";

    String actual = smallerNumber(input);
    Assert.assertEquals(actual, expected);
  }

  @Test
  public void test4() {
    String input = "753463";
    String expected = "753436";

    String actual = smallerNumber(input);
    Assert.assertEquals(actual, expected);
  }

  @Test
  public void test5() {
    String input = "7534634";
    String expected = "7534463";

    String actual = smallerNumber(input);
    Assert.assertEquals(actual, expected);
  }
}
