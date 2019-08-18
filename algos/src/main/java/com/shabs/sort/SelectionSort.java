package com.shabs.sort;

public class SelectionSort {
  public static int[] input = {4, 1, 2, 8, 3, 7, 5, 9};

  public static int[] selectionSort(int[] input) {
    //goes form left to right
    //finds minimum element and swaps it with the left edge element
    for (int i = 0; i < input.length - 1; i++) {
      int minIndex = i;
      for (int j = i + 1; j < input.length; j++) {
        if (input[j] < input[minIndex]) {
          minIndex = j;
        }
      }
      if (minIndex != i) {
        int temp = input[i];
        input[i] = input[minIndex];
        input[minIndex] = temp;
      }
    }

    return input;
  }


  public static void print(int[] input) {
    for (int i = 0; i < input.length; i++) {
      System.out.println(input[i]);
    }
  }

  public static void main(String[] args) {

    selectionSort(input);
    System.out.println("After Sort");
    print(input);
  }
}
