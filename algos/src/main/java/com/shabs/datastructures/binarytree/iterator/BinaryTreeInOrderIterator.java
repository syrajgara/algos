package com.shabs.datastructures.binarytree.iterator;

import com.shabs.datastructures.binarytree.BinaryTreeNode;
import com.shabs.datastructures.stack.Stack;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator to Traverse a Binary Tree
 * http://en.wikipedia.org/wiki/Tree_traversal
 * <p>
 * push all left nodes in a stack,
 * as soon as you pop one node out
 * push its right and then all left nodes of the right.
 */
public class BinaryTreeInOrderIterator<T>
    implements Iterator<BinaryTreeNode<T>>, Iterable<BinaryTreeNode<T>> {
  // Stack itself keeps next node, that node should be of type BinaryTreeNode<T> and not just T
  private Stack<BinaryTreeNode<T>> stack = new Stack<>();


  public BinaryTreeInOrderIterator() {
    // for test
  }

  public BinaryTreeInOrderIterator(BinaryTreeNode<T> root) {
    pushLeftNodes(root);
    //pushLeftNodesRecursive(root);
  }

  @Override
  public Iterator<BinaryTreeNode<T>> iterator() {
    return this;
  }

  private void pushLeftNodes(BinaryTreeNode<T> node) {
    while (node != null) {
      stack.push(node);
      node = node.left;
    }
  }

  private void pushLeftNodesRecursive(BinaryTreeNode<T> node) {
    // not very space efficient
    if (node == null) {
      return;
    }
    stack.push(node);
    pushLeftNodes(node.left);
  }

  @Override
  public boolean hasNext() {
    return !stack.isEmpty();
  }

  @Override
  public BinaryTreeNode<T> next() {
    if (!hasNext()) {
      throw new NoSuchElementException("we visited all the elements");
    }

    return nextForInOrder();
  }

  public BinaryTreeNode<T> nextForInOrder() {
    BinaryTreeNode<T> node = popStackData();
    pushLeftNodes(node.right);

    return node;
  }

  private BinaryTreeNode<T> popStackData() {
    // stack saves our data (BinaryTreeNode<T>) inside its own node
    return stack.pop().getData();
  }

  @Override
  public void remove() {
    // unimplemented, will need tree to be balanced
    throw new UnsupportedOperationException();
  }

  @Test
  public void testIterators() {
    BinaryTreeNode<Integer> node1 = new BinaryTreeNode<>(1);
    BinaryTreeNode<Integer> node2 = new BinaryTreeNode<>(2);
    BinaryTreeNode<Integer> node3 = new BinaryTreeNode<>(3);
    BinaryTreeNode<Integer> node4 = new BinaryTreeNode<>(4);
    BinaryTreeNode<Integer> node5 = new BinaryTreeNode<>(5);
    BinaryTreeNode<Integer> node6 = new BinaryTreeNode<>(6);
    BinaryTreeNode<Integer> node7 = new BinaryTreeNode<>(7);
    BinaryTreeNode<Integer> node8 = new BinaryTreeNode<>(8);
    BinaryTreeNode<Integer> node9 = new BinaryTreeNode<>(9);

    node1.left = (node2);
    node1.right = (node3);

    node2.left = (node4);
    node2.right = (node5);

    node5.left = (node6);
    node5.right = (node7);

    node3.right = (node8);

    node8.left = (node9);

    run(node1, "4, 2, 6, 5, 7, 1, 3, 9, 8");
  }

  private static void run(BinaryTreeNode<Integer> node1, String expected) {
    BinaryTreeInOrderIterator<Integer> binaryTreeInOrderIterator = new BinaryTreeInOrderIterator<>(node1);

    System.out.print(expected);

    StringBuffer actualBuf = new StringBuffer();
    for (BinaryTreeNode<Integer> node : binaryTreeInOrderIterator) {
      actualBuf.append(node.value + ", ");
    }

    String actual = actualBuf.toString();

    Assert.assertEquals(actual, expected + ", ");
    System.out.println("\n--------------------------------------");
  }
}
