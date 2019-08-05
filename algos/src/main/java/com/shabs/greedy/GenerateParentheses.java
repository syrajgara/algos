package com.shabs.greedy;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Generate all combinations of n parentheses
 * <p>
 * for ex, when n=3
 * preference is given to (, so you will get following ordering
 * ((()))
 * (()())
 * (())()
 * ()(())
 * ()()()
 * <p>
 * Recursion
 * - each recursion, provide a partial string and number of left and number of right parentheses left
 */
public class GenerateParentheses {

  private void generate(int left, int right, String substring, List<String> combinations) {
    if (right < left) {
      return; // we used up too many right parentheses, invalid call
    }

    if (left == 0 && right == 0) {
      // we have completed one valid string, save it
      combinations.add(substring);
      return;
    }

    // make sure you dont update substring, left, right - they are reused for right parentheses
    if (left > 0) {
      // lets use one left parentheses
      generate(left - 1, right, substring + "(", combinations);
    }

    if (right > 0) {
      // lets use one right parentheses
      generate(left, right - 1, substring + ")", combinations);
    }
  }

  @Test
  public void test() {
    int numOfParentheses = 3;
    List<String> combinations = new ArrayList<>();
    String substring = "";

    generate(numOfParentheses, numOfParentheses, substring, combinations);

    for (String string : combinations) {
      System.out.println(string);
    }
  }
}
