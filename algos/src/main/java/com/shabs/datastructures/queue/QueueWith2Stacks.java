package com.shabs.datastructures.queue;

import com.shabs.datastructures.node.Node;
import com.shabs.datastructures.stack.Stack;
import org.testng.annotations.Test;

public class QueueWith2Stacks<T> {
  Stack<T> pushStack = new Stack<>();
  Stack<T> popStack = new Stack<>();

  public Node<T> enqueue(T data) {
    if (!popStack.isEmpty()) {
      transferToPushStack();
    }

    return pushStack.push(data);
  }

  public Node<T> dequeue() {
    if (!pushStack.isEmpty()) {
      transferToPopStack();
    }

    return popStack.pop();
  }

  private void transferToPushStack() {
    transfer(popStack, pushStack);
  }

  private void transferToPopStack() {
    transfer(pushStack, popStack);
  }

  private void transfer(Stack<T> fromStack, Stack<T> toStack) {
    while (!fromStack.isEmpty()) {
      toStack.push(fromStack.pop());
    }
  }

  public boolean isEmpty() {
    return pushStack.isEmpty() && popStack.isEmpty();
  }

  @Override
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer();
    transferToPopStack();

    while (!popStack.isEmpty()) {
      stringBuffer.append(popStack.peek().getData());
      pushStack.push(popStack.pop());
    }

    return stringBuffer.toString();
  }

  public void print(String prefix) {
    System.out.println(prefix + " : " + toString());
  }

  @Test
  public void test() {
    QueueWith2Stacks<String> queue = new QueueWith2Stacks<String>();

    queue.print("Null");

    queue.enqueue("1");
    queue.print("1");

    queue.enqueue("2");
    queue.print("12");

    queue.dequeue();
    queue.print("2");

    queue.enqueue("3");
    queue.print("23");

    queue.dequeue();
    queue.print("3");

    queue.dequeue();
    queue.print("Null");

    queue.dequeue();
    queue.print("Null");
  }
}
