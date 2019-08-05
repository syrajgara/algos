package com.shabs.datastructures.string;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * reverse the sentence
 * <p>
 *   IN-PLACE Solution
 *   - O(n) - reverse the whole string, swap first char with last and so forth
 *   - O(n) - now go thru from the first char, on finding space, reverse the previous word
 *
 * EXTRA SPACE
 * - collect words from the end of the string - using StringBuffer
 * - when you find space - add word to outputSentence and add space - another StringBuffer
 * - when done - you will have reversed string
 * <p>
 * OR
 * - from start, collect words and put them in a Stack
 * - once done pop words to form the sentence in reverse order.
 */
public class ReverseWordsOfSentence {
  private String reverse(String sentence) {
    StringBuffer reverseSentence = new StringBuffer();
    String currentWord = "";

    int n = sentence.length() - 1;

    for (int i = n; i >= 0; i--) {
      if (sentence.charAt(i) == ' ') {
        reverseSentence.append(currentWord);
        reverseSentence.append(" ");
        currentWord = ""; // reset
      } else {
        currentWord = sentence.charAt(i) + currentWord;
      }
    }

    reverseSentence.append(currentWord);

    return reverseSentence.toString();
  }

  @Test
  public void reverse() {
    String sentence = "My name is   Shabbir !!!";
    String expected = "!!! Shabbir   is name My";

    String reverseSentence = reverse(sentence);
    Assert.assertEquals(reverseSentence, expected);
  }
}
