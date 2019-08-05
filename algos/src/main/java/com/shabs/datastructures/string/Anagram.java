package com.shabs.datastructures.string;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Check if s and t are anagrams of each other
 * <p>
 * - sort chars and compare
 * - keep hashmap of chars and add for s, subtract for t
 * <p>
 * - keep simple array of 26 length, ++ index for s and -- index for t
 * - loop to see if any char has non-zero count
 */
public class Anagram {

  public boolean isAnagramUsingMap(String s, String t) {
    Map<Character, Integer> map = new HashMap<>();

    for (Character c : s.toCharArray()) {
      map.put(c, map.getOrDefault(c, 0) + 1);
    }

    for (Character c : t.toCharArray()) {
      map.put(c, map.getOrDefault(c, 0) - 1);
    }

    for (Character c : map.keySet()) {
      if (map.get(c) != 0) {
        return false;
      }
    }

    return true;
  }

  public boolean isAnagram(String s, String t) {
    int[] alphabet = new int[26];

    for (int i = 0; i < s.length(); i++) {
      alphabet[s.charAt(i) - 'a']++;
    }
    for (int i = 0; i < t.length(); i++) {
      alphabet[t.charAt(i) - 'a']--;
    }

    for (int i : alphabet) {
      if (i != 0) {
        return false;
      }
    }

    return true;
  }

  @Test
  public void testPositive() {
    String s = "abcdef";
    String t = "daebfc";

    boolean expected = true;

    boolean actual = isAnagram(s, t);
    Assert.assertEquals(actual, expected);

    boolean actual2 = isAnagramUsingMap(s, t);
    Assert.assertEquals(actual2, expected);
  }

  @Test
  public void testNegative() {
    String s = "abc";
    String t = "xyz";

    boolean expected = false;

    boolean actual = isAnagram(s, t);
    Assert.assertEquals(actual, expected);

    boolean actual2 = isAnagramUsingMap(s, t);
    Assert.assertEquals(actual2, expected);
  }
}
