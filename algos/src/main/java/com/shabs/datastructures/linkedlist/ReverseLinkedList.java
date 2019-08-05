package com.shabs.datastructures.linkedlist;

import com.shabs.datastructures.node.Node;

public class ReverseLinkedList {

  public Node<Integer> reverse(Node<Integer> root) {
    Node<Integer> newRoot = null;

    while (root != null) {
      Node<Integer> next = root.getNext();

      root.setNext(newRoot);
      newRoot = root;

      root = next;
    }

    return newRoot;
  }
}
