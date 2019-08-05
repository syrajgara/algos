package com.shabs.datastructures.bst;

import com.shabs.datastructures.node.Node;

public class BinarySearchTreeSearch {

  public static boolean search(Node root, Integer searchData) {
    if (root == null || searchData == null) {
      return false;
    }

    Integer data = (Integer) root.getData();

    int compare = searchData.compareTo(data);

    if (compare < 0) {
      return search(root.getPrevious(), searchData);
    } else if (compare > 0) {
      return search(root.getNext(), searchData);
    }

    return true;
  }
}
