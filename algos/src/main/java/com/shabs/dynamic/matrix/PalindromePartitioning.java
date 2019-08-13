package com.shabs.dynamic.matrix;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


import org.testng.annotations.Test;

/**
 * MATRIX isPalindromeAtThisPosition boolean [n] [n]
 * MATRIX cost int [n] [n]
 * init - diagonal to true and 0
 *
 * Partition / Cut the given string into palindromes.
 * "abaxyx" will need to be cut into "aba" and "xyx"
 * "abaxyxmnme" is cut into "aba 1 xyx 2 mnm 3 e" - where number indicate the cuts.
 * <p>
 * if the string is a palindrome at position i and j, no cutting needed and hence cost = 0
 * every cut will increase cost by 1.
 * <p>
 * create nxn matrix to store if a string is palindrome at that location
 * create another nxn matrix to store the cost at each of these location
 * <p>
 * first calc a nxn matrix which marks whether string is palindrome at that location.
 * - a char is palindrome to itself so, put TRUE and cost 0 for cells where i==j
 * - now check if every 2 char string is palindrome,
 * - then every 3 char string ...
 * - so on till every n char string
 * <p>
 * then, calc cost for each of these cells
 * - if palindrome at that location cost is zero
 * - else mark cost as MAX_VALUE and minimize cost for every cut between (i,j) ... for every cut add 1 to cost
 * - ie. (i -> i CUT i+1 -> j)
 * - (i -> i+1 CUT i+2 -> j) and so on till
 * - (i -> j-1 CUT j -> j)
 */
public class PalindromePartitioning {

  private int minCut(String input) {
    int len = input.length();

    boolean[][] isPalindromeAtThisPosition = new boolean[len][len];
    int[][] cost = new int[len][len];

    // fill in single char palindrome
    for (int i = 0; i < len; i++) {
      // single char - a char is palindrome to itself
      isPalindromeAtThisPosition[i][i] = true;
      cost[i][i] = 0;
    }

    // fill in for multiple char - starting from 2 char length to len char length
    for (int chars = 2; chars <= len; chars++) {
      // first loop word size is going to be 2 chars,
      // keep taking 2 chars starting from 0th index
      // all the way upto len-2 chars
      // for 2 char string, check (0==1) (1==2) ... (len-2==len-1)
      // for 3 char string, check (0==2 and 1 palin 1) ...
      // for 4 char string, check (0==3 and 1 palin 2) ...
      // and so on

      // row is the start char, col is the end char
      for (int row = 0; row <= len - chars; row++) {
        // row is the left most char of the string we are considering
        // col is the right most char ...
        int col = row + chars - 1;

        // row and col should match
        isPalindromeAtThisPosition[row][col] = (input.charAt(row) == input.charAt(col));

        if (chars > 2 && isPalindromeAtThisPosition[row][col]) {
          // in addition - the center string should be palindrome
          // ex: "aba" - if i`th and j`th char are same
          // and the string between them is palindrom
          // then this fragment of string is palindrom
          isPalindromeAtThisPosition[row][col] = isPalindromeAtThisPosition[row + 1][col - 1];
        }

        // set costs
        if (isPalindromeAtThisPosition[row][col]) {
          cost[row][col] = 0;
        } else {
          cost[row][col] = Integer.MAX_VALUE;

          // if this fragment is not palindrome
          // lets see if we can CUT this fragment to make multiple palindrome
          // out of this fragment
          for (int cutAt = row; cutAt < col; cutAt++) {
            cost[row][col] = Math.min( cost[row][col],
                                       1 + cost[row][cutAt] + cost[cutAt + 1][col]);
          }
        }
      }

      printCost(input, cost);
    }

    // cost on the last char is the min cuts needed to break the string into palindromes
    return cost[0][len - 1];
  }

  private void printCost(String input, int[][] cost) {
    System.out.println("\n  a b a x y x m n m e");
    for (int row = 0; row < cost.length; row++) {
      System.out.print(input.charAt(row) + " ");
      for (int col = 0; col < cost[0].length; col++) {
        if (row <= col) {
          System.out.print(cost[row][col] + " ");
        } else {
          System.out.print("- ");
        }
      }
      System.out.println();
    }
  }

  @Test
  public void minCut() {
    String input = "abaxyxmnme";
    int expected = 3;

    int actualCuts = minCut(input);

    assertThat(actualCuts, is(expected));
  }
}
