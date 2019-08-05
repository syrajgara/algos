package com.shabs.datastructures.binarytree;

import org.testng.annotations.Test;

public class BinaryTreeLinkedListConverter {
  public static <T> BinaryTreeNode<T> convertToInOrderLinkedList(BinaryTreeNode<T> root) {
    if (root == null) {
      return null;
    }

    BinaryTreeNode<T> leftSeriesFirstNode = convertToInOrderLinkedList(root.left);
    BinaryTreeNode<T> centerNode = new BinaryTreeNode<>(root.value);
    BinaryTreeNode<T> rightSeriesFirstNode = convertToInOrderLinkedList(root.right);

    // attach center node to end of left series
    if (leftSeriesFirstNode != null) {
      BinaryTreeNode<T> leftSeriesLastNode = leftSeriesFirstNode;
      while (leftSeriesLastNode.right != null) {
        leftSeriesLastNode = leftSeriesLastNode.right;
      }

      leftSeriesLastNode.right = centerNode;
    }

    // attach right series to center node
    centerNode.right = rightSeriesFirstNode;

    return (leftSeriesFirstNode != null) ? leftSeriesFirstNode : centerNode;
  }

  @Test
  public void test() {
    BinaryTreeNode<Integer> n1 = new BinaryTreeNode<>(1);
    BinaryTreeNode<Integer> n2 = new BinaryTreeNode<>(2);
    BinaryTreeNode<Integer> n3 = new BinaryTreeNode<>(3);
    BinaryTreeNode<Integer> n4 = new BinaryTreeNode<>(4);
    BinaryTreeNode<Integer> n5 = new BinaryTreeNode<>(5);
    BinaryTreeNode<Integer> n6 = new BinaryTreeNode<>(6);

    n1.left = n2;
    n1.right = n3;

    n2.left = n4;
    n2.right = n5;

    n4.left = n6;

    /*
            1
          2   3
        4   5
      6
     */

    BinaryTreeNode<Integer> firstNodeOfLinkedList = convertToInOrderLinkedList(n1);
    System.out.println("Printing Linked List version of BST");
    firstNodeOfLinkedList.printLinkedList("");
  }
}
