package com.shabs.design;

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
      input--; // this makes Z=25, A=0, AA=26 etc ... basically one less than encode
      output = (char) ('A' + (input % 26)) + output;
      input /= 26;
    }

    return output.toString();
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
    String input = "ABCDEFG"; // maximum before overflow of the int
    int actual = encode(input);
    System.out.println(input + " = " + actual);
    String output = decode(actual);
    Assert.assertEquals(output, input);
  }

  @Test
  public void test6() {
    String input = "ZZZZZ"; // maximum before overflow of the int
    int actual = encode(input);
    System.out.println(input + " = " + actual);
    String output = decode(actual);
    Assert.assertEquals(output, input);
  }
}
