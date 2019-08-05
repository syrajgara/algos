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
public class FillPatternIterative {

  private List<String> fill(String pattern, Character[] filler) {
    List<String> output = new ArrayList<>();

    // initialize the last char
    if (pattern.charAt(pattern.length() - 1) != '*') {
      output.add(String.valueOf(pattern.charAt(pattern.length() - 1)));
    } else {
      for (Character c : filler) {
        output.add(String.valueOf(c));
      }
    }

    for (int i = pattern.length() - 2; i >= 0; i--) { // O(pattern.length)

      List<String> temp = new ArrayList<>();
      Character currChar = pattern.charAt(i);

      for (String o : output) {
        if (currChar != '*') {
          temp.add(currChar + o);
        } else {
          for (Character c : filler) { // O(filler.length)
            temp.add(c + o);
          }
        }
      }

      output = temp;
    }

    return output;
  }

  @Test
  public void test() {
    String pattern = "*a**";

    Character[] filler = {'0', '1'};

    List<String> output = fill(pattern, filler);

    for (String s : output) {
      System.out.println(s);
    }
  }
}
