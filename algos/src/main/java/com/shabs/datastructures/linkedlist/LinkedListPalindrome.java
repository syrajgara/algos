package com.shabs.datastructures.linkedlist;

import com.shabs.datastructures.node.Node;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * 2 POINTERs
 * Given a singly linked list, determine if it is a palindrome
 * <p>
 * https://leetcode.com/problems/palindrome-linked-list/#/description
 * <p>
 * - move slow and fast pointers to get slow pointer to the center
 * - reverse the 2nd half of the list
 * - slow pointer will reach end and will be the head of the reversed 2nd half
 * - compare the first and reversed 2nd half to check if they match
 */
public class LinkedListPalindrome {

  private boolean checkPalindrome(LinkedList<Character> list) {

    if (list == null || list.root == null) {
      return false; // invalid list
    }

    Node<Character> pointerSlow = list.root;
    Node<Character> pointerFast = list.root;
    int length = 1;

    while (pointerFast != null) {
      pointerFast = pointerFast.getNext();
      length++;
      if (pointerFast == null) {
        break;
      }

      pointerSlow = pointerSlow.getNext();
      pointerFast = pointerFast.getNext();
      length++;
    }

    length--; // last char was null

    //System.out.println("LENGTH = " + length);
    pointerSlow.printLinkedList("pointerSlow");

    Node<Character> forwardPointer = list.root;
    Node<Character> reversePointer = reverse(pointerSlow);

    forwardPointer.printLinkedList("forwardPointer");
    reversePointer.printLinkedList("reversePointer");

    // now they should match till the center
    length = length/2; // need to verify only half of the string
    for (int i = 0; i < length; i++) {
      if (!forwardPointer.getData().equals(reversePointer.getData())) {
        return false;
      }
      forwardPointer = forwardPointer.getNext();
      reversePointer = reversePointer.getNext();
    }

    return true;
  }

  private Node<Character> reverse3(Node<Character> pointer, Node<Character> previousPointer) {

    if (pointer == null) {
      return previousPointer;
    }

    Node<Character> nextPointer = pointer.getNext();
    pointer.setNext(previousPointer);

    return reverse3(nextPointer, pointer);
  }

  private Node<Character> reverse(Node<Character> currentPointer) {

    if (currentPointer.getNext() == null) {
      return currentPointer;
    }

    Node<Character> nextPointer = currentPointer.getNext();
    Node<Character> returnPointer = reverse(nextPointer);

    nextPointer.setNext(currentPointer);
    // previous recursive call, will set this to the correct value
    currentPointer.setNext(null);

    return returnPointer;
  }

  @Test
  public void test() {
    LinkedList<Character> list = new LinkedList<>();

    list.addLast('a');
    list.addLast('b');
    list.addLast('c');
    list.addLast('b');
    list.addLast('a');

    boolean expected = true;
    boolean actual = checkPalindrome(list);

    Assert.assertEquals(actual, expected);
  }
}
