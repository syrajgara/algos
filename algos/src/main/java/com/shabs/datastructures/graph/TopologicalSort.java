package com.shabs.datastructures.graph;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a Di-Graph, topological sort the vertices.
 * <p>
 * Consider classes in a university, they need to be ordered so that pre-req are done before others.
 * <p>
 * List of vertices, with its adjacent vertices.
 * Pick first vertex in the list, and mark it.
 * Do a DFS on its adjacent vertices.
 * When a vertex doesnt have any adjacent node, add it a stack
 * continue till all vertices are marked.
 * <p>
 * the stack gives you the reverse topological order
 */
public class TopologicalSort {

  public List<GraphNode<Integer>> solveIteratively(List<GraphNode<Integer>> nodes) {
    List<GraphNode<Integer>> outputNodes = new ArrayList<>();

    Stack<GraphNode<Integer>> s = new Stack<>();

    for (GraphNode<Integer> gn : nodes) {
      s.push(gn);
    }

    while (!s.empty()) {
      if (s.peek().isVisited()) {
        GraphNode<Integer> gn = s.pop();
        if (!outputNodes.contains(gn)) {
          outputNodes.add(gn);
        }

        continue;
      }

      s.peek().visited = true;

      for (GraphNode<Integer> gn : s.peek().adjacentNodes) {
        if (!gn.isVisited()) {
          s.push(gn);
        }
      }
    }

    return outputNodes;
  }

  public List<GraphNode<Integer>> solveRecursively(List<GraphNode<Integer>> nodes) {
    List<GraphNode<Integer>> outputNodes = new ArrayList<>();

    for (GraphNode<Integer> gn : nodes) {
      dfs(gn, outputNodes);
    }

    return outputNodes;
  }

  private void dfs(GraphNode<Integer> gn, List<GraphNode<Integer>> outputNodes) {

    if (gn.isVisited()) {
      return;
    }

    gn.visited = true;

    for (GraphNode<Integer> agn : gn.adjacentNodes) {
      dfs(agn, outputNodes);
    }

    outputNodes.add(gn);
  }

  @Test
  public void testIteratively() {
    TopologicalSort ts = new TopologicalSort();
    printNodes("testIteratively", ts.solveIteratively(getNodes()));
    //testRecursively :  5 <-- 6 <-- 3 <-- 4 <-- 2 <-- 1 <--|
  }

  @Test
  public void testRecursively() {
    TopologicalSort ts = new TopologicalSort();
    printNodes("testRecursively", ts.solveRecursively(getNodes()));

    //testRecursively :  5 <-- 3 <-- 2 <-- 6 <-- 1 <-- 4 <--|
  }

  private List<GraphNode<Integer>> getNodes() {
    GraphNode<Integer> gn1 = new GraphNode<>(1);
    GraphNode<Integer> gn2 = new GraphNode<>(2);
    GraphNode<Integer> gn3 = new GraphNode<>(3);
    GraphNode<Integer> gn4 = new GraphNode<>(4);
    GraphNode<Integer> gn5 = new GraphNode<>(5);
    GraphNode<Integer> gn6 = new GraphNode<>(6);

    List<GraphNode<Integer>> nodes = new ArrayList<>();
    nodes.add(gn1);
    nodes.add(gn2);
    nodes.add(gn3);
    nodes.add(gn4);
    nodes.add(gn5);
    nodes.add(gn6);

    // 1 -> 2 -> 3 -> 5
    // 1 -> 6 -> 5
    // 4 -> 3 -> 5

    gn1.addAdjacentNode(gn2);
    gn2.addAdjacentNode(gn3);
    gn3.addAdjacentNode(gn5);
    gn4.addAdjacentNode(gn3);
    gn1.addAdjacentNode(gn6);
    gn6.addAdjacentNode(gn5);

    return nodes;
  }

  private void printNodes(String prefix, List<GraphNode<Integer>> sortedNodes) {
    System.out.print("\n" + prefix + " : ");
    for (GraphNode<Integer> gn : sortedNodes) {
      System.out.print(" " + gn.data + " <--");
    }
    System.out.println("|");
  }
}
