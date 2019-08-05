package com.shabs.datastructures.binarytree;

import com.shabs.datastructures.node.Node;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * - if diff in height is within the tolerance T (generally diff of 1)
 * - check if left node is balanced, check if right is balanced
 * - check diff between left and right height is <= T
 * - so return height at each level, -1 if not balanced to short circuit
 */
public class BinaryTreeIsHeightBalanced {
  public int isHeightBalanced(Node<Integer> node) {
    // isHeightBalanced if the height differs by less than 2.
    if (node == null) {
      // we are at sentinel node
      return 0;
    }

    int heightLeft = isHeightBalanced(node.getPrevious());
    if (heightLeft == -1) {
      // -1 indicates not balanced
      return -1;
    }

    int heightRight = isHeightBalanced(node.getNext());
    if (heightRight == -1) {
      // -1 indicates not balanced
      return -1;
    }

    if (Math.abs(heightLeft - heightRight) > 1) {
      // -1 indicates not balanced
      // either side is not balanced, Or diff in height is more than 1
      return -1;
    }

    return Math.max(heightLeft, heightRight) + 1; // add 1 for current node
  }


  @Test
  public void balancedBinaryTree() {
    Node<Integer> node1 = new Node<>(1);

    Node<Integer> node2 = new Node<>(2);
    node1.setPrevious(node2);

    Node<Integer> node3 = new Node<>(3);
    node1.setNext(node3);

    Node<Integer> node4 = new Node<>(4);
    node2.setNext(node4);

    int height = isHeightBalanced(node1);

    Assert.assertEquals(height, 3);
  }

  @Test
  public void unbalancedBinaryTree() {
    Node<Integer> node1 = new Node<>(1);

    Node<Integer> node2 = new Node<>(2);
    node1.setPrevious(node2);

    Node<Integer> node3 = new Node<>(3);
    node1.setNext(node3);

    Node<Integer> node4 = new Node<>(4);
    node2.setNext(node4);

    Node<Integer> node5 = new Node<>(5);
    node4.setNext(node5);

    int height = isHeightBalanced(node1);

    Assert.assertEquals(height, -1);
  }
}
