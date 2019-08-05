package com.shabs.recurssion;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a string s and a dictionary of words dict, add spaces in s to construct a sentence
 * where each word is a valid dictionary word.
 * <p>
 * Return all such possible sentences.
 * <p>
 * For example, given
 * s = "catsanddog",
 * dictionary = ["cat", "cats", "and", "sand", "dog"].
 * <p>
 * A solution is ["cats and dog", "cat sand dog"].
 * <p>
 * ASSUMPTION: the sentence can be broken with the words in the dictionary. (no invalid words in the sentence)
 * <p>
 * - loop thru words in the dictionary
 * - if you find the word, recurse with remaining fragment of the sentence
 * - if no remaining fragment, add word to the list of sentences
 * - on stack re-wound, get a list of sentences, add current word as prefix
 * - return the list of sentences
 */

public class WordBreak {
  public static List<String> solve(List<String> dictionary, String sentenceWithoutSpaces) {
    List<String> sentences = new ArrayList<>();
    if (sentenceWithoutSpaces.isEmpty()) {
      return sentences;
    }

    for (String word : dictionary) {
      if (sentenceWithoutSpaces.startsWith(word)) {
        if (sentenceWithoutSpaces.length() == word.length()) {
          // if this is the last word in the sentence
          sentences.add(word);
        } else {
          List<String> sentenceFragments = solve(dictionary, sentenceWithoutSpaces.substring(word.length()));
          for (String sentenceFragment : sentenceFragments) {
            sentences.add(word + " " + sentenceFragment);
          }
        }
      }
    }

    return sentences;
  }

  @Test
  public void test() {
    List<String> dictionary = Arrays.asList("cat", "cats", "and", "sand", "dog");

    String sentenceWithoutSpaces = "catsanddog";

    List<String> sentences = solve(dictionary, sentenceWithoutSpaces);
    for (String sentence : sentences) {
      System.out.println(sentence);
    }
  }

}
