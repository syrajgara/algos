package com.shabs.backtracking;

import com.shabs.datastructures.graph.GraphEdge;
import com.shabs.datastructures.graph.GraphNode;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * GRAPH - No 2 adjacent nodes in a graph should be of the same color
 *
 * N-Colorable where N=2 colors, is poly time, whereas N=3 colors, is NP Complete
 * <p>
 * In a graph, no two adjacent node can be of the same color,
 * this algo checks if that is possible for a given graph.
 * <p>
 * loop thru the colors, if color valid for a node - color it
 * based on this, recurse to next node
 * if a node cannot get a particular color, loop to the next color
 * continue till all nodes are colored.
 * or you run out of valid options.
 */
public class NColorableGraph {
  public NColorableGraph() {
  }

  public static boolean checkColorability(int numberOfColor, List<GraphNode<Integer>> nodes, int nodeIndex, Map<Integer, Integer> nodeIndexColorMap) {
    if (nodeIndex == nodes.size()) {
      // we have covered all nodes, print the colors
      printColor(nodeIndexColorMap);
      return true;
    }

    List<Integer> possibleColors = possibleColors(numberOfColor, nodes, nodeIndex, nodeIndexColorMap);

    for (int color : possibleColors) {
      // this is valid color for this index, so add it to the map and recurse through other nodes
      nodeIndexColorMap.put(nodeIndex, color);

      if (checkColorability(numberOfColor, nodes, nodeIndex + 1, nodeIndexColorMap)) {
        // we were able to color all nodes after this node,
        // and we already colored this node so return true
        return true;
      }

      // we could not color all nodes after this one, so try another color for this node
      nodeIndexColorMap.remove(nodeIndex);
    }

    return false;
  }

  private static List<Integer> possibleColors(int numberOfColor, List<GraphNode<Integer>> nodes, int nodeIndex, Map<Integer, Integer> nodeIndexColorMap) {

    List<Integer> possibleColors = new ArrayList<>();
    GraphNode<Integer> currentNode = nodes.get(nodeIndex);

    // color=0 means not colored yet
    for (int color = 1; color <= numberOfColor; color++) {
      boolean canAddThisColor = true;

      for (GraphEdge<Integer> edge : currentNode.edges) {
        int adjacentIndex = nodes.indexOf(edge.toNode);
        if (nodeIndexColorMap.containsKey(adjacentIndex)) {
          int adjacentColor = nodeIndexColorMap.get(adjacentIndex);
          if (color == adjacentColor) {
            // adjacent node already has this color, so we cant apply again
            canAddThisColor = false;
            break;
          }
        }
      }

      if (canAddThisColor) {
        possibleColors.add(color);
      }
    }

    return possibleColors;
  }

  private static void printColor(Map<Integer, Integer> nodeIndexColorMap) {
    System.out.println("KEY=COLOR");
    for (Map.Entry colorEntry : nodeIndexColorMap.entrySet()) {
      System.out.print(colorEntry.getKey() + "=" + colorEntry.getValue() + "; ");
    }
    System.out.println();
  }

  @Test
  public void validColorableGraph() {
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

    GraphNode<Integer> node11 = new GraphNode<Integer>(11);
    node11.addNode(node5);
    node11.addNode(node6);
    node11.addNode(node7);
    node11.addNode(node8);
    node11.addNode(node9);
    node11.addNode(node10);

    int numberOfColor = 2;
    Map<Integer, Integer> nodeIndexColorMap = new HashMap<>();

    List<GraphNode<Integer>> nodes = new ArrayList<>();
    nodes.add(node1);
    nodes.add(node2);
    nodes.add(node3);
    nodes.add(node4);
    nodes.add(node5);
    nodes.add(node6);
    nodes.add(node7);
    nodes.add(node8);
    nodes.add(node9);
    nodes.add(node10);
    nodes.add(node11);


    // FAILURE CASE: this will make node 9 and 10 adjacent and so will force it to be of differnt color,
    // which is not possible - and the test should fail.
    //node9.addNode(node10);


    boolean colorable = NColorableGraph.checkColorability(numberOfColor, nodes, 0, nodeIndexColorMap);
    boolean expectedColorable = true;

    Assert.assertEquals(colorable, expectedColorable);
  }
}
