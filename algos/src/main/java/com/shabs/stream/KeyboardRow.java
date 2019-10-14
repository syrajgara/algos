package com.shabs.stream;

import java.util.stream.Stream;

/**
 * given a list of words, return words that are spelt by using char from single row of a keyboard.
 *
 * Input: ["Hello", "Alaska", "Dad", "Peace"]
 * Output: ["Alaska", "Dad"]
 */
public class KeyboardRow {
  public String[] findWords(String[] words) {
    return Stream.of(words)
        .filter(s -> s.toLowerCase().matches("[qwertyuiop]*|[asdfghjkl]*|[zxcvbnm]*"))
        .toArray(String[]::new);
  }
}
