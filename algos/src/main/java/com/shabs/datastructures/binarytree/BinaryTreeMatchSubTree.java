package com.shabs.datastructures.binarytree;

import com.shabs.datastructures.node.Node;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Given tree T1, check if tree T2 is a sub-tree of T1
 * <p>
 * first find the node on T1 that matches T2's root, then from that point onwards make sure T2 fits in T1.
 * <p>
 * - first find the node that matches on T1 and T2,
 * - check root node, if doesnt match, do binary search
 * - ie. recursive check check T1.left with T2 and T1.right with T2
 *
 * - once you find the match on root of both trees
 * - match each element of T1 with T2
 * - if T2 becomes null, we have found the subtree
 * - if T1 becomes null, we ran out of nodes on T1, so false
 * - else keep matching T1 left node with T2 left node and same for right
 */
public class BinaryTreeMatchSubTree {
  public static boolean matchSubTree(Node rootTree1, Node rootTree2) {
    if (rootTree1 == null || rootTree2 == null) {
      return false;
    }

    if (rootTree1.getData().equals(rootTree2.getData())
        && matchTree(rootTree1, rootTree2)) {
      // if the root nodes match and corresponding subtrees match
      return true;
    }

    // match T2 with left and right side of T1
    return matchSubTree(rootTree1.getPrevious(), rootTree2)
        || matchSubTree(rootTree1.getNext(), rootTree2);
  }

  private static boolean matchTree(Node rootTree1, Node rootTree2) {
    if (rootTree2 == null) {
      // we reached end of tree2, but tree1 still has elements,
      // which is ok since tree1 is bigger than tree2
      return true;
    }

    if (rootTree1 == null) {
      // we reached end of tree1, but tree2 still has elements,
      // not ok, since tree2 is supposed to be sub-tree of tree1
      return false;
    }

    if (!rootTree1.getData().equals(rootTree2.getData())) {
      // root data dont match
      return false;
    }

    // check if left and right sides match
    return matchTree(rootTree1.getPrevious(), rootTree2.getPrevious())
        && matchTree(rootTree1.getNext(), rootTree2.getNext());
  }

  private Node node1 = new Node(1);
  private Node node2 = new Node(2);
  private Node node3 = new Node(3);
  private Node node4 = new Node(4);
  private Node node5 = new Node(5);
  private Node node6 = new Node(6);
  private Node node7 = new Node(7);

  @BeforeMethod
  public void setup() {
    node1.setPrevious(node2);
    node1.setNext(node3);

    node2.setPrevious(node4);
    node2.setNext(node5);

    node4.setPrevious(node6);
    node4.setPrevious(node7);
  }

  @Test
  public void completeTreeMatch() {
    boolean match = BinaryTreeMatchSubTree.matchSubTree(node1, node1);
    Assert.assertEquals(match, true);
  }

  @Test
  public void subTreeMatch() {
    boolean match = BinaryTreeMatchSubTree.matchSubTree(node1, node4);
    Assert.assertEquals(match, true);
  }

  @Test
  public void subTreeNoMatch() {
    boolean match = BinaryTreeMatchSubTree.matchSubTree(node2, node3);
    Assert.assertEquals(match, false);
  }

  @Test
  public void subTreeBigger() {
    Node node4 = new Node(4);
    Node node6 = new Node(6);
    Node node7 = new Node(7);
    Node node8 = new Node(8);

    node4.setPrevious(node6);
    node4.setPrevious(node7);

    node7.setPrevious(node8);

    boolean match = BinaryTreeMatchSubTree.matchSubTree(node1, node4);
    Assert.assertEquals(match, false);
  }
}
