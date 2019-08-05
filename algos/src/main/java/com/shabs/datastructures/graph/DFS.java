package com.shabs.datastructures.graph;

import com.shabs.datastructures.node.Node;
import com.shabs.datastructures.stack.Stack;
import org.testng.annotations.Test;

/**
 * Use STACK
 * <p>
 * Keep note of the ** visited ** nodes, to avoid cycles - we are using a graph not a tree.
 */
public class DFS {
  public static boolean dfs(Stack<GraphNode<Integer>> dfsStack, Integer searchItem) {
    if (dfsStack.isEmpty()) {
      System.out.println("NOT Found ... " + searchItem);
      return false;
    }

    Node<GraphNode<Integer>> node = dfsStack.pop();
    GraphNode<Integer> graphNode = node.getData();

    System.out.println("Searching Node with data .. " + graphNode.data);

    if (graphNode.data.equals(searchItem)) {
      System.out.println("Found ... " + searchItem);
      return true;
    }

    for (GraphEdge<Integer> edge : graphNode.edges) {
      if (!edge.toNode.visited) {
        edge.toNode.visited = true;
        dfsStack.push(edge.toNode);
      }
    }

    return dfs(dfsStack, searchItem);
  }

  @Test
  public void dfs() {
    // ### Search the graph
    GraphNode<Integer> rootNode;
    Stack<GraphNode<Integer>> dfsStack = new Stack<GraphNode<Integer>>();

    // first node - mark visited and add to queue
    rootNode = BFS.getTestGraph();
    rootNode.visited = true;
    dfsStack.push(rootNode);
    DFS.dfs(dfsStack, 10);

    // empty before next test
    dfsStack.clearStack();

    // first node - mark visited and add to queue
    rootNode = BFS.getTestGraph();
    rootNode.visited = true;
    dfsStack.push(rootNode);
    DFS.dfs(dfsStack, 12);
  }
}
