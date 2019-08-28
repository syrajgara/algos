package com.shabs.datastructures.map;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 # Construct a class Predictor with a method accept which takes a line as a string of words as input,
 # and a method predict which predicts the next word in a sequence, yield a prediction
 # weighted by frequency of inputs. i.e.
 # predictor.accept("roses are red")
 # predictor.accept("violets are blue")
 # predictor.predict("are") should give red with 1/2 probability, and blue with 1/2 probability
 # if we add another line, predictor.accept("are green") predictor.predict("are") should give 1/3 probability to each (red, blue, green)

 // Map<"are", Map<"red",1>>
 // Map<"are", Map<"blue",1>>
 */

public class Predictor {

  // Map<"are", Map<"red",2>>
  private Map<String, Map<String, Integer>> counts = new HashMap<>();
  private Random randomNum = new Random();

  public void accept(String line) {
    String[] words = line.split(" ");

    for (int i = 0; i < words.length - 1; i++) {
      if (!counts.containsKey(words[i])) {
        counts.put(words[i], new HashMap<>());
      }

      // Map<"are", Map<"red",2>>
      Map<String, Integer> childValue = counts.get(words[i]);
      childValue.put(words[i+1], childValue.getOrDefault(words[i+1], 0) + 1);
    }
  }

  public String predict(String input) {
    Map<String, Integer> childValue = counts.get(input);

    List<String> listOfFollowingWords = new ArrayList<>();

    for (Map.Entry<String, Integer> se : childValue.entrySet()) {
/*
      currentSum += se.getValue()
      currentSum / total
*/
      for (int i = 1; i <= se.getValue(); i++) {
        listOfFollowingWords.add(se.getKey());
      }
    }

/*
        for (String s : listOfFollowingWords) {
            System.out.println(s);
        }
*/
    int rnd = randomNum.nextInt(listOfFollowingWords.size());

    String output = listOfFollowingWords.get(rnd);
    //System.out.print(output + " ");

    return output;
  }


  public void print() {
    for (Map.Entry<String, Map<String, Integer>> e : counts.entrySet()) {
      System.out.println("\n" + e.getKey() + " -- Followed by:");
      System.out.println("======================");

      for (Map.Entry<String, Integer> se : e.getValue().entrySet()) {
        System.out.println(se.getKey() + " " + se.getValue());
      }
    }

    System.out.println();
  }

  public static void main(String[] args) {
    Predictor predictor = new Predictor();

    predictor.accept("roses are red");
    predictor.accept("somethign are red");
    predictor.accept("violets are blue");

    predictor.print();

    // run it many times
    Map<String, Integer> outputs = new HashMap<>();
    for (int i = 0; i < 1000000; i++) {
      String value = predictor.predict("are");
      outputs.put(value, outputs.getOrDefault(value, 0) + 1);
    }

    for (Map.Entry<String, Integer> e : outputs.entrySet()) {
      System.out.println(e.getKey() + " = " + e.getValue());
    }
  }
}

