package com.shabs.sort;

import org.testng.annotations.Test;

/**
 * pick the last element of the array as pivot and bring it to the correct location in the array,
 * then quicksort sub-array before and after that pivot index
 * <p>
 * - do partition, which moves the pivotValue to the correct location and return the pivotIndex
 * - using this pivotIndex split array into 2 and sort left and right separately.
 */
public class QuickSort {
  public static void sort(int[] A, int l, int r) {
    if (l > r) {
      return;
    }

    int pivotIndex = partition(A, l, r);

    sort(A, l, pivotIndex - 1);
    sort(A, pivotIndex + 1, r);
  }

  private static int partition(int[] A, int l, int r) {
    int pivotIndex = r;
    int pivotValue = A[pivotIndex];

    int i = l;
    while (i < pivotIndex) {
      if (A[i] <= pivotValue) {
        i++;
      } else {
        A[pivotIndex] = A[i];
        pivotIndex--;
        A[i] = A[pivotIndex];
      }
    }

    A[pivotIndex] = pivotValue;

    return pivotIndex;
  }

  @Test
  public void test() {
    int[] A = {4, 2, 5, 8, 1, 3, 7, 9, 6};
    QuickSort.sort(A, 0, 8);

    System.out.println("SORTED: ");

    for (int i = 0; i < 9; i++) {
      System.out.print(A[i] + ", ");
    }
  }

  @Test
  public void testDuplicates() {
    int[] A = {4, 5, 2, 8, 2};
    QuickSort.sort(A, 0, 4);

    System.out.println("SORTED: ");

    for (int i = 0; i < 5; i++) {
      System.out.print(A[i] + ", ");
    }
  }
}
