package com.shabs.datastructures.linkedlist;

import com.shabs.datastructures.node.Node;
import org.testng.annotations.Test;

/**
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 *
 * Add the two numbers and return it as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 */
public class AddTwoNumbers {

  private LinkedList<Integer> addNumbers(LinkedList<Integer> operand1, LinkedList<Integer> operand2) {

    LinkedList<Integer> result = new LinkedList<>();

    int carry = 0;
    Node<Integer> node1 = operand1.root;
    Node<Integer> node2 = operand2.root;

    while (node1 != null && node2 != null) {
      int sum = carry + node1.getData() + node2.getData();

      int resultData = sum % 10;
      carry = sum / 10;

      result.addLast(resultData);
      node1 = node1.getNext();
      node2 = node2.getNext();
    }

    if (carry == 1) {
      result.addLast(1);
    }

    return result;
  }

  @Test
  public void test() {
    LinkedList<Integer> operand1 = new LinkedList<>();
    operand1.addFirst(3);
    operand1.addFirst(4);
    operand1.addFirst(2);

    LinkedList<Integer> operand2 = new LinkedList<>();
    operand2.addFirst(6);
    operand2.addFirst(6);
    operand2.addFirst(5);

    LinkedList<Integer> actual = addNumbers(operand1, operand2);
    actual.print();
  }
}
