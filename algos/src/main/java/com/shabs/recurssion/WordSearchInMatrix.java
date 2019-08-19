package com.shabs.recurssion;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * given a matrix (row x col) of chars, find if a word exists in it.
 * the word could wrap horizontally/vertically (no diagonal)
 * also a char cannot be used twice in the word
 */
public class WordSearchInMatrix {

  public boolean findWord(Character[][] input, String word) {

    for (int row = 0; row < input.length; row++) {
      for (int col = 0; col < input[0].length; col++) {
        if (checkWord(input, row, col, word, 0)) {
          return true;
        }
      }
    }

    return false;
  }

  private boolean checkWord(Character[][] input, int row, int col, String word, int wordIndex) {

    if (wordIndex >= word.length()) {
      // we have completed the search
      return true;
    }

    if (row < 0 || col < 0 || row >= input.length || col >= input[0].length) {
      // steping out of the input matrix boundaries
      return false;
    }

    if (input[row][col] != word.charAt(wordIndex)) {
      return false;
    }

    // looks like we got a match, we will drill down further
    // but before that, nullify this char so we dont use it again.
    Character holdToFixAfterReturnFromRecursion = input[row][col];
    input[row][col] = '0';

    // check for next letter
    boolean didNextLetterMatch = checkWord(input, row - 1, col, word, wordIndex + 1)
        || checkWord(input, row + 1, col, word, wordIndex + 1)
        || checkWord(input, row, col - 1, word, wordIndex + 1)
        || checkWord(input, row, col + 1, word, wordIndex + 1);

    // fix the matrix before getting out of this recursion - whether we were successful or not.
    // fix needed to try out with a different starting point (from findWord)
    input[row][col] = holdToFixAfterReturnFromRecursion;

    return didNextLetterMatch;
  }

  private Character[][] getInput() {
    Character[][] input = {
        {'A', 'B', 'C', 'E'},
        {'S', 'F', 'C', 'S'},
        {'A', 'D', 'E', 'E'}
    };

    return input;
  }

  @Test
  public void test1() {
    String word = "ABCCED";
    boolean expected = true;

    boolean actual = findWord(getInput(), word);
    Assert.assertEquals(actual, expected);
  }

  @Test
  public void test2() {
    String word = "SEE";
    boolean expected = true;

    boolean actual = findWord(getInput(), word);
    Assert.assertEquals(actual, expected);
  }

  @Test
  public void test3() {
    String word = "ABCB";
    boolean expected = false;

    boolean actual = findWord(getInput(), word);
    Assert.assertEquals(actual, expected);
  }
}
