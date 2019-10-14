package com.shabs.zUnimplemented;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WordCount {
  private File file;

  public WordCount(String filePath) {
    file = new File(filePath);
  }

  public int countByEachChar() {
    int count = 0;
    boolean emptyFile = true;

    String str = "";
    char[] chars = str.toCharArray();

    Character prevChar = ' ';

    for (Character c : chars) {
      emptyFile = false;
      if (c == ' ' && prevChar != ' ') {
        count++;
      }

      prevChar = c;
    }

    if (!emptyFile && count == 0 && prevChar != ' ') {
      count = 1;
    }

    return count;
  }

  public void countGivenWordsInFile(String filepath, Map<String, Integer> bag) {

    List<String> linesInFile = new ArrayList<>();

    for (String line : linesInFile) {
      String[] words = line.split(" ");

      for (String word : words) {
        if (bag.containsKey(word)) {
          bag.put(word, bag.get(word) + 1);
        }
      }
    }
  }
}
