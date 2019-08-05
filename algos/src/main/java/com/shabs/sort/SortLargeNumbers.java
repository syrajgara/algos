package com.shabs.sort;

import org.testng.annotations.Test;

import java.util.Arrays;

public class SortLargeNumbers {

  public void sort(String[] unsorted) {

    Arrays.sort(unsorted, (o1, o2) -> {
      if (o1.length() < o2.length()) return -1;
      if (o1.length() > o2.length()) return 1;
      for (int i = 0; i < o1.length(); i++) {
        if (o1.charAt(i) < o2.charAt(i)) return -1;
        if (o1.charAt(i) > o2.charAt(i)) return 1;
      }
      return 0;
    });

    for (String s : unsorted) {
      System.out.println(s);
    }
  }

  @Test
  public void test() {
    String[] input = {"7000", "899", "900", "31415926535897932384626433832795", "1", "3", "10", "3", "5"};

    sort(input);
  }
}
