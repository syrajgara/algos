package com.shabs.datastructures.array;

/**
 There are N children standing in a line. Each child is assigned a rating value.

 You are giving candies to these children subjected to the following requirements:

 1) Each child must have at least one candy.
 2) Children with a higher rating get more candies than their neighbors.

 What is the minimum candies you must give?

 ratings => {2, 1,  3,   5, 6, 2, 1}
 candy   => {1, 1-1=0, 1, 2, 3, 1, 0}
 fix candy   => {2, 1, 2, 3, 4, 2, 1}

 */
public class CandyDistribution {

  public int candy(int[] ratings) {

    int negativeCandy = 1;
    int[] candies = new int[1];

    candies[0] = 1;

    for (int i=1; i<ratings.length; i++) {
      if (ratings[i] < ratings[i-1]) {
        candies[i] = candies[i-1] - 1;
        if (candies[i] > 1) {
          candies[i] = 1;
        }

        // negativeCandy = 1, 0

        // keep track of 0 or negative
        if (candies[i] < negativeCandy) {
          negativeCandy = candies[i];
        }
      } else {
        candies[i] = candies[i-1] + 1;
      }
    }

    // -1, -(-1)+1 to all kids
    // -2, 3 to all

    int addCandyToFixNegatives = -negativeCandy + 1; // 1
    int total = 0;

    for (int j = 0; j < candies.length; j++) {
      candies[j] = candies[j] + addCandyToFixNegatives;

      total += candies[j];
    }

    // O(2n)

    return total;
  }
}
