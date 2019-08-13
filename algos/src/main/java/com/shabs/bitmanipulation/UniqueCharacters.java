package com.shabs.bitmanipulation;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * find if string has unique characters
 * - keep an array of boolean one per char
 * - use bit manipulation (1 << val)
 */
public class UniqueCharacters {
  public static boolean isUniqueUsingBit(String text) {
    // assumption: text contains chars from a-z only.

    int bitMapForCharsInText = 0;

    for (char textChar : text.toCharArray()) {
      int textCharInt = textChar - 'a';

      // result is not 1 .. but depends on the which bit is 1 .. so check with != 0
      if ((bitMapForCharsInText & (1 << textCharInt)) != 0) {
        // this means we have already marked current character in the bitmap,
        // this is the 2nd time we saw it.
        return false;
      }

      bitMapForCharsInText |= (1 << textCharInt);
    }

    return true;
  }

  public static boolean isUniqueAscii(String text) {
    if (text.length() > 256) {
      return false; // cant be unique
    }

    // assumption: text contains ascii chars (int value 0 - 255).

    int extendedAsciiCharSetSize = 256;
    boolean[] uniqueness = new boolean[extendedAsciiCharSetSize];

    for (char textChar : text.toCharArray()) {
      int charIntValue = textChar;

      if (uniqueness[charIntValue]) {
        return false;
      }

      uniqueness[charIntValue] = true;
    }

    return true;
  }

  @Test
  public void isNotUnique() {
    String text = "shabbir";
    boolean isUnique = UniqueCharacters.isUniqueUsingBit(text);
    Assert.assertFalse(isUnique);
  }

  @Test
  public void isUnique() {
    String text = "abcdefg";
    boolean isUnique = UniqueCharacters.isUniqueUsingBit(text);
    Assert.assertTrue(isUnique);
  }

  @Test
  public void isNotUniqueAscii() {
    String text = "shabbir";
    boolean isUnique = UniqueCharacters.isUniqueAscii(text);
    Assert.assertFalse(isUnique);
  }

  @Test
  public void isUniqueAscii() {
    String text = "abcdefg";
    boolean isUnique = UniqueCharacters.isUniqueAscii(text);
    Assert.assertTrue(isUnique);
  }
}
