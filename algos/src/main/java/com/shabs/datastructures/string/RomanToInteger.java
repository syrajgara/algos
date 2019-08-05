package com.shabs.datastructures.string;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Convert Roman number to Integer.
 * <p>
 * http://literacy.kent.edu/Minigrants/Cinci/romanchart.htm
 */
public class RomanToInteger {

  public int romanToInt(String s) {
    int nums[] = new int[s.length()];

    for (int i = 0; i < s.length(); i++) {
      switch (s.charAt(i)) {
        case 'M':
          nums[i] = 1000;
          break;
        case 'D':
          nums[i] = 500;
          break;
        case 'C':
          nums[i] = 100;
          break;
        case 'L':
          nums[i] = 50;
          break;
        case 'X':
          nums[i] = 10;
          break;
        case 'V':
          nums[i] = 5;
          break;
        case 'I':
          nums[i] = 1;
          break;
      }
    }

    int sum = 0;

    for (int i = 0; i < nums.length - 1; i++) {
      if (nums[i] < nums[i + 1])
        sum -= nums[i];
      else
        sum += nums[i];
    }

    return sum + nums[nums.length - 1];
  }

  @Test
  public void test() {
    String input = "XXXII";
    int expected = 32;
    int actual = romanToInt(input);

    Assert.assertEquals(actual, expected);
  }
}
