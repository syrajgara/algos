package com.shabs.datastructures.graph;

public class GraphEdge<T> {
  public GraphNode<T> fromNode;
  public GraphNode<T> toNode;
  public int weight;

  public GraphEdge(GraphNode<T> fromNode, GraphNode<T> toNode, int weight) {
    this.fromNode = fromNode;
    this.toNode = toNode;
    this.weight = weight;
  }

  public void print() {
    System.out.println(fromNode.data + " <- w=" + weight + " -> " + toNode.data);
  }
}
