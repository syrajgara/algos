package com.shabs.datastructures.binarytree;

import com.shabs.datastructures.queue.Queue;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * serialize and deserialize a BT
 *
 * - Use char X for the null nodes, use ',' to separate values
 * - do a pre-order flattening/serialization of the tree into a String
 * - to deserialize, tokenize and put them in a Queue
 * - read node from queue
 * - recurse for left node
 * - recurse for right node
 */
public class BinaryTreeSerializer {

  private BinaryTreeNode<String> deserializeBT(String input) {
    Queue<String> q = new Queue<>();
    for (String s : input.split(",")) {
      q.enqueue(s);
    }

    return deserialize(q);
  }

  private BinaryTreeNode<String> deserialize(Queue<String> q) {
    String currVal = q.dequeue().getData();

    if (currVal.equals("x")) {
      return null;
    }

    BinaryTreeNode<String> bt = new BinaryTreeNode<>(currVal);
    bt.left = deserialize(q);
    bt.right = deserialize(q);

    return bt;
  }

  private void serialize(BinaryTreeNode<String> bt, StringBuffer sb) {
    if (bt == null) {
      sb.append("x");
    } else {
      sb.append(bt.value);
      serialize(bt.left, sb);
      serialize(bt.right, sb);
    }
  }

  @Test
  public void test() {

    String input = "1,2,3,x,x,4,x,x,5,6,x,x,x"; // pre-order

    BinaryTreeNode<String> bt = deserializeBT(input);

    StringBuffer sb = new StringBuffer();
    serialize(bt, sb);

    Assert.assertEquals(sb.toString(), input.replaceAll(",",""));
  }
}
