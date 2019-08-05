package com.shabs.datastructures.binarytree.iterator;

import com.shabs.datastructures.node.Node;
import com.shabs.datastructures.stack.Stack;
import org.testng.annotations.Test;

import java.util.Iterator;

/**
 * 1
 * / \
 * 2   3
 * / \ / \
 * 4  5 6  7
 * <p>
 * output => 1, 2, 3, 7, 6, 5, 4, ...
 * <p>
 * - Use 2 stacks L and R
 * - alternate L to R at each level
 */
public class BinaryTreeZigZagOrderIterator<T> implements Iterator<Node<T>>, Iterable<Node<T>> {

  private boolean readLeft = true;
  private Stack<Node<T>> leftStack = new Stack<>();
  private Stack<Node<T>> rightStack = new Stack<>();

  public BinaryTreeZigZagOrderIterator() {
    // for test
  }

  public BinaryTreeZigZagOrderIterator(Node<T> n) {
    leftStack.push(n);
  }

  @Override
  public Iterator<Node<T>> iterator() {
    return this;
  }

  @Override
  public boolean hasNext() {
    return !leftStack.isEmpty() || !rightStack.isEmpty();
  }

  @Override
  public Node<T> next() {
    Node<T> next;

    if (!hasNext()) {
      throw new ArrayIndexOutOfBoundsException();
    }

    if (readLeft) {
      next = leftStack.pop().getData();

      if (leftStack.isEmpty()) {
        readLeft = false;
      }

      push(rightStack, next.getNext());
      push(rightStack, next.getPrevious());
    } else {
      next = rightStack.pop().getData();

      if (rightStack.isEmpty()) {
        readLeft = true;
      }

      push(leftStack, next.getPrevious());
      push(leftStack, next.getNext());
    }

    return next;
  }

  private void push(Stack<Node<T>> stack, Node<T> node) {
    if (node != null) {
      stack.push(node);
    }
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

    node1.setPrevious(node2);
    node1.setNext(node3);

    node2.setPrevious(node4);
    node2.setNext(node5);

    node3.setPrevious(node6);
    node3.setNext(node7);


    run(node1, "1, 2, 3, 4, 5, 8, 6, 7, 9");
  }

  private static void run(Node<Integer> node1, String expected) {

    System.out.print(expected);

    StringBuffer actualBuf = new StringBuffer();

    BinaryTreeZigZagOrderIterator<Integer> binaryTreeZigZagOrderIterator = new BinaryTreeZigZagOrderIterator<>(node1);

    for (Node<Integer> node : binaryTreeZigZagOrderIterator) {
      actualBuf.append(node.getData() + ", ");
    }

    String actual = actualBuf.toString();
    System.out.print(actual);

    //Assert.assertEquals(actual, expected + ", ");
    System.out.println("\n--------------------------------------");
  }
}
