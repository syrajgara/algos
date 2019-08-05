package com.shabs.sort;

import org.testng.annotations.Test;

/**
 * Sort 3 colors, red (0), blue(1), green(2) in an array .. in place.
 * <p>
 * - keep leftIndex and rightIndex where you will swap 0 and 2 respectively.
 * - loop thru all elements
 * - do a swap of 0 to left if swap i greater than leftIndex
 * - do a swap of 2 to right if swap i less than rightIndex
 * - no-op for value=1
 */
public class SortColors {

  private void sortColors(int[] input) {
    int leftIndex = 0;
    int rightIndex = input.length - 1;

    for (int i = 0; i < input.length; i++) {
      if (input[i] == 0 && i > leftIndex) {
        swap(input, i, leftIndex);
        leftIndex++;
        i--;
      } else if (input[i] == 2 && i < rightIndex) {
        swap(input, i, rightIndex);
        rightIndex--;
        i--;
      }
    }
  }

  private void swap(int[] input, int i, int leftIndex) {
    int temp = input[i];
    input[i] = input[leftIndex];
    input[leftIndex] = temp;
  }

  private void print(int[] input) {
    for (int i : input) {
      System.out.print(i + ", ");
    }
    System.out.println();
  }

  @Test
  public void test1() {
    int[] input = {0, 2, 1, 2, 0, 2, 0, 2, 1, 1, 0};
    print(input);
    sortColors(input);
    print(input);
    System.out.println("-----------------------------------");
  }

  @Test
  public void test2() {
    int[] input = {1, 2, 0, 2, 0, 2, 1, 1, 0};
    print(input);
    sortColors(input);
    print(input);
    System.out.println("-----------------------------------");
  }

  @Test
  public void test3() {
    int[] input = {2, 1, 2, 0, 2, 0, 2, 1, 1, 0};
    print(input);
    sortColors(input);
    print(input);
    System.out.println("-----------------------------------");
  }

  @Test
  public void test4() {
    int[] input = {2, 2, 1, 1};
    print(input);
    sortColors(input);
    print(input);
    System.out.println("-----------------------------------");
  }

  @Test
  public void test5() {
    int[] input = {1, 1, 0, 0};
    print(input);
    sortColors(input);
    print(input);
    System.out.println("-----------------------------------");
  }

  @Test
  public void test6() {
    int[] input = {2, 2, 0, 0};
    print(input);
    sortColors(input);
    print(input);
    System.out.println("-----------------------------------");
  }

  @Test
  public void test7() {
    int[] input = {1, 1, 1, 1};
    print(input);
    sortColors(input);
    print(input);
    System.out.println("-----------------------------------");
  }
}
