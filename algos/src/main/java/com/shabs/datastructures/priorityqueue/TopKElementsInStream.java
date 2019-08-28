package com.shabs.datastructures.priorityqueue;

import org.testng.annotations.Test;

import java.util.*;

/**
 * for a streaming data, keep only k most used items
 * <p>
 * map with key and number of time seen
 * on every new key received,
 * - increment value if key exists
 * - add key if enough space in the map
 * - reduce "seen" count for all elements in the map
 * - remove element if "seen" count becomes zero. (or put in priority queue and just remove one of them)
 * - note: cannot remove in the same loop, store and remove separately. due to ConcurrentModificationException
 */
public class TopKElementsInStream {

  private class Node<K,V extends Comparable<V>> implements Comparable<Node<K,V>>{
    K key;
    V value;

    Node(K k, V v) {
      key = k;
      value = v;
    }

    @Override
    public int compareTo(Node<K, V> o) {
      return value.compareTo(o.value);
    }
  }

  private Map<Integer, Integer> addItems(int[] data, int slotSize) {
    Map<Integer, Integer> slots = new HashMap<>();
    PriorityQueue<Node<Integer,Integer>> removeSlots = new PriorityQueue<>();

    for (int datum : data) {
      if (slots.containsKey(datum)) {
        slots.put(datum, slots.get(datum) + 1);
      } else if (slots.size() < slotSize) {
        slots.put(datum, 1);
      } else {
        // new element but we dont have space - evict elements
        // everyone elements size will be reduced by 1 and ones that become zero will be evicted.
        // we do not add the current element on eviction, since its count is 1 as well.
        for (int key : slots.keySet()) {
          slots.put(key, slots.get(key) - 1);

          if (slots.get(key) <= 0) {
            removeSlots.add(new Node<>(key, slots.get(key)));
          }
        }

        // we are giving preference to the newest datum
        // - even though its frequency could be same as older ones
        Node<Integer,Integer> nodeToRemove = removeSlots.remove();
        slots.remove(nodeToRemove.key);
        slots.put(datum, 0);

        removeSlots = new PriorityQueue<>(); // reset
      }
    }

    return slots;
  }

  @Test
  public void addItems() {
    int[] data = {1, 2, 3, 4, 5, 6, 7, 2, 4, 6, 8, 2, 3, 2, 3, 4, 6, 7};
    int slotSize = 5;

    Map<Integer, Integer> slots = addItems(data, slotSize);

    for (Map.Entry<Integer, Integer> e : slots.entrySet()) {
      System.out.println(e.getKey() + " - " + e.getValue());
    }
  }
}
