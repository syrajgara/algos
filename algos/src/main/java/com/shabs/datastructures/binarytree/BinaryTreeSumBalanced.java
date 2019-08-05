package com.shabs.datastructures.binarytree;

import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * For a sum balanced BT, the sum of left+right node should be equal to the parent node for all nodes
 * <p>
 *       40
 *   30      10
 * 10  20   2  8
 */
public class BinaryTreeSumBalanced {

  public static Boolean isSumBalanced(BinaryTreeNode<Integer> binaryTreeNode) {
    if (binaryTreeNode == null) {
      return true;
    }

    if (binaryTreeNode.left == null && binaryTreeNode.right == null) {
      return true; // leaf node
    }

    Integer leftValue = binaryTreeNode.left == null ? 0 : binaryTreeNode.left.value;
    Integer rightValue = binaryTreeNode.right == null ? 0 : binaryTreeNode.right.value;

    if ((leftValue + rightValue) != binaryTreeNode.value) {
      return false;
    }

    return isSumBalanced(binaryTreeNode.left) && isSumBalanced(binaryTreeNode.right);
  }

  @Test
  public void leafNodeBalanced() {
    BinaryTreeNode<Integer> root = new BinaryTreeNode<>();
    root.value = 40;

    Boolean result = BinaryTreeSumBalanced.isSumBalanced(root);
    assertThat(result, is(true));
  }

  @Test
  public void completeTreeBalanced() {
    BinaryTreeNode<Integer> root = new BinaryTreeNode<>();
    root.value = 40;

    root.left = new BinaryTreeNode<>();
    root.left.value = 10;

    root.left.left = new BinaryTreeNode<>();
    root.left.left.value = 2;

    root.left.right = new BinaryTreeNode<>();
    root.left.right.value = 8;

    root.right = new BinaryTreeNode<>();
    root.right.value = 30;

    Boolean result = BinaryTreeSumBalanced.isSumBalanced(root);
    assertThat(result, is(true));
  }

  @Test
  public void completeTreeTopUnBalanced() {
    BinaryTreeNode<Integer> root = new BinaryTreeNode<>();
    root.value = 40;

    root.left = new BinaryTreeNode<>();
    root.left.value = 10;

    root.left.left = new BinaryTreeNode<>();
    root.left.left.value = 2;

    root.left.right = new BinaryTreeNode<>();
    root.left.right.value = 8;

    root.right = new BinaryTreeNode<>();
    root.right.value = 28;

    Boolean result = BinaryTreeSumBalanced.isSumBalanced(root);
    assertThat(result, is(false));
  }

  @Test
  public void completeTreeBottomUnBalanced() {
    BinaryTreeNode<Integer> root = new BinaryTreeNode<>();
    root.value = 40; //balanced

    root.left = new BinaryTreeNode<>();
    root.left.value = 10; // unbalanced

    root.left.left = new BinaryTreeNode<>();
    root.left.left.value = 2;

    root.left.right = new BinaryTreeNode<>();
    root.left.right.value = 2;

    root.right = new BinaryTreeNode<>();
    root.right.value = 30;

    Boolean result = BinaryTreeSumBalanced.isSumBalanced(root);
    assertThat(result, is(false));
  }

  @Test
  public void leftNodeOnlyBalanced() {
    BinaryTreeNode<Integer> root = new BinaryTreeNode<>();
    root.value = 40;

    root.left = new BinaryTreeNode<>();
    root.left.value = 40;

    root.left.left = new BinaryTreeNode<>();
    root.left.left.value = 10;

    root.left.right = new BinaryTreeNode<>();
    root.left.right.value = 30;

    Boolean result = BinaryTreeSumBalanced.isSumBalanced(root);
    assertThat(result, is(true));
  }

  @Test
  public void leftNodeOnlyUnBalanced() {
    BinaryTreeNode<Integer> root = new BinaryTreeNode<>();
    root.value = 50;

    root.left = new BinaryTreeNode<>();
    root.left.value = 40;

    root.left.left = new BinaryTreeNode<>();
    root.left.left.value = 10;

    root.left.right = new BinaryTreeNode<>();
    root.left.right.value = 30;

    Boolean result = BinaryTreeSumBalanced.isSumBalanced(root);
    assertThat(result, is(false));
  }

  @Test
  void leftNodeNullBalanced() {
    BinaryTreeNode<Integer> root = new BinaryTreeNode<>();
    root.value = 40;

    root.right = new BinaryTreeNode<>();
    root.right.value = 40;

    Boolean result = BinaryTreeSumBalanced.isSumBalanced(root);
    assertThat(result, is(true));
  }
}
