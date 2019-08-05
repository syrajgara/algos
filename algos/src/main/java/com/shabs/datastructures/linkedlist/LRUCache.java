package com.shabs.datastructures.linkedlist;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Build a LRU cache with capacity C
 *
 * Keep a HashMap with the (key, node) - for O(1) access
 * Nodes are doubly linked - so easy to move then and stitch their previous/next nodes
 * keep currentCapacity and tailNode, to remove tail nodes when over capacity
 *
 * Link Nodes, so that the head is the most recently used element,
 * tail is least recently used
 * When adding, add to front and add to Hashmap
 * if above capacity remove from tail end.
 */
public class LRUCache {

  private class Node {
    public int key;
    public String value;

    public Node previous;
    public Node next;

    public Node(Integer k, String v) {
      key = k;
      value = v;
    }
  }

  private Node head;
  private Node tail;
  private Map<Integer, Node> cache;

  private int capacity;
  private int numberOfElementsInCache;

  public LRUCache() {
    //for @Test
  }

  public LRUCache(int capacity) {
    head = new Node(0,null);
    tail = new Node(0,null);

    // head/tail sentinel nodes
    head.next = tail;
    tail.previous = head;

    cache = new HashMap<>();

    this.capacity = capacity;
  }

  public void add(int key, String value) {
    if (cache.containsKey(key)) {
      //update value
      Node n = cache.get(key);
      n.value = value;

      //move to front, so that it is the last to be removed from cache
      addToFront(n);
    } else {
      if (numberOfElementsInCache == capacity) {
        // need to remove one element, the one next to tail is the least used
        Node n = tail.previous;
        tail.previous = tail.previous.previous;
        tail.previous.next = tail;
        cache.remove(n.key);
        numberOfElementsInCache--;
      }

      Node newNode = new Node(key, value);
      cache.put(key, newNode);
      addToFront(newNode);
      numberOfElementsInCache++;
    }
  }

  private void addToFront(Node n) {
    n.next = head.next;
    n.previous = head;

    n.next.previous = n;
    n.previous.next = n;
  }

  public void remove(int key) {
    Node n = cache.remove(key);
    n.previous.next = n.next;
    n.next.previous = n.previous;

    numberOfElementsInCache--;
  }

  public String get(int key) {
    return cache.containsKey(key) ? cache.get(key).value : "NULL";
  }

  @Test
  public void test() {
    LRUCache lru = new LRUCache(3);

    lru.add(1, "A");
    lru.add(2, "B");
    lru.add(3, "C");
    String s3 = lru.get(3);
    Assert.assertEquals(s3, "C");
    lru.add(4, "D");
    String s1 = lru.get(1);
    Assert.assertEquals(s1, "NULL");
  }
}
