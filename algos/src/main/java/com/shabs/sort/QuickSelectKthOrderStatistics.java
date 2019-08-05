package com.shabs.sort;

import org.testng.annotations.Test;

/**
 * Get Kth smallest number from an unsorted array
 * sort only required parts of the array to find the kth smallest.
 * <p>
 * use partition of quicksort, to find pivotIndex
 * if pivotIndex less than K, redo in the first part of array else second part
 */
public class QuickSelectKthOrderStatistics {
  public static int kthStatistic(int[] A, int l, int r, int K) {
    int pivotIndex = partition(A, l, r);
    int pivotValue = 0;

    if (K == pivotIndex) {
      pivotValue = A[pivotIndex];
    } else if (K < pivotIndex) {
      pivotValue = kthStatistic(A, l, pivotIndex - 1, K);
    } else if (K > pivotIndex) {
      // index is the value in the complete array, not just sub-array
      pivotValue = kthStatistic(A, pivotIndex + 1, r, K);
    }

    return pivotValue;
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
        A[i] = A[pivotIndex - 1];
        pivotIndex--;
      }
    }

    A[pivotIndex] = pivotValue;

    return pivotIndex;
  }

  @Test
  public void main() {
    int[] A = {4, 2, 5, 8, 1, 3, 7, 9, 6};
    int kthStatistic;

    kthStatistic = kthStatistic(A, 0, 8, 4);
    System.out.println("kthStatistic 4th= " + kthStatistic);  //1

    kthStatistic = kthStatistic(A, 0, 8, 7);
    System.out.println("kthStatistic 7th= " + kthStatistic);  //9
  }
}
