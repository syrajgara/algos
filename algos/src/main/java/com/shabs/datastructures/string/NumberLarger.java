package com.shabs.datastructures.string;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Given a number whose digits are unique,
 * find the next larger number that can be formed with those digits.
 * <p>
 * int input = 12465321;
 * int expected = 12512346;
 * <p>
 * from right find first digit that decreases from its right neighbour,
 * this digit needs to be swapped with next highest digit from the digits on the right
 * and then remaining digits needs to be ordered and added to its right
 * <p>
 * 12*4*65321 - swap 4 with 5
 * 12*5*64321
 * 12*5*12346 - sort low to high, digits right of 5, to get the answer
 * <p>
 * Solution - http://stackoverflow.com/questions/9368205/given-a-number-find-the-next-higher-number-which-has-the-exact-same-set-of-digi
 */
public class NumberLarger {

  public int nextLargerNumber2(int input) {
    char[] chars = String.valueOf(input).toCharArray();
    StringBuffer output = new StringBuffer();

    for (int i = chars.length - 1; i > 0; i--) {
      int currDigit = charToInt(chars[i]);
      int leftOfCurrDigit = charToInt(chars[i - 1]);

      if (leftOfCurrDigit >= currDigit) {
        continue;
      }

      // at this point all chars till i is in ascending order stating from index n-1
      // so pick the first char that is greater than (i-1) and swap it
      for (int j = chars.length - 1; j >= i; j--) {
        if (charToInt(chars[j]) > leftOfCurrDigit) {

          char temp = chars[i - 1];
          chars[i - 1] = chars[j];
          chars[j] = temp;
          break;
        }
      }

      // take the string as is till char (i-1)
      output = new StringBuffer(String.valueOf(chars).substring(0, i));

      // take the string in reverse order from (n-1) to i
      // [convert from ascending (n-1 to i) to descending (n-1 to i)]
      for (int k = chars.length - 1; k >= i; k--) {
        output.append(chars[k]);
      }
      break;
    }

    return Integer.parseInt(output.toString());
  }

  private int charToInt(char c) {
    return Integer.parseInt(String.valueOf(c));
  }

  public int nextLargerNumber(int a) {
    String newNumber = "0";

    char[] number = String.valueOf(a).toCharArray();

    int previousDigit = digitAtIndex(number, number.length - 1);

    for (int i = number.length - 2; i >= 0; i--) {
      int digit = digitAtIndex(number, i);

      if (digit < previousDigit) {
        // this is the location we need to do swap and fix all digits after this one.
        for (int j = number.length - 1; j > i; j--) {
          int compareDigit = digitAtIndex(number, j);
          if (compareDigit > digit) {
            char temp = number[i];
            number[i] = number[j];
            number[j] = temp;
            break;
          }
        }

        newNumber = String.valueOf(number);
        newNumber = newNumber.substring(0, i + 1);

        for (int j = number.length - 1; j > i; j--) {
          // reverse the string after the current index
          newNumber += number[j];
        }
        break;
      }
      previousDigit = digit;
    }

    return Integer.parseInt(newNumber);
  }

  private int digitAtIndex(char[] number, int i) {
    char k = number[i];
    return Integer.parseInt(String.valueOf(k));
  }

  @Test
  public void nextLargerNumber() {
    int input = 12465321;
    int expected = 12512346;

    int actual = nextLargerNumber2(input);
    Assert.assertEquals(actual, expected);
  }

  @Test
  public void nextLargerNumber2() {
    int input = 754123;
    int expected = 754132;

    int actual = nextLargerNumber2(input);
    Assert.assertEquals(actual, expected);
  }
}
