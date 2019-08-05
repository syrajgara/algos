package com.shabs.dynamic.min_max_imize;

import org.testng.annotations.Test;

/**
 * Independent set =>> Adjacent vertices cannot be part of the solution.
 *
 * Find a max weight independent set given a graph with v vertices.
 * <p>
 * keep track of weight at each vertex
 * use zeroth index to track when no vertex weight is added
 * <p>
 * calc weight at a vertex, by adding its weight at previous to previous vertex
 * or just using its previous vertex's weight. (ie. ignore its weight)
 * <p>
 * once weight at each vertex calculated as above,
 * start with the last vertex and back to first (since last has the largest weight)
 */
public class MaxWeightIndependentSet {

  private void calculate(int[] vertices, int[] weights) {
    // track weights of sets, index = +1 of vertices, zeroth index used when no weights selected
    int[] calculatedWeights = new int[vertices.length + 1];

    calculatedWeights[0] = 0; // base case when no vertex selected

    // weight when 1st vertex selected
    calculatedWeights[1] = weights[0];

    for (int vertexIndex = 1; vertexIndex < vertices.length; vertexIndex++) {
      calculatedWeights[vertexIndex + 1] = Math.max(
          // if we ignore current weight, we can use previous weight
          calculatedWeights[vertexIndex],
          // if we use current weight, we need to pick weight at alternate previous
          calculatedWeights[vertexIndex - 1] + weights[vertexIndex] // add current weight
      );
    }

    printSolution(vertices, calculatedWeights);
  }

  private void printSolution(int[] vertices, int[] calculatedWeights) {

    System.out.println("\nMAX WEIGHT = " + calculatedWeights[vertices.length]);
    System.out.print("Solution Vertices Number: ");

    int vertexIndex = vertices.length - 1;
    int calculatedWeightIndex = vertexIndex + 1; // weights are shifted right by one due to no-vertex zero case.

    while (vertexIndex >= 0) {
      if (calculatedWeights[calculatedWeightIndex] == calculatedWeights[calculatedWeightIndex - 1]) {
        // this vertex was not adding value - skip it

      } else {
        // current vertex is giving better weight - use it
        System.out.print(vertices[vertexIndex]);
        System.out.print("[" + calculatedWeights[calculatedWeightIndex] + "] ");
        // since this vertexIndex contributed,
        // its previous should be ignored to get independant set, skip it
        vertexIndex--;
        // and skip previous's weight as well
        calculatedWeightIndex--;
      }

      // previous vertex (or previous to previous, if previous was skipped above already!!)
      vertexIndex--;
      calculatedWeightIndex--;
    }
  }

  @Test
  public void calculate() {
                    //0  1  2  3  4  5  6
    int[] vertices = {1, 2, 3, 4, 5, 6, 7}; // expected 6,4,1
    int[] weights =  {6, 1, 3, 5, 3, 8, 4};

    calculate(vertices, weights);
  }
}
