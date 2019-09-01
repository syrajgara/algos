package com.shabs.datastructures.stack;


import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Given a nested int array, write iterator to flatten it out.
 *
 * [[1,1],2,[1,1]] --> [1,1,2,1,1] --> iterator over this
 *
 * - Use a stack, put one element from the input array
 * - during next() pop one element,
 * - if it is a int return,
 * - if array expand the array back into the stack, and pop first element - till it is an int
 */
public class FlattenNestedListIterator<T> implements Iterator<T>, Iterable<T> {

  private class NestedValue<T> {
    // mutually exclusive variables
    boolean isValue = false;
    T value;
    List<NestedValue<T>> nested;

    public NestedValue(T i) {
      value = i;
      isValue = true;
    }

    public NestedValue(T[] n) {
      nested = new ArrayList<>();
      for (T i : n) {
        nested.add(new NestedValue<>(i));
      }
    }

    public NestedValue(List<NestedValue<T>> n) {
      nested = n;
    }
  }

  Stack<NestedValue<T>> stack = new Stack<>();

  public FlattenNestedListIterator() {
    // needed by @Test
  }

  public FlattenNestedListIterator(NestedValue<T> v) {
    stack.push(v);
  }

  @Override
  public boolean hasNext() {
    return !stack.isEmpty();
  }

  @Override
  public T next() {
    if (!hasNext()) {
      throw new ArrayIndexOutOfBoundsException();
    }

    fixQueue(); // lazy, fix before pulling data out, not when putting it in the stack
    T next = stack.pop().getData().value;

    return next;
  }

  private void fixQueue() {
    while (!stack.peek().getData().isValue) {
      List<NestedValue<T>> nextValue = stack.pop().getData().nested;

      for (int i = nextValue.size() - 1; i >= 0; i--) {
        stack.push(nextValue.get(i));
      }
    }
  }

  @Override
  public Iterator<T> iterator() {
    return this;
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }

  @Test
  public void test() {
    List<NestedValue<Integer>> input = new ArrayList<>();

    Integer[] i1 = {1,2};
    NestedValue<Integer> n1 = new NestedValue<>(i1);
    input.add(n1);

    NestedValue<Integer> n2 = new NestedValue<>(3);
    input.add(n2);

    Integer[] i3 = {4,5};
    NestedValue<Integer> n3 = new NestedValue<>(i3);
    input.add(n3);

    NestedValue<Integer> wrappedInput = new NestedValue<Integer>(input);

    FlattenNestedListIterator<Integer> iterator = new FlattenNestedListIterator(wrappedInput);

    for (Integer i : iterator) {
      System.out.println(i);
    }
  }
}
