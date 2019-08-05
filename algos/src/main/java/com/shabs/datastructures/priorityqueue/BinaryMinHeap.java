package com.shabs.datastructures.priorityqueue;

/**
 * MIN HEAP implementation
 *
 * This class models a binary heap
 * It offers operations like insert, peek and removeMin
 * <p>
 * This stores the heap nodes in an array
 * - root is stored at "1" index
 * - all the children of a particular root at index i
 * are stored at 2*i and 2*i+1 left and right child respectively
 * <p>
 * 1
 * 2,3
 * 4,5 - 6,7
 * 8,9 - 10,11 - 12,13 - 14,15
 * <p>
 * parent of a, child at index i can be found at floor(i/2)
 *
 * INSERT
 * - tail starts at index 0
 * - add the element at the (tail + 1), compare it with its parent (parent index = i/2)
 * - if more than parent, you are done
 * - else swap with parent and continue checking this new index with its parent - till you reach root (index = 1)
 *
 * REMOVE
 * - need to give out the root value and then need to promote child to parent
 * - whichever child is smaller, promote that to parent
 * (Note: left or right child could be smaller - not necessary that left < right)
 * - continue swaps for this new child location
 */
public class BinaryMinHeap<T extends Comparable<T>> {

  public Object[] store = new Object[15];

  /**
   * this variable keeps the location of the last element on the heap in level order
   */
  public int tail = 0;

  /**
   * it returns the minimum node of the heap
   */
  public T peek() {
    return (T) store[1];
  }

  /**
   * checks whether the heap is empty or not
   */
  public boolean isEmpty() {
    return tail == 0;
  }

  /**
   * this method inserts the incoming node to the last node of the heap first
   * then it bubbles up the last node till the heap invariant is met
   */
  public void insert(T data) {
    // save the data to the last available location
    store[++tail] = data;

    // bubble up with heap, if child is smaller than parent
    int index = tail;

    while (index > 1 && ((T) store[index]).compareTo((T) store[index / 2]) < 0) {
      //swap the entries of child with parent - bubble child upwards
      Object temp = store[index / 2];
      store[index / 2] = store[index];
      store[index] = temp;

      // continue till, you reach top or parent is smaller than child
      index = index / 2;
    }
  }

  /**
   * Removes the minimum entry from the heap
   * - replaces the root with tail element
   * and then bubbles down the new root to its appropriate position by
   * swapping it with the minimum of its left or right child till the heap invariant is maintained
   */
  public T remove() {
    T response = (T) store[1];

    //insert the last entry to the root if heap does not only contains root
    if (tail != 1) {
      store[1] = store[tail];
    }

    //nullify the tail node since its copied to the root
    store[tail] = null;
    //reduce the tail
    tail--;

    int index = 1;
    // bubble down the root to satisfy heap order
    // check if root is bigger than left or right child
    while ((tail >= index * 2     && (((T) store[index]).compareTo((T) store[index * 2]) > 0))
        || (tail >= index * 2 + 1 && (((T) store[index]).compareTo((T) store[index * 2 + 1]) > 0))) {

      int minChildIndex = -1;
      T minChildValue = null;

      //if parent is bigger than left child
      if (((T) store[index]).compareTo((T) store[index * 2]) > 0) {
        //assign minChildIndex and minChildValue of the left child
        minChildIndex = index * 2;
        minChildValue = (T) store[minChildIndex];
      }

      // if right child is present,
      // parent is bigger than the right child
      // and the left child is also bigger then right child
      // assign the right child to be the minimum one
      if ( tail >= index * 2 + 1
          && ((T) store[index]).compareTo((T) store[index * 2 + 1]) > 0
          && ((T) store[index * 2]).compareTo((T) store[index * 2 + 1]) > 0
          ) {
        //assign minChildIndex and minChildValue of the right child
        minChildIndex = index * 2 + 1;
        minChildValue = (T) store[minChildIndex];
      }

      //swap the parent with minimum child
      store[minChildIndex] = store[index];
      store[index] = minChildValue;
      index = minChildIndex;
    }

    return response;
  }
}