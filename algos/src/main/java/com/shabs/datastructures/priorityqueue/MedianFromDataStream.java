package com.shabs.datastructures.priorityqueue;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 2 PRIORITY QUEUE
 *
 * Find Median of a Data Stream
 * Median is the middle value in an ordered integer list.
 * If the size of the list is even, there is no middle value.
 * So the median is the mean of the two middle value.
 *
 * - ADD NUM method to build the PQ
 * - add to lowerPQ if empty
 * - add to lower or upper PQ based on the current value and peek of the lower and upper queue
 * - after adding to lower or upper - re adjust if lower size is more than upper+1 or less than upper
 *
 * - MEDIAN method to peek at the PQ and get the current median
 */
public class MedianFromDataStream {

  private PriorityQueue<Integer> lowerPQ = new PriorityQueue<>(Comparator.reverseOrder());
  private PriorityQueue<Integer> upperPQ = new PriorityQueue<>();
  boolean numOfElementsEven = true;

  public void addNum(int i) {
    numOfElementsEven = !numOfElementsEven;

    if (lowerPQ.isEmpty() || i <= lowerPQ.peek()) {
      lowerPQ.add(i);
      if (lowerPQ.size() > upperPQ.size() + 1) {
        upperPQ.add(lowerPQ.remove());
      }
    } else {
      upperPQ.add(i);
      if (upperPQ.size() > lowerPQ.size()) {
        lowerPQ.add(upperPQ.remove());
      }
    }
  }

  public double findMedian() {
    if (numOfElementsEven) {
      return (lowerPQ.peek() + upperPQ.peek() ) / 2.0;
    } else {
      return lowerPQ.peek();
    }
  }

  @Test
  public void test() {
    addNum(1);
    addNum(2);
    double i1 = findMedian();
    Assert.assertEquals(i1, 1.5);
    addNum(3);
    double i2 = findMedian();
    Assert.assertEquals(i2, 2.0);
  }
}
