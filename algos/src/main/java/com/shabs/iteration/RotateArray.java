package com.shabs.iteration;

import org.testng.annotations.Test;

/**
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 *
 * move 1 t0 k+1 index, k+1 to 2k+1 ...
 * then 2 to k+2 index, k+2 to 2k+2 ...
 * in each case when you rotate and come to be beginning of the array that cycle is over
 */
public class RotateArray {
  public void rotate(int[] input, int k) {

    while (k > input.length) {
      k -= input.length;
    }

    for (int i = 0; i < k; i++) {
      // move these too new location
      int rotationNumber = 1;
      int numberToMove = input[i];

      while (rotationNumber * k < input.length) {
        int replacedNumber = input[i + rotationNumber * k];
        input[i + rotationNumber * k] = numberToMove;
        numberToMove = replacedNumber;
        rotationNumber++;
      }

      input[i + rotationNumber * k - input.length] = numberToMove;

      for (int j : input) {
        System.out.print(j + ", ");
      }
      System.out.println();
    }
  }

  @Test
  public void test() {
    int[] input  = {1,2,3,4,5,6};
    int[] output = {5,6,1,2,3,4};
    int k = 2;

    rotate(input, k);
  }

  @Test
  public void test1() {
    int[] input  = {1,2};
    int[] output = {2,1};
    int k = 1;

    rotate(input, k);
  }

  @Test
  public void test2() {
    int[] input  = {1,2};
    int[] output = {2,1};
    int k = 2;

    rotate(input, k);
  }

  @Test
  public void test3() {
    int[] input  = {1,2};
    int[] output = {2,1};
    int k = 3;

    rotate(input, k);
  }

  @Test
  public void test4() {
    int[] input  = {1,2};
    int[] output = {2,1};
    int k = 5;

    rotate(input, k);
  }
}
