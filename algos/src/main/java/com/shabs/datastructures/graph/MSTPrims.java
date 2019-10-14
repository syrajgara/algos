package com.shabs.datastructures.graph;

import java.util.Comparator;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.PriorityQueue;

/**
 * MST - connect all nodes in a graph, to form a tree with minimum edge weight
 * <p>
 * MSTPrims - Greedy
 * - grow single tree
 * - pick lowest weight edge from already seen edges (priority queue)
 *
 * https://stackoverflow.com/questions/14144279/difference-between-prims-and-dijkstras-algorithms
 *
 * <p>
 * - start from one node (source node)
 * - pick edge connected to it, but lowest weight
 * - now pick edge connected to either on of these nodes, pick lowest weight
 * - continue, till all nodes covered
 */
public class MSTPrims {

  private int findMinWeight(GraphNode<Integer> rootNode) {

    PriorityQueue<GraphEdge<Integer>> edgePQ =
        new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));

    checkAndAddToPQ(rootNode, edgePQ);

    int minWeight = 0;

    while (!edgePQ.isEmpty()) {
      GraphEdge<Integer> currentEdge = edgePQ.remove();

      if (currentEdge.fromNode.visited && currentEdge.toNode.visited) {
        continue; // we dont need this edge
      }

      // we are going to use this edge
      currentEdge.print();
      minWeight += currentEdge.weight;

      checkAndAddToPQ(currentEdge.fromNode, edgePQ);
      checkAndAddToPQ(currentEdge.toNode, edgePQ);
    }

    return minWeight;
  }

  private void checkAndAddToPQ(GraphNode<Integer> node, PriorityQueue<GraphEdge<Integer>> edgePQ) {
    if (!node.visited) {
      node.visited = true;

      for (GraphEdge<Integer> ge : node.edges) {
        edgePQ.add(ge);
      }
    }
  }

  @Test
  public void test() {
    GraphNode<Integer> gn1 = new GraphNode(1);
    GraphNode<Integer> gn2 = new GraphNode(2);
    GraphNode<Integer> gn3 = new GraphNode(3);
    GraphNode<Integer> gn4 = new GraphNode(4);
    GraphNode<Integer> gn5 = new GraphNode(5);
    GraphNode<Integer> gn6 = new GraphNode(6);
    GraphNode<Integer> gn7 = new GraphNode(7);
    GraphNode<Integer> gn8 = new GraphNode(8);
    GraphNode<Integer> gn9 = new GraphNode(9);

    gn1.addNode(gn2, 4);
    gn1.addNode(gn3, 8);
    gn2.addNode(gn4, 8);
    gn3.addNode(gn5, 7);
    gn3.addNode(gn6, 1);
    gn4.addNode(gn5, 2);
    gn4.addNode(gn7, 7);
    gn4.addNode(gn8, 4);
    gn5.addNode(gn6, 6);
    gn6.addNode(gn8, 2);
    gn7.addNode(gn8, 14);
    gn7.addNode(gn9, 9);
    gn8.addNode(gn9, 10);

    int expected = 37;
    int actual = findMinWeight(gn1);

    Assert.assertEquals(actual, expected);
  }
}