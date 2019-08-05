package com.shabs.dynamic.array;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * 2 player game, given a number n
 * you can subtract, 1^2, 2^2, 3^2 .... from it
 * then 2nd player takes turn
 * player who reaches 0 will win
 */
public class SquareLooser {

  public boolean canYouWinWith(int n) {
    boolean[] winArray = new boolean[n+1];
    winArray[0] = true;

    for (int i = 1; i <= n; i++ ) {
      int sqrt = (int) Math.sqrt(i);

      if (i - Math.pow(sqrt, 2) == 0) {
        // this number is perfect square - so it wins
        winArray[i] = true;
      } else {
        // now need to subtract perfect square and see if one of the balance is a looser,
        // if yes, than this number is winner
        for (int j = 1; j <= sqrt; j++) {
          int numberAtNextPlayersTurn = i - j*j;
          if (numberAtNextPlayersTurn > i) {
            // we didnt find any looser on next turn,
            // and the subtract of perfect square will take us in negative, so break
            continue;
          }

          if (!winArray[numberAtNextPlayersTurn]) {
            winArray[i] = true;
            continue;
          }
        }
      }
    }

    return winArray[n];
  }

  @Test public void testPefectSquaresUnder22() {
    int n;
    boolean expected;
    boolean actual;

    n=1;
    expected = true;
    actual = canYouWinWith(n);
    Assert.assertEquals(actual, expected);

    n=4;
    expected = true;
    actual = canYouWinWith(n);
    Assert.assertEquals(actual, expected);

    n=9;
    expected = true;
    actual = canYouWinWith(n);
    Assert.assertEquals(actual, expected);

    n=16;
    expected = true;
    actual = canYouWinWith(n);
    Assert.assertEquals(actual, expected);
  }

  @Test public void testPefectSquaresAbove22() {
    int n;
    boolean expected;
    boolean actual;

    n=25;
    expected = true;
    actual = canYouWinWith(n);
    Assert.assertEquals(actual, expected);

    n=36;
    expected = true;
    actual = canYouWinWith(n);
    Assert.assertEquals(actual, expected);
  }

  @Test public void testAbove22() {
    int n;
    boolean expected;
    boolean actual;

    n=25;
    expected = true;
    actual = canYouWinWith(n);
    Assert.assertEquals(actual, expected);

    n=30;
    expected = true;
    actual = canYouWinWith(n);
    Assert.assertEquals(actual, expected);

    n=40;
    expected = true;
    actual = canYouWinWith(n);
    Assert.assertEquals(actual, expected);

    n=400;
    expected = true;
    actual = canYouWinWith(n);
    Assert.assertEquals(actual, expected);
  }

  @Test public void testDivisibleBy5() {
    int n;
    boolean expected;
    boolean actual;

    n=5;
    expected = false;
    actual = canYouWinWith(n);
    Assert.assertEquals(actual, expected);

    n=10;
    expected = false;
    actual = canYouWinWith(n);
    Assert.assertEquals(actual, expected);

    n=15;
    expected = false;
    actual = canYouWinWith(n);
    Assert.assertEquals(actual, expected);

    n=20;
    expected = false;
    actual = canYouWinWith(n);
    Assert.assertEquals(actual, expected);
  }

  @Test public void testMinus2DivisibleBy5() {
    int n;
    boolean expected;
    boolean actual;

    n=7;
    expected = false;
    actual = canYouWinWith(n);
    Assert.assertEquals(actual, expected);

    n=12;
    expected = false;
    actual = canYouWinWith(n);
    Assert.assertEquals(actual, expected);

    n=17;
    expected = false;
    actual = canYouWinWith(n);
    Assert.assertEquals(actual, expected);

    n=22;
    expected = false;
    actual = canYouWinWith(n);
    Assert.assertEquals(actual, expected);
  }
}
