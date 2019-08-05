package com.shabs.dynamic.min_max_imize;

import org.testng.annotations.Test;

/**
 * - Given a sentence, arrange the words on an n char line such that it can be neatly printed.
 * - no word length will exceed the line length.
 * <p>
 * - for ex1: line1 = 2spaces, line2 = 2spaces -- total spaces = 4
 * - for ex2: line1 = 3spaces, line2 = 1spaces -- total spaces = 4
 * In above examples total spaces in both line is same, but ex1 is better than ex2,
 * <p>
 * Greedy Solution:
 * - fit as many words as possible on current line and proceed,
 * - this might not always give optimal solution.
 * <p>
 * Dynamic Solution:
 * - calculate cost of putting word on current line and minimize the cost
 * - calculate cost (polynomially) as
 * - for ex1: 2^3+2^3=16 --- so this is better than ex2
 * - for ex2: 3^3+1^3=28
 * <p>
 * For example, consider the following string and line width M = 15
 * "Geeks for Geeks presents word wrap problem"
 * <p>
 * Following is the optimized arrangement of words in 3 lines
 * Geeks for Geeks
 * presents word
 * wrap problem
 * <p>
 * http://www.geeksforgeeks.org/dynamic-programming-set-18-word-wrap/
 * Algo - https://www.youtube.com/watch?v=ENyox7kNKeY
 *
 * If you need to track line-breaks need to enhance this.
 */
public class TextJustification {
  private static final int SPACE_LENGTH = 1;

  private static int justify(int lineSize, String[] words, int wordIndex, int remainingLineSize) {

    // if previous word did exact fit, lineSize will be -1, correct that
    int costWastedSpace = (int) Math.pow(remainingLineSize + SPACE_LENGTH, 3);

    int cost = costWastedSpace;

    if (wordIndex >= words.length) {
      return cost; // we have used all the words in the array
    }

    int currentWordLength = words[wordIndex].length();
    int newRemainingLineSize = remainingLineSize - currentWordLength - SPACE_LENGTH;

    if (remainingLineSize == lineSize) {
      // if no word has been put on this line, we have to put the currentWord on this line.
      // else we will keep trying putting this word on a new line in a loop
      cost = justify(lineSize, words, wordIndex + 1, newRemainingLineSize); // current line

    } else if (currentWordLength > remainingLineSize) {
      // no space on this line, we have to put the currentWord on next line
      // whenever we move to next line, add cost of leftover spaces
      cost = costWastedSpace + justify(lineSize, words, wordIndex, lineSize); // next line

    } else {
      // we can either put currentWord on current line or next line - minimize
      int costCurrentLine = justify(lineSize, words, wordIndex + 1, newRemainingLineSize);
      int costNextLine = costWastedSpace + justify(lineSize, words, wordIndex, lineSize);

      cost = Math.min(costCurrentLine, costNextLine);
    }

    return cost;
  }

  @Test
  public void test() {
    String[] words = {"a", "bb", "cc", "dd", "e"};
    int lineSize = 5;

//        String[] words = {"Geeks", "for", "Geeks", "presents", "word", "wrap", "problem"};
//        int lineSize = 15;

    int actualCost = justify(lineSize, words, 0, lineSize);

    System.out.println("COST: " + actualCost); // 35
  }
}
