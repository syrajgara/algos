package com.shabs.sort;

import com.shabs.datastructures.priorityqueue.BinaryMinHeap;
import org.testng.annotations.Test;

/**
 * heapify (using binary heap / min priority queue) all the elements of the input.
 * take elements out of heap - it will be in the increasing order.
 */
public class HeapSort<T extends Comparable<T>> {

  public void heapSort(T[] objects) {
    BinaryMinHeap<T> heap = new BinaryMinHeap<>();
    for (T object : objects) {
      // heapify every element
      heap.insert(object);
    }

    // start arranging the minimum elements from heap
    for (int i = 0; i < objects.length; i++) {
      objects[i] = heap.remove();
    }
  }

  @Test
  public void test() {
    HeapSort<Integer> heapSort = new HeapSort<>();
    Integer[] input = {7, 5, 11, 9, 2, 15, 1, 3};

    heapSort.heapSort(input);

    for (int i = 0; i < input.length; i++) {
      System.out.println(input[i]);
    }
  }
}
