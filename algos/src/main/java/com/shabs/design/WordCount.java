package com.shabs.design;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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

  public void wordCountJava() throws IOException {
    List<String> linesInFile = new ArrayList<>();
    linesInFile.add("java stream api");
    linesInFile.add("how to use java stream api");

//    List<String> words = linesInFile.stream().map(l -> l.split(" ")).flatMap();
//
//    Map<String, Long> wordCounts = words.stream().collect(groupingBy(Function.identity(), counting()));
  }

  public void wordCountFromFile() throws IOException {
    Path path = Paths.get("filepath/book.txt");

//    Map<String, Long> wordCount = Files.lines(path)
//        .flatMap(line -> Arrays.stream(line.trim().split("\s")))
//        .map(word -> new AbstractMap.SimpleEntry(word, 1))
//        .collect(groupingBy(AbstractMap.SimpleEntry::getKey, counting()));

//    wordCount.forEach((k, v) -> System.out.println(String.format("%s ==>> %d", k, v)));
  }
}
