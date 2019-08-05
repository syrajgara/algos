package com.shabs.datastructures.linkedlist;

import com.shabs.datastructures.node.Node;

/**
 * LinkedList
 * add first, last
 * remove
 * search
 */
public class LinkedList<T> {
  public Node<T> root;

  public Node<T> addFirst(T data) {
    Node<T> node = new Node<>(data);

    node.setNext(root);
    root = node;

    return root;
  }

  public Node<T> addLast(T data) {
    Node<T> node = new Node<>(data);

    if (root == null) {
      root = node;
    } else {
      Node currentNode = root;

      while (currentNode.hasNext()) {
        currentNode = currentNode.getNext();
      }

      currentNode.setNext(node);
    }

    return node;
  }

  public void remove(T data) {

    if (root == null) {
      return;
    }

    if (root.getData().equals(data)) {
      root = root.getNext();
      return;
    }

    Node<T> currentNode = root;
    while (currentNode.getNext() != null) {
      if (currentNode.getNext().getData().equals(data)) {
        currentNode.setNext(currentNode.getNext().getNext());
        return;
      }
      currentNode = currentNode.getNext();
    }
  }

  public boolean exists(T data) {
    Node<T> currentNode = root;

    while (currentNode != null) {
      if (currentNode.getData().equals(data)) {
        return true;
      }
      currentNode = currentNode.getNext();
    }

    return false;
  }

  public void print() {
    Node<T> node = root;
    while (node != null) {
      System.out.print(node.getData() + " -> ");
      node = node.getNext();
    }
    System.out.println();
  }
}
