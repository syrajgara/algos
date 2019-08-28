package com.shabs.datastructures.priorityqueue;

import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * returns a value greater than or equal to the previous value returned by next()
 */
public interface SortedIterator<E extends Comparable<E>> extends Iterator<E> {
  E next();
  boolean hasNext();
}

class IteratorClient {
  /**
   * IMPLEMENT this function
   */
  static <E extends Comparable<E>> SortedIterator<E> merge(List<SortedIterator<E>> iterators) {
    // IMPLEMENT this function
    SortedIteratorMultiple<E> merged = new SortedIteratorMultiple<>(iterators);
    return merged;
  }
}
/**
 * Takes multiple SortedIterator as input and returns a single SortedIterator
 *
 * List of iterators will have a, b,...
 * a: {1, 2, 5} - num elem m
 * b: {3, 4}    - num elem n
 *
 * m = merge([a, b]): {1, 2, 3, 4, 5}
 *
 * List of SortedIterators - size of list n
 *
 * take first elements of each iterator in the list and put in PQElement and in PQ
 * when you remove one element from the PQ, insert the next() from that iterator
 *
 * Time - O(log n) - due to use of priority queue
 * Space - O(n)
 */
class SortedIteratorMultiple<E extends Comparable<E>> implements SortedIterator<E> {

  static class PQElement<E extends Comparable<E>> implements Comparable<PQElement<E>> {
    E elem;
    SortedIterator<E> iter; // iterator from which this elem came

    PQElement(E elem, SortedIterator<E> iter) {
      this.elem = elem;
      this.iter = iter;
    }

    public int compareTo(PQElement<E> other) {
      return elem.compareTo(other.elem);
    }
  }

  private PriorityQueue<PQElement<E>> pq;

  public SortedIteratorMultiple(List<SortedIterator<E>> iterators) {
    pq = new PriorityQueue<>(iterators.size()); // we will store maximum 1 element from each iterator
    initPQ(iterators);
  }

  private void initPQ(List<SortedIterator<E>> iterators) {
    // take first elem from each iterator and put in PQ
    for (SortedIterator<E> si : iterators) {
      if (si != null && si.hasNext()) {
        pq.add(new PQElement<>(si.next(), si));
      }
    }
  }

  public boolean hasNext() {
    return !pq.isEmpty();
  }

  public E next() {
    if (!hasNext()) {
      throw new ArrayIndexOutOfBoundsException("No more elements!");
    }

    PQElement<E> pqElement = this.pq.remove();

    if (pqElement.iter.hasNext()) {
      pq.add((new PQElement<>(pqElement.iter.next(), pqElement.iter)));
    }

    return pqElement.elem;
  }
}