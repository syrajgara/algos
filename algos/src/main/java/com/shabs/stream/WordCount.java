package com.shabs.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.testng.annotations.Test;

public class WordCount {


  @Test
  public void wordCount() {
    List<String> linesInFile = new ArrayList<>();
    linesInFile.add("java stream remove-me api");
    linesInFile.add("how to use java stream api");

    System.out.println("Array");
    Stream.of(linesInFile) // Stream.of is for arrays not for list
        .forEach(e -> System.out.println(e + "\n"));


    System.out.println("All Sentences");
    linesInFile.stream()
        .forEach(e -> System.out.println(e + "\n"));


    System.out.println("\n\nFirst Sentence");
    String firstLine = linesInFile.stream()
        .findFirst()
        .orElse(null);
    System.out.println(firstLine);


    System.out.println("\n\nFiltered Words: ");
    List<String> words = linesInFile.stream()
        //.map(someClass::someMethod)
        .map(l -> l.split(" "))
        .flatMap(w -> Stream.of(w))
        .filter(e -> !e.equals("remove-me"))
        .filter(e -> e.length() != 2)
        .collect(Collectors.toList());

    words.forEach(e -> System.out.println(e));


    System.out.println("\n\nWord Counts: ");
    Map<String, Long> wordCount = linesInFile.stream()
        //.map(someClass::someMethod)
        .map(l -> l.split(" "))
        .flatMap(w -> Stream.of(w))
        .map(word -> new SimpleEntry<>(word, 1))
        .collect(Collectors.groupingBy(AbstractMap.SimpleEntry::getKey, Collectors.counting()));

    wordCount.forEach((k,v) -> System.out.println(k + " = " + v));
  }

  @Test
  public void wordCountFromFile() throws IOException {
    Path path = Paths.get("filepath/book.txt");

    Map<String, Long> wordCount = Files.lines(path)
        .map(l -> l.trim().split(" "))
        .flatMap(w -> Stream.of(w))
        .map(word -> new SimpleEntry<>(word, 1))
        .collect(Collectors.groupingBy(AbstractMap.SimpleEntry::getKey, Collectors.counting()));

    wordCount.forEach((k, v) -> System.out.println(String.format("%s ==>> %d", k, v)));
  }


  @Test
  public void pattern() {
    String s = "this is line one\n"
        + "this is line two\n"
        + "is line three";

    Pattern p = Pattern.compile("this");

    Scanner scanner = new Scanner(s); // create a mew token (line) for every javaWhitespace in the input

    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      Matcher m = p.matcher(line);
      if (m.find()) {
        System.out.println(line);
      }
    }
  }
}
