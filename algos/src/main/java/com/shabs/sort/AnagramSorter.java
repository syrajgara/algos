package com.shabs.sort;

import org.testng.annotations.Test;

import java.util.*;

/**
 * Sort anagrams, collection and array versions
 * <p>
 * first sort each string's chars, use this for sorting within the array
 * write Comparator, that first sorts the chars in the string
 * and than uses that to compare with each other
 */
public class AnagramSorter {
  public static void sortCollection(List<String> anagrams) {
    Collections.sort(anagrams, new AnagramComparator());

    // now print it
    System.out.println("sortCollection :\n---------------");
    for (String anagram : anagrams) {
      System.out.println(anagram);
    }
  }

  public static void sortArray(String[] anagramArray) {
    Arrays.sort(anagramArray, new AnagramComparator());

    // now print it
    System.out.println("sortArray :\n---------------");
    for (String anagram : anagramArray) {
      System.out.println(anagram);
    }
  }

  public static class AnagramComparator
      implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
      o1 = sortCharsInString(o1);
      o2 = sortCharsInString(o2);

      return o1.compareTo(o2);
    }

    private String sortCharsInString(String s) {
      char[] chars = s.toCharArray();
      Arrays.sort(chars);

      return String.valueOf(chars);
    }
  }

  @Test
  public void test() {
    List<String> anagrams = new ArrayList<>();
    anagrams.add("axyz");
    anagrams.add("abcz");
    anagrams.add("clmn");
    anagrams.add("yzxa");
    anagrams.add("mnlc");
    anagrams.add("zbac");

    AnagramSorter.sortCollection(anagrams);

    System.out.println("---------------\n");

    String[] anagramArray = anagrams.toArray(new String[anagrams.size()]);
    AnagramSorter.sortArray(anagramArray);
  }
}
