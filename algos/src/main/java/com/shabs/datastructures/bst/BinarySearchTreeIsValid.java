package com.shabs.datastructures.bst;

import com.shabs.datastructures.node.Node;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * isBinarySearchTree
 * - every node needs to be between min and max value,
 * since a left node will have upper limit based on parent node,
 * but this left node could be on the right of its grandparent node - which dictates the min value
 * - opposite for the right node
 */
public class BinarySearchTreeIsValid {

  public boolean isBinarySearchTree(Node<Integer> rootNode, int minValue, int maxValue) {
    if (rootNode == null) {
      // leaf/sentinel node
      return true;
    }
    if (rootNode.getData() < minValue || rootNode.getData() > maxValue) {
      return false;
    }

    // left is < current and > previous min
    // right is > current and < previous max
    return isBinarySearchTree(rootNode.getPrevious(), minValue, rootNode.getData())
        && isBinarySearchTree(rootNode.getNext(), rootNode.getData(), maxValue);
  }

  @Test
  public void validBST() {
    Integer[] sortedArray = {1, 2, 3, 4, 6, 7, 8};
    List<Integer> sortedList = new ArrayList<>(Arrays.asList(sortedArray));

    BinarySearchTree<Integer> bst = new BinarySearchTree(sortedList);

    boolean isValid = isBinarySearchTree(bst.root, Integer.MIN_VALUE, Integer.MAX_VALUE);

    Assert.assertEquals(isValid, true);
  }

  @Test
  public void invalidBST() {
    Node<Integer> root = new Node<>(10);

    Node<Integer> right = new Node<Integer>(20);
    root.setNext(right);

    Node<Integer> left = new Node<Integer>(5);
    root.setPrevious(left);

    left.setPrevious(new Node<>(1));
    left.setNext(new Node<>(3)); // INVALID

    boolean isValid = isBinarySearchTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE);

    Assert.assertEquals(isValid, false);
  }
}
