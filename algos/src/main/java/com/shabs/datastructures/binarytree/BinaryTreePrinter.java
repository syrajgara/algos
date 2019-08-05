package com.shabs.datastructures.binarytree;

import org.testng.annotations.Test;

public class BinaryTreePrinter {

  public static void printInOrder(BinaryTreeNode root) {
    if (root == null) {
      return;
    }

    printInOrder(root.left);
    System.out.print(root.value + ", ");
    printInOrder(root.right);
  }

  public static void printPreOrder(BinaryTreeNode root) {
    if (root == null) {
      return;
    }

    System.out.print(root.value + ", ");
    printPreOrder(root.left);
    printPreOrder(root.right);
  }

  public static void printPostOrder(BinaryTreeNode root) {
    if (root == null) {
      return;
    }

    printPostOrder(root.left);
    printPostOrder(root.right);
    System.out.print(root.value + ", ");
  }

  @Test
  public void testDepth() {
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
    System.out.println("PRE ORDER");
    printPreOrder(n1);
    System.out.println();

    System.out.println("IN ORDER");
    printInOrder(n1);
    System.out.println();

    System.out.println("POST ORDER");
    printPostOrder(n1);
    System.out.println();
  }
}
