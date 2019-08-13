package com.shabs.bitmanipulation;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * store 0 or 1 in the bitmap
 * we will need n/32 bitmaps, since each int has 32 bits and can store 0 or 1 for 32 ints.
 * <p>
 * use - new int[Integer.MAX_VALUE >> 6] ... to get array big enough to hold bitmap for all integers
 */
public class FindMissingInteger {
  private int find(int[] data) {
    int[] bitmaps = new int[data.length / 32 + 1];

    for (int i = 0; i < data.length; i++) {
      int datum = data[i];

      int bitmapIndex = datum / 32; // defines which array index, this numbers bitmap is on
      int bitmapOffset = datum % 32; // remainder defines which of the 32 bits, this number represents

      // turn ON the corresponding bit in the corresponding array index
      bitmaps[bitmapIndex] |= (1 << bitmapOffset);
    }

    for (int i = 0; i < bitmaps.length; i++) {
      for (int j = 0; j < 32; j++) {
        if ((bitmaps[i] & (1 << j)) == 0) {
          // bit is set to zero, this is our missing number's bit
          int missingNumber = i * 32 + j;
          return missingNumber;
        }
      }
    }

    return -1; // no missing number
  }

  @Test
  public void find() {
    // assuming data starts at 0
    // if it starts from 1, we will have to substract 1 from all numbers
    // to make sure 32 fits in row 1, 32nd bit
    int[] data = {0, 1, 2, 3, 4, 5, 6, 7, /* 8, */ 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19};
    int expected = 8;

    int actual = find(data);
    Assert.assertEquals(actual, expected);
  }
}
