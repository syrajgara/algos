package com.shabs.recurssion;

import com.shabs.datastructures.stack.Stack;
import org.testng.annotations.Test;

/**
 * Three towers, Move from one tower to another, maintain stack order.
 * <p>
 * on every moveDiscs
 * - somehow! move all disc, other than the last that needs to be moved, to buffer
 * - move last to toStack
 * - and then somehow! move all disc on buffer to toStack
 * <p>
 * somehow! ==> call moveDiscs recursively, till no disc to move - in that case unwind call stack.
 */
public class TowerOfHanoi {
  public void moveDiscs(int numberOfDiscsToMove, Stack<Integer> fromStack, Stack<Integer> toStack, Stack<Integer> bufferStack) {
    if (numberOfDiscsToMove == 0) {
      return;
    }

    moveDiscs(numberOfDiscsToMove - 1, fromStack, bufferStack, toStack);
    toStack.push(fromStack.pop());
    moveDiscs(numberOfDiscsToMove - 1, bufferStack, toStack, fromStack);

    print(fromStack, toStack, bufferStack);
  }

  private void print(Stack<Integer> fromStack, Stack<Integer> toStack, Stack<Integer> bufferStack) {
    fromStack.print("fromStack   ");
    toStack.print("toStack     ");
    bufferStack.print("bufferStack ");
    System.out.println();
  }

  @Test
  public void test() {
    Stack<Integer> fromStack = new Stack<>();
    Stack<Integer> toStack = new Stack<>();
    Stack<Integer> bufferStack = new Stack<>();

    //fromStack.push(5);
    //fromStack.push(4);
    fromStack.push(3);
    fromStack.push(2);
    fromStack.push(1);

    int numberOfDiscsToMove = 3;

    TowerOfHanoi towerOfHanoi = new TowerOfHanoi();
    towerOfHanoi.moveDiscs(numberOfDiscsToMove, fromStack, toStack, bufferStack);
  }
}
