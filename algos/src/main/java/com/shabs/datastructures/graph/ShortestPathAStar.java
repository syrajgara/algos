package com.shabs.datastructures.graph;

/**
 * http://theory.stanford.edu/~amitp/GameProgramming/AStarComparison.html
 * <p>
 * A* algo is to find the shortest path and is a combination of Dijkstra's shortest path
 * and greedy Best First Search algos.
 * In addition to the cost function as used in dijkstra's algo,
 * it also uses a heuristic function which is low when travelling towards the sink.
 * <p>
 * g(n) = c(n) + h(n);
 * - when h(n)=0 this acts as dijtstra's algo
 * - when c(n)=0 this acts as best first search algo
 * <p>
 * finding a good heuristic function is the challenge!!
 */
public class ShortestPathAStar {

}
