package com.shabs.datastructures.array;

import org.testng.annotations.Test;

/**
 * 2Pointers
 * <p>
 * given a 1d array of numbers, return an output array
 * where output[i] = product of all elements except input[i]
 * *without* using division operation.
 * <p>
 * input  = {1,  2,  3, 4}
 * output = {24, 12, 8, 6}
 * <p>
 * N solution
 * create 2 arrays and then do product of the elements
 * outputLeft[i] = product of all elements left of i
 * outputRight[i] = product of all elements right of i
 */
public class ProductExceptSelf {

  public int[] product(int[] input) {

    int len = input.length;

    int[] outputLeft = new int[len];
    int[] outputRight = new int[len];
    int[] output = new int[len];

    outputLeft[0] = 1;
    int lastIndexOnRight = len - 1;
    outputRight[lastIndexOnRight] = 1;

    for (int i = 0 + 1; i < len; i++) {
      outputLeft[i] = outputLeft[i - 1] * input[i - 1];

      // this will work - but to reduce complexity, just add another loop
      //outputRight[lastIndexOnRight-i] = outputRight[lastIndexOnRight-i+1] * input[lastIndexOnRight-i+1];
    }

    // another loop
    for (int j = lastIndexOnRight - 1; j >= 0; j--) {
      outputRight[j] = outputRight[j + 1] * input[j + 1];
    }

    for (int k = 0; k < len; k++) {
      output[k] = outputLeft[k] * outputRight[k];
    }

    print(outputLeft);
    print(outputRight);

    return output;
  }

  public int[] productN2(int[] input) {

    int[] output = new int[input.length];

    for (int i = 0; i < input.length; i++) {
      output[i] = 1;
      for (int j = 0; j < input.length; j++) {
        if (i != j) {
          output[i] *= input[j];
        }
      }
    }

    return output;
  }

  public void print(int[] arr) {
    for (int element : arr) {
      System.out.print(element + ", ");
    }
    System.out.println();
  }

  @Test
  public void test() {
    int[] input = {1, 2, 3, 4};
    print(input);
    int[] output = product(input);
    print(output);
  }

  @Test
  public void testN2Solution() {
    int[] input = {1, 2, 3, 4};
    print(input);
    int[] output = productN2(input);
    print(output);
  }
}
