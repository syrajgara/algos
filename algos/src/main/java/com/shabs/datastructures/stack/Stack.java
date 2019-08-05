package com.shabs.datastructures.stack;

import com.shabs.datastructures.node.Node;

public class Stack<T> {
  private Node<T> top;

  public Node<T> push(T data) {
    Node<T> node = new Node<>(data);
    return push(node);
  }

  public Node<T> push(Node<T> node) {
    node.setNext(top);
    top = node;

    return top;
  }

  public Node<T> peek() {
    return top;
  }

  public Node<T> pop() {
    Node<T> node = top;
    if (top != null) {
      top = top.getNext();
    }

    return node;
  }

  public boolean isEmpty() {
    return (top == null);
  }

  public void clearStack() {
    top = null;
  }

  @Override
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(">> ");
    Node<T> tempNode = top;

    while (tempNode != null) {
      stringBuffer.append(tempNode.getData() + " ");
      tempNode = tempNode.getNext();
    }

    return stringBuffer.toString();
  }

  public void print(String prefix) {
    System.out.println(prefix + " : " + toString());
  }

  public static void main(String[] args) {
    Stack<String> stack = new Stack<>();

    stack.print("expect Null");
    stack.pop();
    stack.print("expect Null");

    stack.push("1");
    stack.print("expect    1");

    stack.push("2");
    stack.print("expect   21");

    stack.pop();
    stack.print("expect    1");

    stack.push("3");
    stack.print("expect   31");
    stack.pop();
    stack.print("expect    1");
    stack.pop();
    stack.print("expect Null");
    stack.pop();
    stack.print("expect Null");
  }
}
