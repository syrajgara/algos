package com.shabs.datastructures.array;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * find the longest common prefix - valid for all elements of the array
 * <p>
 * - sort the strings
 * - the first and the last are most distinct,
 * so the common prefix of these 2 would be the longest for all elements
 */
public class LongestCommonPrefix {

  private String find(String[] strings) {

    Arrays.sort(strings);

    String first = strings[0];
    String last = strings[strings.length - 1];

    StringBuffer result = new StringBuffer();

    for (int i = 0; i < first.length(); i++) {
      if (first.charAt(i) != last.charAt(i)) {
        break;
      }

      result.append(first.charAt(i));
    }

    return result.toString();
  }

  @Test
  public void test() {

    String[] strings = {"abce",
        "abcdd",
        "abssss",
        "abcdee",
        "abcdfff"};

    String expected = "ab";
    String actual = find(strings);

    Assert.assertEquals(actual, expected);
  }
}
