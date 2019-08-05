package com.shabs.datastructures.binarytree.iterator;

import com.shabs.datastructures.node.Node;
import com.shabs.datastructures.queue.Queue;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * BFS - Queue
 */
public class BinaryTreeLevelOrderIterator<T>
    implements Iterator<Node<T>>, Iterable<Node<T>> {

  private Queue<Node<T>> lazyQueue = new Queue<>();

  public BinaryTreeLevelOrderIterator() {
    // for test
  }

  public BinaryTreeLevelOrderIterator(Node<T> root) {
    lazyQueue.enqueue(root);
  }

  @Override
  public Iterator<Node<T>> iterator() {
    return this;
  }

  @Override
  public boolean hasNext() {
    return !lazyQueue.isEmpty();
  }

  @Override
  public Node<T> next() {
    if (!hasNext()) {
      throw new NoSuchElementException("we visited all the elements");
    }

    Node<T> next = lazyQueue.dequeue().getData();

    if (next.hasPrevious()) {
      lazyQueue.enqueue(next.getPrevious());
    }

    if (next.hasNext()) {
      lazyQueue.enqueue(next.getNext());
    }

    return next;
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

    node3.setNext(node8);

    node5.setPrevious(node6);
    node5.setNext(node7);

    node8.setPrevious(node9);

    run(node1, "1, 2, 3, 4, 5, 8, 6, 7, 9");
  }

  private static void run(Node<Integer> node1, String expected) {
    BinaryTreeLevelOrderIterator<Integer> binaryTreeLevelOrderIterator = new BinaryTreeLevelOrderIterator<>(node1);

    System.out.print(expected);

    StringBuffer actualBuf = new StringBuffer();

    for (Node<Integer> node : binaryTreeLevelOrderIterator) {
      actualBuf.append(node.getData() + ", ");
    }

    String actual = actualBuf.toString();

    Assert.assertEquals(actual, expected + ", ");
    System.out.println("\n--------------------------------------");
  }
}