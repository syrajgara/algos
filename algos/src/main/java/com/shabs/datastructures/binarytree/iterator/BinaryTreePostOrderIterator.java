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
 * push node to stack, then
 * push left nodes to a stack, and right node only if left is empty
 * recursively keep pushing the left (or right if left not present) till both are null.
 * <p>
 * when you pop, check if it was left node, if yes then push its right sibling and leftnodes of that sibling.
 */
public class BinaryTreePostOrderIterator<T> implements Iterator<Node<T>>, Iterable<Node<T>> {
  // Stack itself keeps next node, that node should be of type Node<T> and not just T
  private Stack<Node<T>> stack = new Stack<>();

  public BinaryTreePostOrderIterator(Node<T> root) {
    pushLeftNodes(root);
  }

  @Override
  public Iterator<Node<T>> iterator() {
    return this;
  }

  private void pushLeftNodes(Node<T> node) {
    // push left node, push right only if left is null.
    while (node != null) {
      stack.push(node);
      if (node.getPrevious() != null) {
        node = node.getPrevious();
      } else {
        node = node.getNext();
      }
    }
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

    return nextForPostOrder();
  }

  public Node<T> nextForPostOrder() {
    Node<T> node = popStackData();

    if (stack.peek() != null) {
      Node<T> nextNodeInStack = stack.peek().getData();
      if (node.equals(nextNodeInStack.getPrevious())) {
        // if we just pop`ed a left node, push the right node.
        // see if node is left of the next node in the stack
        if (nextNodeInStack.getNext() != null) {
          pushLeftNodes(nextNodeInStack.getNext());
        }
      }
    }

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
  public static void testIterators() {
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

    run(node1, "4, 6, 7, 5, 2, 9, 8, 3, 1");
  }

  private static void run(Node<Integer> node1, String expected) {
    BinaryTreePostOrderIterator<Integer> binaryTreePostOrderIterator = new BinaryTreePostOrderIterator<>(node1);

    System.out.print(expected);

    StringBuffer actualBuf = new StringBuffer();
    for (Node<Integer> node : binaryTreePostOrderIterator) {
      actualBuf.append(node.getData() + ", ");
    }

    String actual = actualBuf.toString();

    Assert.assertEquals(actual, expected + ", ");
    System.out.println("\n--------------------------------------");
  }
}


