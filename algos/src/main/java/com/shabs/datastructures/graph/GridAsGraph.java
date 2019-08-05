package com.shabs.datastructures.graph;

/**
 * A grid (n * m) can also be represented as a graph,
 * with edge going from one cell of the grid to adjacent cell of the grid.
 * <p>
 * http://www.redblobgames.com/pathfinding/a-star/implementation.html#sec-2-1
 * <p>
 * You dont have to keep track of each adjacentEdge, instead calculate the edge on the fly.
 * Caveat: edge cost/weight of these edges should be calculatable on the fly
 * ex1. all weight is 1 or Integer.MAX_VALUE
 * ex2. store level on each cell and edge weight is difference between the level of each cell.
 * consider a topology map, level would be altitude, and cost will be difference of altitude.
 */
public class GridAsGraph {
}
