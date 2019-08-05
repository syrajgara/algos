package com.shabs.datastructures.binarytree;

import com.shabs.datastructures.node.Node;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Given a Binary Tree (not binary search tree), find the common ancestor of 2 nodes.
 * 1. both nodes part of the tree
 * 2. one node not part of the tree
 * <p>
 * On each return, need to return
 * - whether an ancestor was found
 * - OR the node1 or node2 that was the match
 * - or none of the above
 * - so need a struct to hold this.
 * <p>
 * On every recursive call
 * - either inputs are null (reached last node to search)
 * - currentNode matches one of the nodes
 * - else recurse left and right to get individual results
 * - on return if ancestor was found you are done
 * - else if both nodes found on different sides of currentNode, currentNode is the ancestor
 * - else only one node found, check if current node is the other node - in which case this node is the ancestor
 * - else return to previous call stack result from left or right
 */
public class BinaryTreeCommonAncestor {
  public static class Result {
    boolean foundAncestor;

    // if foundAncestor, than the ancestorNode, else one of the node1 or node2 that was found.
    Node node;

    public Result(Node node, boolean foundAncestor) {
      this.node = node;
      this.foundAncestor = foundAncestor;
    }
  }

  public static Result findAncestor(Node currentNode, Node node1, Node node2) {
    // we have reached the end or invalid inputs
    if (currentNode == null || node1 == null || node2 == null) {
      return new Result(null, false);
    }

    // we found ancestor - so return
    Result previousSearchResult = findAncestor(currentNode.getPrevious(), node1, node2);
    if (previousSearchResult.foundAncestor) {
      return previousSearchResult;
    }

    Result nextSearchResult = findAncestor(currentNode.getNext(), node1, node2);
    if (nextSearchResult.foundAncestor) {
      return nextSearchResult;
    }

    if (previousSearchResult.node != null && nextSearchResult.node != null) {
      // node1 and node2 on different side of root, so currentNode is ancestor
      return new Result(currentNode, true);
    }

    // one node matched, lets see if current node becomes the ancestor
    // we got a match on previousSearchResult or nextSearchResult
    if (previousSearchResult.node != null || nextSearchResult.node != null) {
      // we got a match on currentNode
      if (currentNode.equals(node1) || currentNode.equals(node2)) {
        // node1 or node2 itself is the common ancestor
        return new Result(currentNode, true);
      }

      // return the match, this currentNode is not making any change
      if (previousSearchResult.node != null) {
        return previousSearchResult;
      }
      return nextSearchResult;
    }

    // wont find ancestor in this iteration, but maybe we will find one node match
    if (currentNode.equals(node1) || currentNode.equals(node2)) {
      // currentNode is either node1 or node2
      return new Result(currentNode, false);
    }

    // no matches, return null/false
    return new Result(null, false);
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

    node5.setPrevious(node7);
  }

  @Test
  public void rootIsCommonAncestor() {
    BinaryTreeCommonAncestor.Result result = BinaryTreeCommonAncestor.findAncestor(node1, node5, node3);
    Assert.assertEquals(result.foundAncestor, true);
    Assert.assertEquals(result.node, node1);
  }

  @Test
  public void hasCommonAncestor() {
    BinaryTreeCommonAncestor.Result result = BinaryTreeCommonAncestor.findAncestor(node1, node6, node7);
    Assert.assertEquals(result.foundAncestor, true);
    Assert.assertEquals(result.node, node2);
  }

  @Test
  public void ancestorIsOneOfTheNode() {
    BinaryTreeCommonAncestor.Result result = BinaryTreeCommonAncestor.findAncestor(node1, node2, node7);
    Assert.assertEquals(result.foundAncestor, true);
    Assert.assertEquals(result.node, node2);
  }

  @Test
  public void oneNodeNotPartOfTree() {
    BinaryTreeCommonAncestor.Result result = BinaryTreeCommonAncestor.findAncestor(node1, node2, new Node(0));
    Assert.assertEquals(result.foundAncestor, false);
    Assert.assertEquals(result.node, node2);
  }
}
