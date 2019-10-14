package com.shabs.recurssion;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * give a pattern "*a**"
 * for every * in the pattern, fill in {0,1}
 *
 * Start building from the last char
 *
 * if building from first char, O(m^n) - where m is number of fillers, n is length of pattern
 *
 * input: "*a**" and {0,1}
 * ouput: {"0a00", "0a01", "0a10", "0a11", ... }
 */
public class FillInPattern {

  private List<String> fill(String pattern, Character[] fillers, int pIndex) {
    List<String> output = new ArrayList<>();

    if (pIndex == pattern.length()) {
      return output;
    }

    List<String> nextFragments = fill(pattern, fillers, pIndex + 1);

    if (pattern.charAt(pIndex) != '*') {
      if (nextFragments.isEmpty()) {
        output.add(String.valueOf(pattern.charAt(pIndex)));
      }

      for (String nf : nextFragments) {
        output.add(pattern.charAt(pIndex) + nf);
      }

      return output;
    }

    for (Character c : fillers) {
      if (nextFragments.isEmpty()) {
        output.add(String.valueOf(c));
      }

      for (String nf : nextFragments) {
        output.add(c + nf);
      }
    }

    return output;
  }

  @Test
  public void test() {
    String pattern = "*a**";
    int pIndex = 0;

    Character[] filler = {'0', '1'};

    List<String> output = fill(pattern, filler, pIndex);

    for (String s : output) {
      System.out.println(s);
    }
  }
}
