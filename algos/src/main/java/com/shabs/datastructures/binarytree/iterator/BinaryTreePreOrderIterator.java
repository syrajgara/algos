package com.shabs.datastructures.binarytree.iterator;

import com.shabs.datastructures.node.Node;
import com.shabs.datastructures.stack.Stack;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator to Traverse a Binary Tree
 * http://en.wikipedia.org/wiki/Tree_traversal
 * <p>
 * put the node in the stack.
 * pop stack and print - put right and then left in the stack, repeat this till no nodes left in the stack
 */
public class BinaryTreePreOrderIterator<T> implements Iterator<Node<T>>, Iterable<Node<T>> {
  // Stack itself keeps next node, that node should be of type Node<T> and not just T
  private Stack<Node<T>> stack = new Stack<>();

  // since we are writing non-static test in this class we need a public no-arg constructor.
  public BinaryTreePreOrderIterator() {
  }

  public BinaryTreePreOrderIterator(Node<T> root) {
    stack.push(root);
  }

  @Override
  public Iterator<Node<T>> iterator() {
    return this;
  }

  @Override
  public boolean hasNext() {
    return !stack.isEmpty();
  }

  @Override
  public Node<T> next() {
    if (!hasNext()) {
      throw new NoSuchElementException("we visited all the elements");
    }

    return nextForPreOrder();
  }

  private Node<T> nextForPreOrder() {
    Node<T> node = popStackData();

    pushStackData(node.getNext());
    pushStackData(node.getPrevious());

    return node;
  }

  private void pushStackData(Node<T> node) {
    if (node != null) {
      stack.push(node);
    }
  }

  private Node<T> popStackData() {
    // stack saves our data (Node<T>) inside its own node
    Node<Node<T>> stackNode = stack.pop();
    return stackNode.getData();
  }

  @Override
  public void remove() {
    // unimplemented, will need tree to be balanced
    throw new UnsupportedOperationException();
  }

  @Test
  public void testIterators() {
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

    run(node1, "1, 2, 4, 5, 6, 7, 3, 8, 9");
  }

  private static void run(Node<Integer> node1, String expected) {
    BinaryTreePreOrderIterator<Integer> binaryTreePreOrderIterator = new BinaryTreePreOrderIterator<>(node1);

    System.out.print(expected);

    StringBuffer actualBuf = new StringBuffer();
    for (Node<Integer> node : binaryTreePreOrderIterator) {
      actualBuf.append(node.getData() + ", ");
    }

    String actual = actualBuf.toString();

    Assert.assertEquals(actual, expected + ", ");
    System.out.println("\n--------------------------------------");
  }
}
