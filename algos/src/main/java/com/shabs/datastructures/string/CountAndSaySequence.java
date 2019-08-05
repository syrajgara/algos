package com.shabs.datastructures.string;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * The count-and-say sequence is the sequence of integers beginning as follows:
 * 1, 11, 21, 1211, 111221, ...
 * <p>
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 * Given an integer n, generate the nth sequence.
 * <p>
 * Note: The sequence of integers will be represented as a string.
 */
public class CountAndSaySequence {

  public void findNthSequence(int n, List<String> output) {

    output.add(""); // when n = 0

    if (n > 0) {
      output.add("1"); // first string
    }

    for (int i = 2; i <= n; i++) {
      String previous = output.get(i - 1);
      StringBuffer current = new StringBuffer();

      int count = 0;
      int previousChar = 0;

      for (Character c : previous.toCharArray()) {
        int currentChar = Integer.parseInt(c.toString());

        if (currentChar == previousChar) {
          count++;
        } else {
          if (count > 0) {
            current.append(count).append(previousChar);
          }
          previousChar = currentChar;
          count = 1;
        }
      }

      if (count > 0) {
        current.append(count).append(previousChar);
      }

      output.add(current.toString());
    }
  }

  @Test
  public void test() {
    int n = 20;

    List<String> output = new ArrayList<>();
    findNthSequence(n, output);

    int index = 0;
    for (String s : output) {
      System.out.println(index++ + " - " + s);
    }
  }
}
