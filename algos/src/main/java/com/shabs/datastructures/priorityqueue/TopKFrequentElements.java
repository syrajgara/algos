package com.shabs.datastructures.priorityqueue;

import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given an non empty array of n elements having duplicates
 * - find top K frequent elements.
 * - ex: if array has following frequency (2,5,10,100) for corresponding elements (1,2,3,4)
 * - then top 2 elements are (3,4)
 * <p>
 * - figure out the frequency of each element
 * - put them in priority queue with comparator on the frequency
 * - add to the queue if not already full - else remove the least and add the new one in
 * <p>
 * - if not using the queue, order them in an array based on the frequency and then
 * - from the end of that array pick K elements
 * <p>
 * - Note: each frequency could have multiple elements
 */
public class TopKFrequentElements {

  public void find(int[] input, int k) {
    Map<Integer, Integer> frequency = new HashMap<>();
    for (int i : input) {
      frequency.put(i, frequency.getOrDefault(i, 0) + 1);
    }

    // MIN heap, since you want to remove the small elements from the top
    PriorityQueue<Map.Entry<Integer, Integer>> pq
        = new PriorityQueue<>(k, Comparator.comparingInt(o -> o.getValue()));

    int currentK = 0;

    for (Map.Entry<Integer, Integer> e : frequency.entrySet()) {
      if (currentK < k) {
        currentK++;
        pq.add(e);
        continue;
      }

      if (pq.peek().getValue() < e.getValue()) {
        pq.remove();
        pq.add(e);
      }
    }

    for (Map.Entry<Integer, Integer> e : pq) {
      System.out.println(e.getKey() + " => " + e.getValue());
    }
  }

  @Test
  public void test() {
    int k = 3; // 1,2,3
    int[] input = {1, 4, 1, 5, 1, 2, 6, 2, 3, 7};

    find(input, k);
  }

  @Test
  public void test2() {
    int k = 2; // 1,2,3
    int[] input = {5,5,4,4,3,3};

    find(input, k);
  }
}
