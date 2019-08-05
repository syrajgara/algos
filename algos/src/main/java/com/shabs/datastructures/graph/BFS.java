package com.shabs.datastructures.graph;

import com.shabs.datastructures.node.Node;
import com.shabs.datastructures.queue.Queue;
import org.testng.annotations.Test;

/**
 * Use QUEUE - take care of CYCLE by keeping visited
 * <p>
 * Keep note of the ** visited ** nodes, to avoid cycles - we are using a graph not a tree.
 */
public class BFS {
  public static boolean bfs(Queue<GraphNode<Integer>> bfsQueue, Integer searchItem) {
    if (bfsQueue.isEmpty()) {
      System.out.println("NOT Found ... " + searchItem);
      return false;
    }

    Node<GraphNode<Integer>> node = bfsQueue.dequeue();
    GraphNode<Integer> graphNode = node.getData();

    System.out.println("Searching Node with data .. " + graphNode.data);

    if (graphNode.data.equals(searchItem)) {
      System.out.println("Found ... " + searchItem);
      return true;
    }

    for (GraphEdge<Integer> edge : graphNode.edges) {
      if (!edge.toNode.visited) {
        edge.toNode.visited = true;
        bfsQueue.enqueue(edge.toNode);
      }
    }

    return bfs(bfsQueue, searchItem);
  }

  @Test
  public void bfs() {
    // ### Search the graph
    GraphNode<Integer> rootNode;
    Queue<GraphNode<Integer>> bfsQueue = new Queue<GraphNode<Integer>>();

    // first node - mark visited and add to queue
    rootNode = getTestGraph();
    rootNode.visited = true;
    bfsQueue.enqueue(rootNode);
    BFS.bfs(bfsQueue, 10);

    // empty before next test
    bfsQueue.drainQueue();

    // first node - mark visited and add to queue
    rootNode = getTestGraph();
    rootNode.visited = true;
    bfsQueue.enqueue(rootNode);
    BFS.bfs(bfsQueue, 12);
  }

  public static GraphNode<Integer> getTestGraph() {
    // ### Setup the Graph

    //    -> N2 -> N5,N6 -> N11
    // N1 -> N3 -> N7,N8 -> N11
    //    -> N4 -> N9,N10 -> N11

    GraphNode<Integer> node1 = new GraphNode<Integer>(1);

    GraphNode<Integer> node2 = node1.addNode(2);
    GraphNode<Integer> node3 = node1.addNode(3);
    GraphNode<Integer> node4 = node1.addNode(4);

    GraphNode<Integer> node5 = node2.addNode(5);
    GraphNode<Integer> node6 = node2.addNode(6);

    GraphNode<Integer> node7 = node3.addNode(7);
    GraphNode<Integer> node8 = node3.addNode(8);

    GraphNode<Integer> node9 = node4.addNode(9);
    GraphNode<Integer> node10 = node4.addNode(10);

        /*
        GraphNode<Integer> node11 = new GraphNode<Integer>(11);
        node11.addNode(node5);
        node11.addNode(node6);
        node11.addNode(node7);
        node11.addNode(node8);
        node11.addNode(node9);
        node11.addNode(node10);
        */

    return node1;
  }
}
