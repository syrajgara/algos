package com.shabs.datastructures.map;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * MAP[char][index]
 *
 * <p>
 * Given a string, find the length of the longest substring without repeating characters.
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 * Given "bbbbb", the answer is "b", with the length of 1.
 * Given "pwwkew", the answer is "wke", with the length of 3.
 * Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * <p>
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/?tab=Description
 * <p>
 * - set lowIndex = 0, outputString = ""
 * - loop thru all the chars in the String
 * - Store the char and the index in a HashMap
 * - if you encounter same char again (duplicate in the map),
 * - check if length of String from lowIndex to previousIndex is longer than the current output
 * - if yes use that as the new longer string
 * - use the index from the map to move a LowPointer and reset the char's index in the Map
 * - if new char, put in Map the char and its index
 */
public class LongestSubstringWithRepeatingCharacters_ZeroTimes {

  private String find(String input) {
    String output = "";
    int lowIndex = 0;

    Map<String, Integer> charIndex = new HashMap<>();

    for (int i = 0; i < input.length(); i++) {
      String thisChar = String.valueOf(input.charAt(i));

      if (charIndex.containsKey(thisChar)) {
        // from lowindex to previous char
        int newMaxLength = (i - 1) - lowIndex;
        if (newMaxLength >= output.length()) {
          output = input.substring(lowIndex, i);
        }

        int newLowIndex = charIndex.get(thisChar) + 1;
        if (newLowIndex > lowIndex) {
          lowIndex = newLowIndex;
        }

        charIndex.replace(thisChar, i);
      } else {
        charIndex.put(thisChar, i);
      }
    }

    if (output.equals("")) {
      output = input;
    }

    return output;
  }

  @Test
  public void test() {
    String input = "abcabcbb";
    String expected = "abc";

    String actual = find(input);
    Assert.assertEquals(actual, expected);
  }

  @Test
  public void test2() {
    String input = "bbbbbb";
    String expected = "b";

    String actual = find(input);
    Assert.assertEquals(actual, expected);
  }

  @Test
  public void test3() {
    String input = "abcde";
    String expected = "abcde";

    String actual = find(input);
    Assert.assertEquals(actual, expected);
  }
}
