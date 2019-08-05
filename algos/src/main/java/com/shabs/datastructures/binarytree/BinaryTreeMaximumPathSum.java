package com.shabs.datastructures.binarytree;

import com.shabs.datastructures.node.Node;
import com.shabs.datastructures.queue.Queue;

/**
 * For a BT what is the max path sum.
 *
 * Each child node can send the sum to the parent,
 * parent continues with largest child + its own value.
 *
 */
public class BinaryTreeMaximumPathSum {

  public int findMaxPathSum(Node<Integer> node) {
    if (node == null) {
      return 0;
    }

    return node.getData() + Math.max(findMaxPathSum(node.getPrevious()),
                                     findMaxPathSum(node.getNext()));
  }

  public class NodeExtn<T> {
    public Node<T> node;
    public T prevNodeValue;

    public NodeExtn(Node<T> n, T pv) {
      node = n;
      prevNodeValue = pv;
    }
  }

  public int findMaxPathSumIterative(Node<Integer> root) {
    int maxSum = Integer.MIN_VALUE;

    Queue<NodeExtn<Integer>> queue = new Queue<>();
    queue.enqueue(new NodeExtn<>(root, 0));

    while (!queue.isEmpty()) {
      NodeExtn<Integer> currNode = queue.dequeue().getData();
      maxSum = Math.max(maxSum, currNode.prevNodeValue + currNode.node.getData());

      if (currNode.node.getPrevious() != null) {
        queue.enqueue(new NodeExtn<>(currNode.node.getPrevious(), currNode.node.getData()));
      }

      if (currNode.node.getNext() != null) {
        queue.enqueue(new NodeExtn<>(currNode.node.getNext(), currNode.node.getData()));
      }
    }

    return maxSum;
  }
}
