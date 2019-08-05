package com.shabs.datastructures.queue;

import com.shabs.datastructures.node.Node;
import org.testng.annotations.Test;

/**
 * FIFO
 * enqueue, dequeue, peek
 *
 * use Node (head, tail and next pointer on Node) for implementing the queue
 *
 */
public class Queue<T> {
  private Node<T> head;
  private Node<T> tail;

  public Queue() {
  }

  public Node<T> enqueue(T data) {
    Node<T> newNode = new Node<>(data);
    if (head == null) {
      head = newNode;
    } else {
      newNode.setPrevious(tail); // for double side queue
      tail.setNext(newNode);
    }

    tail = newNode;

    return tail;
  }

  public Node<T> peek() {
    return head;
  }

  public Node<T> peekTail() {
    return tail;
  }

  public Node<T> dequeue() {
    Node<T> returnNode = new Node<>(null);
    if (head != null) {
      returnNode = head;
      if (head == tail) {
        // just dequeued the last node.
        tail = null;
      } else {
        head.setPrevious(null);
      }
      head = head.getNext();
    }

    return returnNode;
  }

  public Node<T> pop() {
    // for some algos need to be able to pop from the end, in addition to dequeue from the front.
    Node<T> returnNode = new Node<>(null);

    if (tail != null) {
      returnNode = tail;

      tail = tail.getPrevious();
      if (returnNode == head) {
        head = null;
        tail = null;
      } else {
        tail.setNext(null);
      }
    }

    return returnNode;
  }

  public boolean isEmpty() {
    return (head == null);
  }

  public void drainQueue() {
    head = tail = null;
  }

  @Override
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer();
    Node<T> tempNode = head;

    while (tempNode != null) {
      stringBuffer.append(tempNode.getData() + " ");
      tempNode = tempNode.getNext();
    }

    return stringBuffer.toString();

  }

  public void print(String prefix) {
    System.out.println(prefix + " : " + toString());
  }

  @Test
  public void test() {
    Queue<String> queue = new Queue<>();

    queue.enqueue("1");
    queue.print("1");

    queue.enqueue("2");
    queue.print("1 2");

    queue.dequeue();
    queue.print("2");

    queue.enqueue("3");
    queue.print("2 3");

    queue.dequeue();
    queue.print("3");

    queue.dequeue();
    queue.print("Null");

    queue.dequeue();
    queue.print("Null");
  }
}
