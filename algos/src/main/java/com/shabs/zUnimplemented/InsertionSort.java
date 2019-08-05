package com.shabs.zUnimplemented;

public class InsertionSort {

  public static int[] input = {4, 1, 2, 8, 3, 7, 5, 9};

  // Start again from second element and compare it with rest of the elements on left
  //find its right place and shift all the elements on left accordingly
  //in each iteration

  public static int[] insertionSort(int[] input) {
    for (int i = 1; i < input.length - 1; i++) {
      int pivot = input[i];  // save the pivot element.. since while shifting it will get lost
      int j = i - 1;   // set the J to be one less than i
      while (j >= 0 && pivot < input[j]) {  // till J is greater than or equals to zero and pivot is less than the element in comparsion
        input[j + 1] = input[j];  // do a right shift of element
        j--;
      }
      if (j != i - 1) {   // if J has changed a bit means right shift has happened .. set the pivot to its correct place
        input[j + 1] = pivot;
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

    insertionSort(input);
    System.out.println("After Sort");
    print(input);
  }

}
