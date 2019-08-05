package com.shabs.bitmanipulation;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * use binary addition,
 * you will need to take each bit out of the number and then add to corresponding of another number,
 * and take care of the carry.
 * <p>
 * bit shift (1 << n) -- signed left shift, put 0 on the right
 * bit shift (1 >>> n) -- un-signed right shift, put 0 on the left
 * bit shift (1 >> n) -- signed right shift, replicate sign bit on the left (left bit added is 0 or 1)
 */
public class AddTwoNumbersInBinary {
  public static int add(int x, int y) {
    int carry = 0;
    int result = 0;

    // loop thru 32 bits of the numbers
    for (int bitIndex = 0; bitIndex < 32; bitIndex++) {
      int xBit = getBit(x, bitIndex);
      int yBit = getBit(y, bitIndex);

      int sum = xBit + yBit + carry;

      if (sum == 2 || sum == 3) {
        carry = 1;
      } else {
        carry = 0;
      }

      if (sum == 1 || sum == 3) {
        result = result | (1 << (bitIndex));
      }
    }

    return result;
  }

  private static int getBit(int x, int bitIndex) {
    return (x & (1 << bitIndex)) == 0 ? 0 : 1;
  }

  @Test
  public void add() {
    int x = 5;
    int y = 10;
    int expected = x + y;

    int result = AddTwoNumbersInBinary.add(x, y);
    Assert.assertEquals(result, expected);
  }

  @Test
  public void squares() {
    System.out.println("" + (1 << 2));
  }
}
