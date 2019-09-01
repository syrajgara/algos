package com.shabs.datastructures.queue;

import org.testng.annotations.Test;

public class QueueWithArray {
  int[] list;
  int maxCapacity;
  int currCapacity = 0;
  int currentHead = 0;
  int writeIndex = 0;

  public QueueWithArray(int capacity) {
    this.maxCapacity = capacity;
    list = new int[capacity];
  }

  public boolean enqueue(int data) {
    if (currCapacity == maxCapacity) {
      return false;
    }

    currCapacity++;
    list[writeIndex++] = data;

    if (writeIndex == maxCapacity) {
      writeIndex = 0;
    }

    return true;
  }

  public int dequeue() {
    if (currCapacity == 0) {
      return -1;
    }

    int returnElement = list[currentHead];
    list[currentHead] = -1;

    currCapacity--;
    currentHead++;

    if (currentHead == maxCapacity) {
      currentHead = 0;
    }

    return returnElement;
  }

  @Test
  public void test() {
    QueueWithArray q = new QueueWithArray(3);

    boolean status = false;
    int output = 0;

    status = q.enqueue(1);
    System.out.println(status);
    status = q.enqueue(2);
    System.out.println(status);
    status = q.enqueue(3);
    System.out.println(status);

    output = q.dequeue();
    System.out.println(output);


    status = q.enqueue(4);
    System.out.println(status);

    output = q.dequeue();
    System.out.println(output);
    output = q.dequeue();
    System.out.println(output);
    output = q.dequeue();
    System.out.println(output);
  }
}
