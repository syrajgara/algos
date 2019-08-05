package com.shabs.backtracking.permutation;

import org.testng.annotations.Test;

/**
 * Below are the permutations of string ABC.
 * ABC, ACB, BAC, BCA, CAB, CBA
 * <p>
 * fix one char (loop thru all chars), permute rest of the chars recursively and fix the array back
 * <p>
 * Time Complexity => O(n*n!), where n is the number of chars
 * <p>
 * http://www.geeksforgeeks.org/write-a-c-program-to-print-all-permutations-of-a-given-string/
 */
public class PermutationString {
  public PermutationString() {
  }

  public static void permute(char[] chars, int startIndex) {
    if (startIndex == (chars.length - 1)) // last char in the string
    {
      printPermutation(chars);
      return;
    }

    for (int i = startIndex; i < chars.length; i++) {
      swap(chars, startIndex, i); // the first index will be fixed and we will then permute to other chars
      permute(chars, startIndex + 1);
      swap(chars, startIndex, i); // fix the array back to original
    }
  }

  private static void printPermutation(char[] chars) {
    for (int i = 0; i < chars.length; i++) {
      System.out.print(chars[i] + " ");
    }
    System.out.println();
  }

  private static void swap(char[] chars, int i, int j) {
    char temp = chars[i];
    chars[i] = chars[j];
    chars[j] = temp;
  }

  @Test
  public void test() {
    String string = "ABCD";
    char[] chars = string.toCharArray();

    int startIndex = 0;

    permute(chars, startIndex);
  }
}
