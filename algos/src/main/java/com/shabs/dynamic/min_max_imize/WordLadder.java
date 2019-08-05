package com.shabs.dynamic.min_max_imize;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

/**
 * HIT TO COG
 *
 * Given 2 words - beginWord and endWord - and a dictionary of words
 * transform beginWord to endWord,
 * with each hop having only one char changed with resulting transformation a valid dict word
 *
 * All words have the same length. All words contain only lowercase alphabetic characters
 *
 * - use a word
 * - see what tranforms you can do with that word
 * - recursively drill down, exit a recursive call if cost is higher that what you had from before
 *
 * - key method is the finding valid paths forward
 */
public class WordLadder {

  private int transform(Set<String> wordSet, String beginWord, String endWord) {

    // indicates no valid transforms,
    // -1 since +1 in the previous iteration
    int minTransforms = Integer.MAX_VALUE - 1;

    if (wordSet.isEmpty()) {
      return minTransforms;
    }

    wordSet.remove(beginWord); // we do not want to re-use this word - else will run into cycles

    if (beginWord.equals(endWord)) {
      return 0;
    }

    Set<String> validTransforms = findValidTransforms(wordSet, beginWord);

    // instead of having 2 options, here we have multiple options
    // we need to minimize on these multiple options
    for(String newBeginWord : validTransforms) {

      int newTransforms = 1 + transform(wordSet, newBeginWord, endWord);

      // minTransforms = Math.min(minTransforms, newTransforms);
      if (newTransforms < minTransforms) {
        minTransforms = newTransforms;
        System.out.print("(" + (newTransforms) + ") " + newBeginWord + "* <- ");
      }
    }

    return minTransforms;
  }

  private Set<String> findValidTransforms(Set<String> wordSet, String beginWord) {
    Set<String> validTransforms = new HashSet<>();

    for (String word : wordSet) {
      for (int i = 0; i < beginWord.length(); i++) {
        String modifiedWord = modify(word, i);
        String modifiedBeginWord = modify(beginWord, i);
        if (modifiedBeginWord.equals(modifiedWord)) {
          validTransforms.add(word);
        }
      }
    }

    return validTransforms;
  }

  private String modify(String word, int ignore) {
    StringBuffer b = new StringBuffer();
    for (int i = 0; i < word.length(); i++) {
      if (i != ignore) {
        b.append(word.charAt(i));
      }
    }

    return b.toString();
  }

  @Test
  public void test() {

    String beginWord = "hit";
    String endWord = "cog";

    Set<String> wordSet = new HashSet(Arrays.asList("hot","dot","dog","lot","log","cog"));

    int expected = 4;
    int actual = transform(wordSet, beginWord, endWord);

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void testInvalid() {

    String beginWord = "hit";
    String endWord = "bot"; // not in dict

    Set<String> wordSet = new HashSet(Arrays.asList("hot","dot","dog","lot","log","cog"));

    int expected = 2147483646;
    int actual = transform(wordSet, beginWord, endWord);

    Assert.assertEquals(actual, expected);
  }
}
