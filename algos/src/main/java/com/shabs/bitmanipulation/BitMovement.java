package com.shabs.bitmanipulation;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BitMovement {

  @Test
  public void bitLeft() {
    int expected = 4;
    int actual = (1 << 2);
    Assert.assertEquals(actual, expected);
  }

  @Test void bitRightSigned() {
    int expected = 1;
    int actual = (4 >> 2);
    Assert.assertEquals(actual, expected);
  }

  @Test void bitRightSignedNegative() {
    int expected = -1;
    //  4 in binary 0000 0000 0000 0000 0000 0000 0000 0100
    // -4 in binary 1111 1111 1111 1111 1111 1111 1111 1100
    // arithmetic signed -- adds ones on the left
    // -1 in binary 1111 1111 1111 1111 1111 1111 1111 1111
    int actual = (-4 >> 2);
    Assert.assertEquals(actual, expected);
  }

  @Test void bitRightUnSigned() {
    int expected = 1073741823;
    // -4 in binary   1111 1111 1111 1111 1111 1111 1111 1100
    // logical unsigned -- add zeros on the left
    // unsigned shift 0011 1111 1111 1111 1111 1111 1111 1111
    int actual = (-4 >>> 2);
    Assert.assertEquals(actual, expected);
  }

  @Test void compute2raiseX() {
    int x;

    x = 0;
    Assert.assertEquals(1 << x , 1);

    x = 1;
    Assert.assertEquals(1 << x , 2);

    x = 3;
    Assert.assertEquals(1 << x , 4);

    x = 4;
    Assert.assertEquals(1 << x , 8);
  }

  @Test void isPowerOf2() {
    int x;

    x = 1;
    Assert.assertEquals((x & x-1) == 0, true);

    x = 2;
    Assert.assertEquals((x & x-1) == 0, true);

    x = 3;
    Assert.assertEquals((x & x-1) == 0, false);

    x = 4;
    Assert.assertEquals((x & x-1) == 0, true);

    x = 7;
    Assert.assertEquals((x & x-1) == 0, false);

    x = 8;
    Assert.assertEquals((x & x-1) == 0, true);
  }
}
