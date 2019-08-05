package com.shabs.dynamic.matrix;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * MATRIX [m+1] [n+1]
 * init mat[row][0] = row, mat[0][col] = col
 *
 * Given 2 words, find the minimum steps required to convert word 1 to word 2.
 * To convert you can:
 * - add a char
 * - delete a char
 * - replace a char
 * <p>
 * - word1 == WOMAN
 * - word2 == MAN
 * <p>
 * i 0    1         2         3
 * j      Null M         A         N
 * 0 Null 0    1         2         3
 * 1 W    1    (0,1,1)+1 (1,1,2)+1 (2,2,3)+1 ---> MIN(diagonal, left, top)
 * 2 O    2    (1,2,1)+1 (1,2,2)+1 (2,2,3)+1
 * 3 M    3    (2)+0     (2,2,2)+1 (2,3,3)+1
 * 4 A    4    (3,4,2)+1 (2)+0     (3,2,3)+1
 * 5 N    5    (4,5,4)+1 (4,5,2)+1 (2)+0
 * <p>
 * 0th location is used to indicate null value for that word.
 * to get null you have to drop char, each char in other word cost 1 to drop.
 * <p>
 * cost = 1 + MIN of
 * - drop both char -- look at previous row/columm
 * - drop char from word1 -- look at previous col, current row
 * - drop char from word2 -- look at current col, previous row
 */
public class EditDistance {
  private static int calculateDistance(String word1, String word2) {
    int[][] distance = new int[word1.length() + 1][word2.length() + 1];

    for (int row = 0; row <= word1.length(); row++) {
      // setup matrix when word2 is null
      // word1 will be down the rows (first index)
      distance[row][0] = row;
    }

    for (int col = 0; col <= word2.length(); col++) {
      // setup matrix when word1 is null
      // word2 will be across the columns (second index)
      distance[0][col] = col;
    }

    for (int row = 1; row <= word1.length(); row++) // 0 to 3
    {
      for (int col = 1; col <= word2.length(); col++) // 0 to 5
      {
        // char matches on both words, no additional cost, take cost of previous
        if (word1.charAt(row - 1) == word2.charAt(col - 1)) {
          distance[row][col] = distance[row - 1][col - 1];
          continue;
        }

        // take minimum of these 3 and add penalty/cost of 1
        // Drop => Dont use for this iteration
        int distanceDropCharFromBothWords = distance[row - 1][col - 1]; // replace both
        int distanceDropCharFromWord1 = distance[row - 1][col]; // add to word 1 == remove from word 2
        int distanceDropCharFromWord2 = distance[row][col - 1]; // add to word 2 == remove from word 1

        distance[row][col] = 1 + Math.min(Math.min( distanceDropCharFromBothWords,
                                                    distanceDropCharFromWord1),
                                                    distanceDropCharFromWord2);
      }
    }

    return distance[word1.length()][word2.length()];
  }

  @Test
  public void calculateDistance() {
    String word1 = "WOMAN";
    String word2 = "MAN";
    int expected = 2;

    int distance = EditDistance.calculateDistance(word1, word2);
    Assert.assertEquals(distance, expected);
  }
}
