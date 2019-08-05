package com.shabs.datastructures.stack;

import com.shabs.datastructures.node.Node;
import org.testng.annotations.Test;

/**
 * Sort a stack, using another stack as helper
 *
 * - TEMP stack always in the right order - high (top of stack)  to low
 * - take top from the input stack - as currentElement
 * - check if it is higher than the top of the temp stack
 * - if not move top of temp to top of input till above condition met
 * - at that point move the currentElement to the temp stack
 */
public class StackSortUsingAnotherStack {
  public static Stack<Integer> sort(Stack<Integer> stack) {
    // will hold smaller values at the bottom of the stack
    Stack<Integer> tempStack = new Stack<>();

    while (!stack.isEmpty()) {
      Node<Integer> currentNode = stack.pop();

      while (!tempStack.isEmpty() && tempStack.peek().getData() > currentNode.getData()) {
        stack.push(tempStack.pop());
      }

      tempStack.push(currentNode);
    }

    return tempStack; // reverse order
  }

  @Test
  public void test() {
    Stack<Integer> stack = new Stack<Integer>();

    stack.push(2);
    stack.push(5);
    stack.push(9);
    stack.push(3);
    stack.push(5);
    stack.push(1);
    stack.push(0);
    stack.push(9);
    stack.push(7);

    stack.print("PRE ");
    Stack<Integer> sortedStack = sort(stack);
    sortedStack.print("POST");
  }
}
