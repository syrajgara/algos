package com.shabs.sort;

import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * Given an array - sort it such that nums[0] < nums[1] > nums[2] < nums[3]...
 *
 * - sort the array
 * - break it into 2 halfs arrays
 * - merge them one element from each array - starting from last element of both arrays
 *
 * [1,1,1,2  ,2,3,3] => from first [1,2,1,3,1,3,2] OR from last [2,3,1,3,1,2,1]
 * [1,1,1, 1,2,2] => from first [1,1,1,2,1,2]  OR from last [2,1,2,1,1,1]
 */
public class WiggleSort {

  public int[] sort(int[] a) {
    Arrays.sort(a);

    int[] b = new int[a.length];

    int m = (a.length + 1) / 2 - 1;
    for (int i = 0; i <= m; i++) {
      b[i * 2] = a[i];
      if (m + 1 + i < a.length) {
        b[i * 2 + 1] = a[m + 1 + i];
      }
    }

    return b;
  }

  @Test
  public void test() {
    int[] a = {1,1,1,2,2,3,3};
    int[] b = sort(a);

    for (int i : b) {
      System.out.print(i + ", ");
    }
  }
}
