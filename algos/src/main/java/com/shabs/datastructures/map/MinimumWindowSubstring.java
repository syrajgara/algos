package com.shabs.datastructures.map;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * given strings S and T
 * find smallest substring of S that contains all chars from T
 * in any order - not dynamic longest common subsequence
 * <p>
 * have two pointers, start and end
 * FREQUENCY Map of chars in T
 * <p>
 * outer loop
 * - to move the end pointer
 * - if end pointer past last char of S - quit, you have the results.
 * - decrease tMap as you find chars of T in S
 * - continue till you find all the char in T in S
 * inner loop
 * - once outer is done, inner will do the opposite
 * - to move the start pointer
 * - move till you find first char of T in S
 * - move past it - and add it back to the tMap
 * - if tMap has a char that needs to be found in S, go back to outer loop
 */
public class MinimumWindowSubstring {

  private String find(String s, String t) {

    Map<Character, Integer> tMap = new HashMap<>();
    for (Character c : t.toCharArray()) {
      tMap.put(c, tMap.getOrDefault(c, 0) + 1);
    }

    int substringLength = Integer.MAX_VALUE;
    int substringHead = 0;

    int start = 0;
    int end = 0;

    int numTcharsStillToBeFound = t.length();

    // move end to the last char
    while (end < s.length()) {
      Character currentEndChar = s.charAt(end);

      // MOVE END -- removes(-) from tMap
      // find position "end" in S where all T has been found
      if (tMap.containsKey(currentEndChar)) {
        // found one T char match

        // one less char to search in tMap, decrement by 1
        tMap.put(currentEndChar, tMap.get(currentEndChar) - 1);

        if (tMap.get(currentEndChar) >= 0) {
          numTcharsStillToBeFound--;
        }
      }

      end++; // move to next position

      // MOVE START -- adds(+) to tMap
      while (numTcharsStillToBeFound == 0) {
        Character currentStartChar = s.charAt(start);
        if (t.contains(currentStartChar.toString())) {
          // found one T char match
          if (end - start < substringLength) {
            // string smaller than what we have found until now, so update
            substringHead = start;
            substringLength = end - start;
          }
          // lets kick it out, and find another instance of this char on the end side
          tMap.put(currentStartChar, tMap.get(currentStartChar) + 1);

          if (tMap.get(currentStartChar) > 0) {
            numTcharsStillToBeFound++;
          }
        }

        start++;
      }
    }

    return s.substring(substringHead, substringHead + substringLength);
  }

  @Test
  public void test() {
    String s = "ADOBECODEBANC";
    String t = "ABC";

    String expected = "BANC";
    String actual = find(s, t);

    Assert.assertEquals(actual, expected);
  }
}
