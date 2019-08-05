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
 * take first elements of each iterator in the list and put in PQHolder and in PQ
 * when you remove one element from the PQ, insert the next() from that iterator
 *
 * Time - O(log n) - due to use of priority queue
 * Space - O(n)
 */
class SortedIteratorMultiple<E extends Comparable<E>> implements SortedIterator<E> {

  static class PQHolder<E extends Comparable<E>> implements Comparable<PQHolder<E>> {
    E elem;
    SortedIterator<E> iter;

    PQHolder(E elem, SortedIterator<E> iter) {
      this.elem = elem;
      this.iter = iter;
    }

    public int compareTo(PQHolder<E> other) {
      return elem.compareTo(other.elem);
    }
  }

  private PriorityQueue<PQHolder<E>> pq;

  public SortedIteratorMultiple(List<SortedIterator<E>> iterators) {
    pq = new PriorityQueue<>(iterators.size());
    initPQ(iterators);
  }

  private void initPQ(List<SortedIterator<E>> iterators) {
    // take first elem from each iterator and put in PQ
    for (SortedIterator<E> si : iterators) {
      if (si != null && si.hasNext()) {
        PQHolder<E> h = new PQHolder<>(si.next(), si);
        pq.add(h);
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

    PQHolder<E> h = pq.remove();

    if (h.iter.hasNext()) {
      PQHolder<E> newH = new PQHolder<>(h.iter.next(), h.iter);
      pq.add(newH);
    }

    return h.elem;
  }
}