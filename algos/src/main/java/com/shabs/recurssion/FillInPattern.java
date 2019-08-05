package com.shabs.recurssion;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * give a pattern "*a**"
 * for every * in the pattern, fill in {0,1}
 *
 * input: "*a**" and {0,1}
 * ouput: {"0a00", "0a01", "0a10", "0a11", ... }
 */
public class FillInPattern {

  private List<String> fill(String pattern, Character[] filler, int pIndex) {
    List<String> output = new ArrayList<>();

    if (pIndex == pattern.length()) {
      return output;
    }

    List<String> nextFragments = fill(pattern, filler, pIndex + 1);

    if (pattern.charAt(pIndex) != '*') {
      if (nextFragments.isEmpty()) {
        output.add(String.valueOf(pattern.charAt(pIndex)));
      }

      for (String nf : nextFragments) {
        output.add(pattern.charAt(pIndex) + nf);
      }

      return output;
    }

    for (Character c : filler) {
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
