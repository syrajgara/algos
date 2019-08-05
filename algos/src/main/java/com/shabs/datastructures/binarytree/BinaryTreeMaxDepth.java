package com.shabs.datastructures.binarytree;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Find maximum depth of a BT
 * <p>
 * Recursive:
 * - recurse to the left and right of each node
 * - return the depth from each call
 * - zero for null/end nodes
 * - on each call +1 to the value returned from previous call
 * <p>
 * Iterative - DFS - using 2 stacks
 * - push node to one stack and depth to another
 * - keep increasing depth as you pop parent and push childs
 * - keep track of max depth
 */
public class BinaryTreeMaxDepth {

  public int findDepth(BinaryTreeNode<Integer> n) {
    if (n == null) {
      return 0;
    }

    return 1 + Math.max(findDepth(n.left), findDepth(n.right));
  }

  @Test
  public void testDepth() {
    BinaryTreeNode<Integer> n1 = new BinaryTreeNode<>(1);
    BinaryTreeNode<Integer> n2 = new BinaryTreeNode<>(2);
    BinaryTreeNode<Integer> n3 = new BinaryTreeNode<>(2);
    BinaryTreeNode<Integer> n4 = new BinaryTreeNode<>(3);
    BinaryTreeNode<Integer> n5 = new BinaryTreeNode<>(3);
    BinaryTreeNode<Integer> n6 = new BinaryTreeNode<>(4);

    n1.left = n2;
    n1.right = n3;

    n2.left = n4;
    n2.right = n5;

    n4.left = n6;

    int expected = 4;
    int actual = findDepth(n1);

    Assert.assertEquals(actual, expected);
  }
}
