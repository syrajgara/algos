package com.shabs.datastructures.linkedlist;

import org.testng.annotations.Test;

import java.util.*;
import java.util.LinkedList;

/**
 * A list of iterators are given
 * Write another iterator that will iterate thru these iterators in round robbin fashion.
 */
public class RoundRobinIterator<T> implements Iterator<T>, Iterable<T> {

  private List<Iterator<T>> iteratorList;
  private Iterator<Iterator<T>> outerIterator;

  public RoundRobinIterator() {
  }

  public RoundRobinIterator(List<Iterator<T>> iteratorList) {
    this.iteratorList = iteratorList;
    outerIterator = iteratorList.iterator();
  }

  @Override
  public Iterator<T> iterator() {
    return this;
  }

  @Override
  public boolean hasNext() {

    // if one of the outerIterator in the list hasNext, we still have elements to iterate over
    for (Iterator<T> i : iteratorList) {
      if (i.hasNext()) return true;
    }
    return false;
  }

  @Override
  public T next() {

    if (!hasNext()) {
      throw new NoSuchElementException();
    }

    while (true) {
      if (!outerIterator.hasNext()) {
        // reset - roundRobin on outer iterator
        outerIterator = iteratorList.iterator();
      }

      Iterator<T> innerIterator = outerIterator.next();
      if (innerIterator.hasNext()) {
        return innerIterator.next();
      }
    }
  }

  @Test
  public void test() {
    List<Integer> list1 = new java.util.LinkedList<>(Arrays.asList(1, 6, 10));
    List<Integer> list2 = new java.util.LinkedList<>(Arrays.asList(2));
    List<Integer> list3 = new java.util.LinkedList<>(Arrays.asList(3, 7));
    List<Integer> list4 = new java.util.LinkedList<>(Arrays.asList(4, 8, 11, 12));
    List<Integer> list5 = new java.util.LinkedList<>(Arrays.asList(5, 9));

    List<Iterator<Integer>> iterators = new LinkedList<>();
    iterators.add(list1.iterator());
    iterators.add(list2.iterator());
    iterators.add(list3.iterator());
    iterators.add(list4.iterator());
    iterators.add(list5.iterator());

    RoundRobinIterator<Integer> roundRobinIterator = new RoundRobinIterator(iterators);

    while (roundRobinIterator.hasNext()) {
      System.out.print(roundRobinIterator.next() + ", ");
    }
  }
}
