package com.shabs.datastructures.graph;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * GREEDY Algo - since picks node with least distance, to progress further.
 *
 *  Given  an  n x n  grid  with  a  person  and  obstacles,  how  would  you  find  a  path  for  the
 *  person  to  a  particular  destination?   The  person  is  permitted  to  move  left,  right,  up,  and  down.
 *
 * <p>
 * will find *A* shortest path if *NON-NEGATIVE* edges.
 * <p>
 * Dijkstra’s algorithm is guaranteed to find *a* shortest path from the starting point to the goal,
 * as long as the edges have *non-negative* cost.
 *
 * <p>
 * http://theory.stanford.edu/~amitp/GameProgramming/AStarComparison.html
 * <p>
 * - use priority queue, to get the node with smallest distance
 * - add zero cost to the source and add it to the priority queue
 * - pick smallest from priority queue and mark visited
 * - find all adjacent nodes and update cost to that node from source
 * - add them to the priority queue, for exploration
 * - loop2 - find the one with least cost and consider that to be the source now.
 */
public class ShortestPathDijkstra {
  public int shortestPath(GraphNode<Integer> sourceNode, GraphNode<Integer> sinkNode) {

    PriorityQueue<GraphNode<Integer>> priorityQueue =
        new PriorityQueue<>(Comparator.comparingInt(o -> o.distance));

    sourceNode.distance = 0; // default is Integer.MAX_VALUE, so root has to be converted to zero.
    priorityQueue.add(sourceNode);

    while (!priorityQueue.isEmpty()) {
      GraphNode<Integer> currentNode = priorityQueue.remove();

      if (currentNode.equals(sinkNode)) {
        break;
      }

      // as soon as we work on a node we mark it visited.
      currentNode.visited = true;

      // distance on nodes will be updated multiple times from different edges/nodes,
      // but once a node is picked from the priority queue, it would be at smallest distance

      for (GraphEdge<Integer> edge : currentNode.edges) {
        // consider only nodes that havent been visited yet
        // and distance from current node is better than its distance from some other node
        if (!edge.toNode.visited
            && edge.toNode.distance > currentNode.distance + edge.weight) {
          // update the distance
          edge.toNode.distance = currentNode.distance + edge.weight;
          edge.toNode.visitedFrom = currentNode;

          // if not in priority queue add it
          // (if we add it without the check, same node might exist twice - with same priorities!!)
          if (!priorityQueue.contains(edge.toNode)) {
            priorityQueue.add(edge.toNode);
          }
        }
      }
    }

    // after the graph traversal, we should have shortest distance at the sink node.
    return sinkNode.distance;
  }

  @Test
  public void shortestPath() {
    ShortestPathDijkstra shortestPathDijkstra = new ShortestPathDijkstra();

    GraphNode<Integer> sourceNode = new GraphNode<>(1);
    GraphNode<Integer> sinkNode = buildGraphAt(sourceNode);

    int shortestDistance = shortestPathDijkstra.shortestPath(sourceNode, sinkNode);
    int expectedShortestDistance = 8;

    Assert.assertEquals(shortestDistance, expectedShortestDistance);

    sinkNode.printPathToThisNode();
  }

  private GraphNode<Integer> buildGraphAt(GraphNode<Integer> sourceNode) {
    // ### Setup the Graph

    //           -> N2 -> N5,N6 -> N11
    // ROOT NODE -> N3 -> N7,N8 -> N11
    //           -> N4 -> N9,N10 -> N11

    GraphNode<Integer> node2 = sourceNode.addNode(2, 2);
    GraphNode<Integer> node3 = sourceNode.addNode(3, 3);
    GraphNode<Integer> node4 = sourceNode.addNode(4, 4);

    GraphNode<Integer> node5 = node2.addNode(5, 1);
    GraphNode<Integer> node6 = node2.addNode(6, 1);

    GraphNode<Integer> node7 = node3.addNode(7, 1);
    GraphNode<Integer> node8 = node3.addNode(8, 1);

    GraphNode<Integer> node9 = node4.addNode(9, 1);
    GraphNode<Integer> node10 = node4.addNode(10, 1);

    GraphNode<Integer> node11 = new GraphNode<>(11);
    node11.addNode(node5, 5);
    node11.addNode(node6, 6);
    node11.addNode(node7, 7);
    node11.addNode(node8, 8);
    node11.addNode(node9, 9);
    node11.addNode(node10, 10);

    return node11;
  }
}
