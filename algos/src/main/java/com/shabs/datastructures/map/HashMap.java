package com.shabs.datastructures.map;

import org.testng.annotations.Test;

/**
 * - Use fixed size array - Entry[]
 * - index of array = (key.hashCode() & 0x7fffffff) % max_size
 *
 * - PUT - if capacity is above load factor
 * - rehash all the keys, before adding the new one
 *
 * - GET - get based on the index, and then loop Entry->next Linked List
 * till you get actual match with the key.
 */
public class HashMap<K, V> {

  private class Entry<K, V> {
    public int hashcode;
    public K key;
    public V value;
    Entry<K, V> next;

    private Entry(int hashcode, K key, V value, Entry<K, V> next) {
      this.hashcode = hashcode;
      this.key = key;
      this.value = value;
      this.next = next;
    }
  }

  private static int DEFAULT_SIZE = 16;
  private static double DEFAULT_LOAD_FACTOR = 0.75;

  private double loadFactor;
  private int size = 0;
  private int max_size = 0;

  private Entry[] store;

  public HashMap() {
    max_size = DEFAULT_SIZE;
    loadFactor = DEFAULT_LOAD_FACTOR;

    store = new Entry[max_size];
  }

  public HashMap(int initialSize) {
    max_size = initialSize;
    loadFactor = DEFAULT_LOAD_FACTOR;

    store = new Entry[max_size];
  }

  public HashMap(int initialSize, double loadFactor) {
    max_size = initialSize;
    this.loadFactor = loadFactor;

    store = new Entry[initialSize];
  }

  public V get(K key) {
    if (size > 0) {
      //trick to avoid negative hash code and hash value more than Integer.MAX_VALUE
      // 0x7fffffff means 0111 1111 1111 1111 1111 1111 1111 1111 .. doing a logical and with this value will unset the sign bit
      // even negative thing will become positive
      int index = (key.hashCode() & 0x7fffffff) % max_size;

      Entry<K, V> target = store[index];

      while (target != null) {
        if (target.key.equals(key) && target.hashcode == key.hashCode()) {
          return target.value;
        }

        target = target.next;
      }
    }

    return null;
  }

  public boolean contains(K key) {
    if (size > 0) {
      //trick to avoid negative hash code and hash value more than Integer.MAX_VALUE
      // 0x7fffffff means 0111 1111 1111 1111 1111 1111 1111 1111 .. doing a logical and with this value will unset the sign bit
      // even negative thing will become positive
      int index = (key.hashCode() & 0x7fffffff) % max_size;

      Entry target = store[index];

      while (target != null) {
        if (target.key.equals(key)) {
          return true;
        }
        target = target.next;
      }

    }

    return false;
  }

  private void rehashIfNecessary() {
    //if hashmap size is more than a certain load - it is time to rehash the array
    if (size >= max_size * loadFactor) {
      //temporarily copy the old array
      Entry[] temp = new Entry[max_size];
      System.arraycopy(store, 0, temp, 0, store.length);

      //double the max_size
      max_size *= 2;
      // create a clean copy of expanded old array
      store = new Entry[max_size];
      //reset the size of old array
      size = 0;

      //iterate the temporary copy and put all the values back in expanded array
      for (int i = 0; i < temp.length; i++) {

        Entry<K, V> node = temp[i];
        //there could be a linked list inside
        while (node != null) {
          //recursively call put
          put(node.key, node.value);
          node = node.next;
        }
      }
    }
  }

  public void put(K key, V value) {

    rehashIfNecessary();

    //trick to avoid negative hash code and hash value more than Integer.MAX_VALUE
    int hashcode = key.hashCode();
    int index = (hashcode & 0x7fffffff) % max_size;

    if (store[index] == null) {
      Entry<K, V> newEntry = new Entry(hashcode, key, value, null);
      store[index] = newEntry;
      size++;
    } else {
      Entry<K, V> target = store[index];

      // update the value scenario - incase adding same key again - with different value
      while (target != null) {
        if (target.key.equals(key) && target.hashcode == hashcode) {
          target.value = value;
          return;
        }

        target = target.next;
      }

      // create new entry - at the beginning of the linkedlist
      Entry<K, V> newEntry = new Entry(hashcode, key, value, store[index]);
      store[index] = newEntry;
      size++;
    }
  }

  @Test
  public void test() {
    HashMap<String, Integer> hashMap = new HashMap<>(10);

    hashMap.put("1", 1);
    hashMap.put("2", 2);
    hashMap.put("12", 12);
    hashMap.put("22", 22);
    hashMap.put("32", 32);
    hashMap.put("42", 42);
    hashMap.put("52", 52);
    hashMap.put("62", 62);
    hashMap.put("72", 72);
    hashMap.put("10", 10);

    System.out.println(hashMap.get("10"));
  }
}
