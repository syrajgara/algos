package com.shabs.iteration;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * excel column name A, B .. Z,  AA, AB, ... AZ
 *                   1, 2 .. 26, 27, 28, ... 52
 *
 * input = "ABCDEFG"; // maximum before overflow of the int
 */
public class ExcelNumbering {

  public int encode(String input) {
    int output = 0;
    for (Character c : input.toCharArray()) {
      output = output * 26 + (c - 'A' + 1);
    }

    return output;
  }

  public String decode(int input) {
    String output = "";

    while (input != 0) {
      // without shifting every number down by 1, A will be 1 and Z will be zero
      // with shifting, A will be 0 and Z will be 25
      // and AA will be 26
      input--;

      // due to the modulus, we will get char from the R to L
      // so the newly created (modulus) char will be left of the previous output
      output = (char) ('A' + (input % 26)) + output;

      // remove data for this char, and redo loop with balance number, to find the next (previous) char
      input /= 26;
    }

    return output;
  }

  @Test
  public void test1() {
    String input = "A";
    int actual = encode(input);
    System.out.println(input + " = " + actual);
    String output = decode(actual);
    Assert.assertEquals(output, input);
  }

  @Test
  public void test2() {
    String input = "Z";
    int actual = encode(input);
    System.out.println(input + " = " + actual);
    String output = decode(actual);
    Assert.assertEquals(output, input);
  }

  @Test
  public void test3() {
    String input = "AA";
    int actual = encode(input);
    System.out.println(input + " = " + actual);
    String output = decode(actual);
    Assert.assertEquals(output, input);
  }

  @Test
  public void test4() {
    String input = "AZ";
    int actual = encode(input);
    System.out.println(input + " = " + actual);
    String output = decode(actual);
    Assert.assertEquals(output, input);
  }

  @Test
  public void test5() {
    String input = "BA";
    int actual = encode(input);
    System.out.println(input + " = " + actual);
    String output = decode(actual);
    Assert.assertEquals(output, input);
  }

  @Test
  public void test6() {
    String input = "BB";
    int actual = encode(input);
    System.out.println(input + " = " + actual);
    String output = decode(actual);
    Assert.assertEquals(output, input);
  }

  @Test
  public void test7() {
    String input = "ABCDEFG"; // maximum before overflow of the int
    int actual = encode(input);
    System.out.println(input + " = " + actual);
    String output = decode(actual);
    Assert.assertEquals(output, input);
  }

  @Test
  public void test8() {
    String input = "ZZZZZ"; // maximum before overflow of the int
    int actual = encode(input);
    System.out.println(input + " = " + actual);
    String output = decode(actual);
    Assert.assertEquals(output, input);
  }
}
