package com.shabs.datastructures.binarytree;

import org.testng.annotations.Test;

/**
 * Given a BT with NULL pointer on each node,
 * populate the pointer to point to the node on its right in a level order
 * <p>
 * 1 -> NULL
 * /  \
 * 2 -> 3 -> NULL
 * / \  / \
 * 4->5->6->7 -> NULL
 * <p>
 * - LEVEL order traversal using QUEUE
 *
 * - recursive, connect node to the one on its right
 * - connect left to right
 * - connect right to its parents-right.left
 * - for ex: to connect 5->6, check if 2->3 and then use 3.left = 6 to connect to 5
 */
public class BinaryTreeLRPointer {

  public class BinaryTreeNodeWithRPointer<T> extends BinaryTreeNode<T> {
    public BinaryTreeNode<T> rightLevelPointer;

    public BinaryTreeNodeWithRPointer(T d) {
      super(d);
    }

    @Override
    public String toString() {
      String l = left != null ? left.toString() : "";
      String r = right != null ? right.toString() : "";
      String rp = rightLevelPointer != null ? " > " + rightLevelPointer.value : "";

      return " ( " + value + rp + l + r + " )";
    }
  }

  private void rightPointer(BinaryTreeNodeWithRPointer<Integer> n) {
    if (n.left != null && n.left instanceof BinaryTreeNodeWithRPointer) {
      BinaryTreeNodeWithRPointer nl = (BinaryTreeNodeWithRPointer) n.left;
      nl.rightLevelPointer = n.right;

      rightPointer(nl);
    }

    if (n.right != null && n.right instanceof BinaryTreeNodeWithRPointer) {
      BinaryTreeNodeWithRPointer nr = (BinaryTreeNodeWithRPointer) n.right;

      if (n.rightLevelPointer != null) {
        nr.rightLevelPointer = n.rightLevelPointer.left;
      }

      rightPointer(nr);
    }
  }

  @Test
  public void test() {
    BinaryTreeNodeWithRPointer<Integer> n1 = new BinaryTreeNodeWithRPointer<>(1);
    BinaryTreeNode<Integer> n2 = new BinaryTreeNodeWithRPointer<>(2);
    BinaryTreeNode<Integer> n3 = new BinaryTreeNodeWithRPointer<>(3);
    BinaryTreeNode<Integer> n4 = new BinaryTreeNodeWithRPointer<>(4);
    BinaryTreeNode<Integer> n5 = new BinaryTreeNodeWithRPointer<>(5);

    n1.left = n2;
    n1.right = n3;

    n2.left = n4;
    n2.right = n5;

    System.out.println(n1);
    rightPointer(n1);
    System.out.println(n1);
  }
}
