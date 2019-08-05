package com.shabs.datastructures.binarytree;

public class BinaryTreeNode<T> {
  public T value;

  public BinaryTreeNode<T> left;
  public BinaryTreeNode<T> right;

  public BinaryTreeNode() {}

  public BinaryTreeNode(T d) {
    value = d;
  }

  public void printLinkedList(String prefix) {
    System.out.print(prefix + " ==>> " + value + " ");

    BinaryTreeNode nextNode = right;
    while (nextNode != null) {
      System.out.print(nextNode.value + " ");
      nextNode = nextNode.right;
    }
    System.out.println(" ");
  }
}
