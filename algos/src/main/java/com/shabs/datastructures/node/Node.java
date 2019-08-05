package com.shabs.datastructures.node;

import java.util.List;

public class Node<T> {
  private T data;
  private Node<T> next; // right node in binary tree
  private Node<T> previous; // left node in binary tree

  private List<Node<T>> connectedTo; // for graph - need weights on edges, use Edge

  private boolean visited = false;

  //public Node() {}
  public Node(T data) {
    this.data = data;
  }

  public void setNext(Node<T> next) {
    this.next = next;
  }

  public Node<T> getNext() {
    return next;
  }

  public boolean hasNext() {
    return (getNext() != null);
  }

  public void setPrevious(Node<T> previous) {
    this.previous = previous;
  }

  public Node<T> getPrevious() {
    return previous;
  }

  public boolean hasPrevious() {
    return (getPrevious() != null);
  }

  public String toString() {
    return data.toString();
  }

  public T getData() {
    return data;
  }

  public boolean isVisited() {
    return visited;
  }

  public void setVisited(boolean visited) {
    this.visited = visited;
  }

  public void printLinkedList(String prefix) {
    System.out.print(prefix + " ==>> " + data + " ");

    Node nextNode = next;
    while (nextNode != null) {
      System.out.print(nextNode.data + " ");
      nextNode = nextNode.getNext();
    }
    System.out.println(" ");
  }
}
