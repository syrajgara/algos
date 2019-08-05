package com.shabs.datastructures.queue;

import org.testng.annotations.Test;

/**
 * 2 SIDED QUEUE
 *
 * given a 1D array and sliding window size K, print the largest number in all these windows.
 *
 * - 2 sided queue with each Node holding the index of the largest value in a window
 * - dequeue any indexes that are before the start of the window (i-k)
 * - pop (from tail) any index whose corresponding value < current indexes value
 * since the current value will replace the previous smaller value
 * - enqueue current index, will be kept/removed when window moves past this point
 *
 * - index in the queue always increase from left to right - though their might be gaps
 * - corresponding value always decreases from left to right
 * the left edge (head) of the queue will have the index of largest value in the current window
 *
 * index => [0,1, 2, 3,4,5,6,7]
 * value => {1,3,-1,-3,5,3,6,7}
 * queue values as window moves beyond k = 3
 * q1 (index) window end at index 2 => (1,2)
 * q2 (index) window end at index 3 => (1,2,3)
 * q3 (index) window end at index 4 => (4)
 * q4 (index) window end at index 5 => (4,5)
 * q5 (index) window end at index 6 => (6)
 * q6 (index) window end at index 7 => (7)
 */
public class SlidingWindowMaximum {

  public void largestInWindow(int[] input, int k) {
    //indexOfLargestInPreviousWindow
    Queue<Integer> index = new Queue<>();

    for (int i = 0; i < input.length; i++) {

      while ( !index.isEmpty() && index.peek().getData() <= i-k) {
        // this index is not in the current window - remove it
        index.dequeue();
      }

      while ( !index.isEmpty() && input[index.peekTail().getData()] <= input[i]) {
        // this index is part of current window, but is less than current value which will replace it - remove it
        index.pop();
      }

      index.enqueue(i);

      if (i >= k-1) {
        //ignore previous incomplete windows
        System.out.println("[" + index.peek().getData() + "] = " + input[index.peek().getData()]);
      }
    }
  }

  @Test
  public void test() {
    int[] input = {1,3,-1,-3,5,3,6,7};
    int k = 3;

    largestInWindow(input, k);
  }
}
