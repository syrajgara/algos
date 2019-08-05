package com.shabs.datastructures.bst;

import com.shabs.datastructures.node.Node;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Create BST from List of data
 * - divide, use center element as node and create previous and next roots by passing
 * sub-list of data.
 * <p>
 * Adding data to existing BST root
 * - if data > root.data - replace right node with new node after adding data to the right node
 * - if data < root.data - replace left node with new node after adding data to the left node
 * - if data = root.data - ignore, we are not adding duplicates
 * <p>
 * Convert to linked list
 */
public class BinarySearchTree<T> {
  public Node<T> root;

  public BinarySearchTree() {}

  public BinarySearchTree(List<T> list) {
    // TODO needs a sorted list
    // Collections.sort(list); // we need sorted array to create BST

    root = createBST(list, 0, list.size() - 1);
  }

  private Node<T> createBST(List<T> list, int l, int r) {
    if (l > r) {
      return null;
    }

    int m = l + (r - l) / 2;
    Node<T> rootForThisSubList = new Node<>(list.get(m));

    rootForThisSubList.setPrevious(createBST(list, l, m - 1));
    rootForThisSubList.setNext(createBST(list, m + 1, r));

    return rootForThisSubList;
  }

  public static Node<Integer> addData(Node<Integer> bstNode, Integer data) {
    if (bstNode == null) {
      bstNode = new Node<>(data);
      return bstNode;
    }

    if (data > bstNode.getData()) {
      addData(bstNode.getNext(), data);
    } else if (data < bstNode.getData()) {
      addData(bstNode.getPrevious(), data);
    } else {
      // data == bstNode.getData() ... ignore data ... no duplicates
    }

    return bstNode;
  }

  public static Node sucessorNode(Node node) {
    // TODO IMPLEMENT
    return null;
  }

  public static Node predecessorNode() {
    // TODO IMPLEMENT
    return null;
  }

  @Test
  public void test() {
    Integer[] sortedArray = {1, 2, 3, 4, 6, 7, 8};
    List<Integer> sortedList = new ArrayList<>(Arrays.asList(sortedArray));

    BinarySearchTree<Integer> bst = new BinarySearchTree(sortedList);

    BinarySearchTree.addData(bst.root, 9);
    BinarySearchTree.addData(bst.root, 5);
    BinarySearchTree.addData(bst.root, 0);

    System.out.println("search -1: " + BinarySearchTreeSearch.search(bst.root, -1));
    System.out.println("search 2: " + BinarySearchTreeSearch.search(bst.root, 2));
    System.out.println("search 5: " + BinarySearchTreeSearch.search(bst.root, 5));
    System.out.println("search 100: " + BinarySearchTreeSearch.search(bst.root, 100));
  }
}
