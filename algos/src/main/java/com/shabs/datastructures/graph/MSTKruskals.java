package com.shabs.datastructures.graph;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * <p>
 * MST - connect all nodes in a graph, to form a tree with minimum edge weight
 * <p>
 * MSTKruskals - Greedy
 * - grow forest, ignore edges that has both end nodes already covered
 * - sort all edges according to weight and then loop thru it
 * <p>
 * - order all edges based on their weight
 * - loop and take one edge at a time
 * - if both nodes of edge already visited/covered ignore it.
 * - else add to the tree or start a new tree
 * - in other words this edge becomes part of MST, count its weight towards final min edge weight
 */
public class MSTKruskals {

  private int findMinWeight(List<GraphEdge<Integer>> edges) {

    int minSpanningWeight = 0;

    // Collections.sort(edges, (o1, o2) -> Integer.compare(o1.weight, o2.weight));
    Collections.sort(edges, Comparator.comparingInt(o -> o.weight));

    for (GraphEdge<Integer> e : edges) {
      if (e.fromNode.visited && e.toNode.visited) {
        continue; // we dont need this edge
      }

      // we are going to use this edge
      e.print();
      minSpanningWeight += e.weight;

      e.fromNode.visited = true;
      e.toNode.visited = true;
    }

    return minSpanningWeight;
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

    GraphEdge<Integer> ge1 = new GraphEdge<>(gn1, gn2, 4);
    GraphEdge<Integer> ge2 = new GraphEdge<>(gn1, gn3, 8);
    GraphEdge<Integer> ge3 = new GraphEdge<>(gn2, gn4, 8);
    GraphEdge<Integer> ge4 = new GraphEdge<>(gn3, gn5, 7);
    GraphEdge<Integer> ge5 = new GraphEdge<>(gn3, gn6, 1);
    GraphEdge<Integer> ge6 = new GraphEdge<>(gn4, gn5, 2);
    GraphEdge<Integer> ge7 = new GraphEdge<>(gn4, gn7, 7);
    GraphEdge<Integer> ge8 = new GraphEdge<>(gn4, gn8, 4);
    GraphEdge<Integer> ge9 = new GraphEdge<>(gn5, gn6, 6);
    GraphEdge<Integer> ge10 = new GraphEdge<>(gn6, gn8, 2);
    GraphEdge<Integer> ge11 = new GraphEdge<>(gn7, gn8, 14);
    GraphEdge<Integer> ge12 = new GraphEdge<>(gn7, gn9, 9);
    GraphEdge<Integer> ge13 = new GraphEdge<>(gn8, gn9, 10);

    List<GraphEdge<Integer>> edges = new ArrayList<>();
    edges.add(ge1);
    edges.add(ge2);
    edges.add(ge3);
    edges.add(ge4);
    edges.add(ge5);
    edges.add(ge6);
    edges.add(ge7);
    edges.add(ge8);
    edges.add(ge9);
    edges.add(ge10);
    edges.add(ge11);
    edges.add(ge12);
    edges.add(ge13);

    int expected = 25;
    int actual = findMinWeight(edges);

    Assert.assertEquals(actual, expected);
  }
}
