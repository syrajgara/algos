package com.shabs.datastructures.trie;

/**
  Use Suffix Tree - for ex, for word banana - create trie with
  banana
  anana
  nana
  ana
  na
  a

  now you can search for n-gram in the above suffix tree.


  find the length of the longest repeated substring inside of a string

  "AAAAAA" == "AAAAA" == 5

// "toil toil toil and trouble, cauldrons bubble" == "toil toil " == 10
//  toil toil
//       toil toil

// "the quick brown fox jumped over the" == "the"  == 3
// “double, double toil and trouble” == “double” == 6
*/

public class LongestRepeatingSubString {


  public int findLongest(String str) {
    int maxLength = 0;

    // length of pattern
    for (int cl = 1; cl <= str.length() - 1; cl++) {
      // starting location
      for (int i = 0; i < str.length() - cl; i++) {
        String subStr = str.substring(i, i + cl);

        // use Set to store the substr
        for (int start = i + 1; start < str.length() - cl; start++) {
          String currSubStr = str.substring(start, cl);
          if (subStr.equals(currSubStr)) {
            maxLength = cl;
            continue;
          }
        }
      }
    }

    return maxLength;
  }
}
