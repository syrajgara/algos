package com.shabs.sort;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given an array of ints - combine them so that it forms the largest possible number
 *
 * - convert ints to strings
 * - write comparator which will compare the 2 strings
 * - as combinations s1+s2 and s2+s1
 * - use this comparator to sort the strings array
 * - concat back the sorted array into the largest number
 */
public class LargestNumber {

  public int sort(int[] input) {

    String[] strings = new String[input.length];
    for (int i = 0; i < input.length; i++) {
      strings[i] = String.valueOf(input[i]);
    }

    Comparator<String> comparator = (o1, o2) -> {
      // cant compare o1 to o2 directly - form the new string before comparing
      String s1 = o1+o2;
      String s2 = o2+o1;

      return s2.compareTo(s1);
    };

    Arrays.sort(strings, comparator);

    StringBuffer sb = new StringBuffer();
    for (String s : strings) {
      sb.append(s);
    }

    return Integer.valueOf(sb.toString());
  }

  @Test
  public void test() {
    int[] input = {3, 30, 34, 5, 9};
    int expected = 9534330;

    int actual = sort(input);
    Assert.assertEquals(actual, expected);
  }

  @Test
  public void test2() {
    String[] ss = {"3", "30", "34", "5", "9"};
    Arrays.sort(ss);

    // NOTE: simple sort wont work - you have to concat 2 strings and then sort
    String expected = "9534**330**";
    String actual   = "9534**303**";

    for (int i = ss.length-1; i >=0; i--) {
      System.out.print(ss[i]);
    }
  }
}
