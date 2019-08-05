package com.shabs.datastructures.binarytree;

import com.shabs.datastructures.node.Node;
import org.testng.annotations.Test;

public class BinaryTreeRootToLeafPath {

  public void printRootToLeafPath(Node<Integer> node, String pathSoFar) {
    if (node == null) {
      System.out.println(pathSoFar);
      return;
    }

    pathSoFar += node.getData() + ", ";

    if (node.getPrevious() != null) {
      printRootToLeafPath(node.getPrevious(), pathSoFar);
    }

    if (node.getPrevious() == null || node.getNext() != null) {
      printRootToLeafPath(node.getNext(), pathSoFar);
    }
  }

  @Test
  public void printRootToLeafPath() {
    Node<Integer> node1 = new Node<>(1);
    Node<Integer> node2 = new Node<>(2);
    Node<Integer> node3 = new Node<>(3);
    Node<Integer> node4 = new Node<>(4);
    Node<Integer> node5 = new Node<>(5);
    Node<Integer> node6 = new Node<>(6);
    Node<Integer> node7 = new Node<>(7);
    Node<Integer> node8 = new Node<>(8);
    Node<Integer> node9 = new Node<>(9);

    node1.setPrevious(node2);
    node1.setNext(node3);

    node2.setPrevious(node4);
    node2.setNext(node5);

    node5.setPrevious(node6);
    node5.setNext(node7);

    node3.setNext(node8);

    node8.setPrevious(node9);

    printRootToLeafPath(node1, "");
  }
}
