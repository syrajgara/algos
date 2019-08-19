package com.shabs.datastructures.linkedlist;

import com.shabs.datastructures.node.Node;
import org.testng.annotations.Test;

/**
 * 2 Pointers
 * <p>
 * run first pointer n steps
 * run first and second together - till next of first is null
 * re-link next of the second, to skip the nth element from the end.
 */
public class RemoveNthNodeFromEndOfList {

  private void remove(LinkedList<Integer> list, int nFromEnd) {
    Node<Integer> firstPointer = list.root;
    Node<Integer> secondPointer = list.root;

    for (int i = 0; i < nFromEnd; i++) {
      System.out.println("First: " + firstPointer.getData());
      firstPointer = firstPointer.getNext();
    }

    while (firstPointer.hasNext()) {
      System.out.println("First: " + firstPointer.getData());
      firstPointer = firstPointer.getNext();

      System.out.println("Second: " + secondPointer.getData());
      secondPointer = secondPointer.getNext();
    }

    System.out.println("First: " + firstPointer.getData());
    System.out.println("Second: " + secondPointer.getData());

    secondPointer.setNext(secondPointer.getNext().getNext());
    System.out.println("Second: " + secondPointer.getNext().getData());
  }

  @Test
  public void test() {
    LinkedList<Integer> list = new LinkedList<>();

    list.addLast(1);
    list.addLast(2);
    list.addLast(3);
    list.addLast(4);
    list.addLast(5);

    remove(list, 2);
  }

}
