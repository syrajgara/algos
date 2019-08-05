package com.shabs.datastructures.binarytree;

import com.shabs.datastructures.stack.Stack;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * check if BT is symmetric.
 * STACK - POP in PAIRS
 *
 * TRUE
 *     1
 *  /    \
 *  2     2
 * / \   / \
 * 3  4 4  3
 * <p>
 * FALSE
 * 1
 * / \
 * 2   2
 * \   \
 * 3    3
 * <p>
 * - Use Stack
 * - push left and right for root node
 * - from there on, always pop in pairs for comparision
 * - check if these poped pairs are equal,
 * - if yes, for each pair, push left.right and right.left - and left.left and right.right
 * - continue the pop/check/push till stack is empty
 */
public class BinaryTreeIsSymmetric {

  private boolean isSymmetric(BinaryTreeNode<Integer> n) {
    Stack<BinaryTreeNode<Integer>> stack = new Stack<>();
    stack.push(n.left);
    stack.push(n.right);

    return checkPairs(stack);
  }

  private boolean checkPairs(Stack<BinaryTreeNode<Integer>> stack) {

    if (stack.isEmpty()) {
      return true;
    }

    BinaryTreeNode<Integer> r = stack.pop().getData();
    BinaryTreeNode<Integer> l = stack.pop().getData();

    if (r == null && l == null) {
      return true;
    }

    if (r == null || l == null) {
      return false;
    }

    if (!r.value.equals(l.value)) {
      return false;
    }

    stack.push(l.left);
    stack.push(r.right);

    stack.push(l.right);
    stack.push(r.left);

    return checkPairs(stack);
  }

  @Test
  public void testSymmetric() {
    BinaryTreeNode<Integer> n1 = new BinaryTreeNode<>(1);
    BinaryTreeNode<Integer> n2 = new BinaryTreeNode<>(2);
    BinaryTreeNode<Integer> n3 = new BinaryTreeNode<>(2);
    BinaryTreeNode<Integer> n4 = new BinaryTreeNode<>(3);
    BinaryTreeNode<Integer> n5 = new BinaryTreeNode<>(4);
    BinaryTreeNode<Integer> n6 = new BinaryTreeNode<>(4);
    BinaryTreeNode<Integer> n7 = new BinaryTreeNode<>(3);

    n1.left = n2;
    n1.right = n3;

    n2.left = n4;
    n2.right = n5;

    n3.left = n6;
    n3.right = n7;

    boolean expected = true;
    boolean actual = isSymmetric(n1);

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void testAsymmetric() {
    BinaryTreeNode<Integer> n1 = new BinaryTreeNode<>(1);
    BinaryTreeNode<Integer> n2 = new BinaryTreeNode<>(2);
    BinaryTreeNode<Integer> n3 = new BinaryTreeNode<>(2);
    BinaryTreeNode<Integer> n4 = new BinaryTreeNode<>(3);
    BinaryTreeNode<Integer> n5 = new BinaryTreeNode<>(3);

    n1.left = n2;
    n1.right = n3;

    n2.left = n4;
    n2.right = n5;

    boolean expected = false;
    boolean actual = isSymmetric(n1);

    Assert.assertEquals(actual, expected);
  }
}
