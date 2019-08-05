package com.shabs.datastructures.string;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 *
 * String input: xxyyyz
 * Output: x2y3z1
 */

public class CharCount {

  public static void main(String[] args) {

    String input = "xxyyyz";
    String output = transform(input);
    System.out.println(output);

    input = null;
    output = transform(input);
    System.out.println(output);

    input = "";
    output = transform(input);
    System.out.println(output);

    input = "a";
    output = transform(input);
    System.out.println(output);

    input = "1";
    output = transform(input);
    System.out.println(output);

  }

  public static String transform(String input) {

    if (input == null || input.equals("")) {
      return "";
    }

    int counter = 1;
    StringBuffer output = new StringBuffer();

    for (int i = 1; i < input.length(); i++) {
      // "aa" - 2
      // "aab"
      if (input.charAt(i-1) == input.charAt(i)) {
        counter++;
      } else {
        output.append(input.charAt(i-1)).append(counter);
        counter = 1;
        // output = a2,
      }
    }

    // a2b1
    output.append(input.charAt(input.length() - 1)).append(counter);

    return output.toString();
  }
}