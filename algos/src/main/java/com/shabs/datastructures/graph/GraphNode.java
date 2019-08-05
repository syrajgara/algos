package com.shabs.datastructures.graph;

import java.util.ArrayList;
import java.util.List;

public class GraphNode<T> {
  private static int DEFAULT_WEIGHT = 1;

  public T data;
  public boolean visited = false;

  public boolean isVisited() {
    return visited;
  }

  public GraphNode<T> visitedFrom;
  public int distance = Integer.MAX_VALUE;

  public List<GraphEdge<T>> edges = new ArrayList<>();
  public List<GraphNode<T>> adjacentNodes = new ArrayList<>();

  public GraphNode(T data) {
    this.data = data;
  }

  public void addAdjacentNode(GraphNode<T> gn) {
    adjacentNodes.add(gn);
  }

  public GraphNode addNode(T data) {
    return addNode(data, DEFAULT_WEIGHT);
  }

  public GraphNode addNode(T data, Integer weight) {
    // create new node
    GraphNode<T> newNode = new GraphNode<T>(data);

    return addNode(newNode, weight);
  }

  public GraphNode addNode(GraphNode<T> node) {
    return addNode(node, DEFAULT_WEIGHT);
  }

  public GraphNode addNode(GraphNode<T> node, Integer weight) {
    // tie the two nodes !!

    GraphEdge<T> edgeNode1ToNode2 = new GraphEdge<>(this, node, weight);
    this.edges.add(edgeNode1ToNode2);

    GraphEdge<T> edgeNode2ToNode1 = new GraphEdge<>(node, this, weight);
    node.edges.add(edgeNode2ToNode1);

    return node;
  }

  public void printPathToThisNode() {
    GraphNode<T> currentNode = this;
    while (currentNode.visitedFrom != null) {
      System.out.print(currentNode.data + " ");
      currentNode = currentNode.visitedFrom;
    }

    System.out.println(currentNode.data + " ");
  }
}
