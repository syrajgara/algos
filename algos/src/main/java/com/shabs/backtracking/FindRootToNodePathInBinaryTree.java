package com.shabs.backtracking;

import com.shabs.datastructures.binarytree.BinaryTreeNode;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree and a leaf node, find the path from root to that node.
 *
 * add a node to the list of paths
 * go down left and right - if none of them return true, backtrack and remove current node from the paths
 */
public class FindRootToNodePathInBinaryTree {

  public static boolean find(BinaryTreeNode<Integer> root, Integer value, List<Integer> path) {
    if (root == null) {
      return false;
    }

    if (root.value.equals(value)) {
      path.add(root.value);

      return true;
    }

    if (find(root.left, value, path) || find(root.right, value, path)) {
      path.add(root.value);

      return true;
    }

    return false;
  }

  @Test
  public void test() {
    BinaryTreeNode<Integer> tree1 = new BinaryTreeNode<>(1);
    BinaryTreeNode<Integer> tree2 = new BinaryTreeNode<>(2);
    BinaryTreeNode<Integer> tree3 = new BinaryTreeNode<>(3);
    BinaryTreeNode<Integer> tree4 = new BinaryTreeNode<>(4);
    BinaryTreeNode<Integer> tree5 = new BinaryTreeNode<>(5);
    BinaryTreeNode<Integer> tree6 = new BinaryTreeNode<>(6);
    BinaryTreeNode<Integer> tree7 = new BinaryTreeNode<>(7);

    /**
     *          1
     *     2        3
     *  4    n    6
     * n 5      7
     */
    tree1.left = tree2;
    tree1.right = tree3;
    tree2.left = tree4;
    tree4.right = tree5;
    tree3.left = tree6;
    tree6.left = tree7;

    System.out.println("TC #1 - VALID");
    List<Integer> path = new ArrayList<>();
    find(tree1, 5, path);

    for (int i : path) {
      System.out.print(i + ", ");
    }
    System.out.println();
    System.out.println("----------------------");


    System.out.println("TC #2 - VALID");
    List<Integer> path2 = new ArrayList<>();
    find(tree1, 7, path2);

    for (int i : path2) {
      System.out.print(i + ", ");
    }
    System.out.println();
    System.out.println("----------------------");


    System.out.println("TC #3 - INVALID");
    List<Integer> path3 = new ArrayList<>();
    find(tree1, 10, path3);

    for (int i : path3) {
      System.out.print(i + ", ");
    }
    System.out.println("----------------------");
  }
}
