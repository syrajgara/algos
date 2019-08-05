package com.shabs.dynamic.array;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * for every word -> ARRAY boolean [wordLength + 1]
 * init a[0] = true
 *
 * Given a dictionary of words, build a word in the dictionary using other words in that dictionary.
 * <p>
 * sort in reverse order, longer word first. since we just need the first longest word.
 * for each word see if other words can be used to form it.
 * <p>
 * for each word, create a **boolean array indicating valid locations to start next word search**
 * first position is true by default
 * for each position, see which words fit in, and mark end+1 for those words in the array
 * if last+1 position is marked TRUE, means we were able to fit words correctly to form this word
 * return boolean value for this position
 */
public class LongestWordMadeByOtherWord {

  private String longestWord(String[] dict) {
    sortReverse(dict);

    for (int i = 0; i < dict.length; i++) {
      if (isWordMadeByOtherWords(dict, i)) {
        return dict[i];
      }
    }

    return null;
  }

  private String[] sortReverse(String[] dict) {
    // reverse integer sort
    Arrays.sort(dict, (o1, o2) -> -1 * Integer.compare(o1.length(), o2.length()));

    return dict;
  }

  private boolean isWordMadeByOtherWords(String[] dict, int currentWordIndex) {
    String currentWord = dict[currentWordIndex];

    boolean[] validPositionToLookForNextWord = new boolean[currentWord.length() + 1];
    validPositionToLookForNextWord[0] = true;

    for (int charIndex = 0; charIndex < currentWord.length(); charIndex++) {
      if (!validPositionToLookForNextWord[charIndex]) {
        // not valid position to look for next dict word
        continue;
      }

      // loop thru all dict words after this word and see what fits
      for (int otherWordIndex = currentWordIndex + 1; otherWordIndex < dict.length; otherWordIndex++) {
        String otherWord = dict[otherWordIndex];

        int endPositionOfDictWord = charIndex + otherWord.length() - 1;
        if (endPositionOfDictWord >= currentWord.length()) {
          continue; // this dictWord will take us beyond the word's end index
        }

        // note: +1 -> substring, end position of substring is one before endIndex
        //+1 could mark the char after end of the word - and so the boolean is length+1
        String subString = currentWord.substring(charIndex, endPositionOfDictWord + 1);

        if (otherWord.equals(subString)) {
          // we found match, mark char after end as valid for next lookup
          validPositionToLookForNextWord[endPositionOfDictWord + 1] = true;
        }
      }
    }

    // if we marked last position as true
    // - we were able to get exact breakup of our word made of other words from dict
    return validPositionToLookForNextWord[currentWord.length()];
  }

  @Test
  public void findLongestWord() {
    String[] dict = {"test", "Tester", "testertest", "Testing",
        "Apple", "Seattle", "Banana", "batting", "ngcat",
        "batti", "BAT", "testingtester", "testbattingcat"};

    String expected = "testbattingcat";
    String actual = longestWord(dict);

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void findLongestWordRepeat() {
    String[] dict = {"test", "testtestabca", "abc", "testabc", "a"};

    String expected = "testtestabca";
    String actual = longestWord(dict);

    Assert.assertEquals(actual, expected);
  }
}
