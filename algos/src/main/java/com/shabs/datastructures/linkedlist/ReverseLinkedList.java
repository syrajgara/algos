package com.shabs.datastructures.linkedlist;

import com.shabs.datastructures.node.Node;

/**
 * Given a singly linked list, reverse it
 */
public class ReverseLinkedList<T> {

  public Node<T> reverse(Node<T> root) {
    Node<T> newRoot = null;

    while (root != null) {
      Node<T> next = root.getNext();

      root.setNext(newRoot);
      newRoot = root;

      root = next;
    }

    return newRoot;
  }
}
