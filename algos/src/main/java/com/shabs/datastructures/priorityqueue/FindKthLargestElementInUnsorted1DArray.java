package com.shabs.datastructures.priorityqueue;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * PriorityQueue - minHeap of size K
 *
 * Find the Kth Largest element in an unsorted 1D Array.
 */
public class FindKthLargestElementInUnsorted1DArray {

  public int find(int[] input, int k) {

//  PriorityQueue<Integer> minHeap = new PriorityQueue<>(k, (o1,o2) -> Integer.compare(o1, o2));
    PriorityQueue<Integer> minHeap = new PriorityQueue<>(k, Comparator.comparingInt(i -> i));

    for (int i : input) {
      if (minHeap.size() < k) {
        // keep adding till we fill up k elements
        minHeap.add(i);
        continue;
      }

      if (minHeap.peek() < i) {
        // remove the smaller element and put the larger in
        minHeap.remove();
        minHeap.add(i);
      }
    }

    return minHeap.remove();
  }

  @Test
  public void test() {
    int[] input = {3,2,1,5,6,4};
    int k = 2;

    int expected = 5;
    int actual = find(input, k);

    Assert.assertEquals(actual, expected);
  }
}
