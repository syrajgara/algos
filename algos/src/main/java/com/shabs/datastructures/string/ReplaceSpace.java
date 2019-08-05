package com.shabs.datastructures.string;

import org.testng.annotations.Test;

/**
 * Replace all spaces in a String with %20
 * <p>
 * - working with arrays only
 * - count all spaces, multiply by 2 and add to original length of string
 * - if using same char array - start from last char of array
 * - iterate thru all char, add char or %20 - update indexes appropriately.
 */
public class ReplaceSpace {
  private static final String TEST_STRING = "Encode spaces in this string with %20";

  public String encodeUsingBuffer(String input) {
    StringBuffer newString = new StringBuffer();
    for (char c : input.toCharArray()) {
      if (c == ' ') {
        newString.append("%20");
      } else {
        newString.append(c);
      }
    }
    return newString.toString();
  }

  private static String encodeString(String string) {
    char[] chars = string.toCharArray();
    int stringLength = chars.length;

    // no need to convert to char[] - could have just used String.charAt()
    for (char letter : chars) {
      if (letter == ' ') {
        stringLength += 2; // increase length by 2
      }
    }

    int newArrayIndex = stringLength - 1;
    char[] newChars = new char[stringLength];

    // going reverse - only needed when using the same char[] to restore the results
    for (int i = chars.length - 1; i >= 0; i--) {
      if (string.charAt(i) == ' ') {
        newChars[newArrayIndex--] = '0';
        newChars[newArrayIndex--] = '2';
        newChars[newArrayIndex--] = '%';
      } else {
        newChars[newArrayIndex--] = string.charAt(i);
      }
    }

    return new String(newChars);
  }

  @Test
  public void test() {
    System.out.println(TEST_STRING);
    System.out.println(encodeString(TEST_STRING));
    System.out.println(encodeUsingBuffer(TEST_STRING));
  }
}
