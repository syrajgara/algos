package com.shabs.greedy;

import org.testng.annotations.Test;

/**
 * given denominations 1, 5, 10, 25
 * find the least amount of coins needed to get an amount - 27
 * <p>
 * for denominations 1, 10, 25 and amount 31
 */

public class GreedyCoin {
  // Greedy works for coins of denomination -- 1, 5, 10, 25
  public static int numberOfCoins(int totalAmount) {
    int coins;

    coins = (totalAmount / 25)
        + ((totalAmount % 25) / 10)
        + (((totalAmount % 25) % 10) / 5)
        + (((totalAmount % 25) % 10) % 5);

    return coins;
  }

  @Test
  public void test() {
    int totalAmount;

    totalAmount = 4;
    System.out.println("totalAmount = " + totalAmount + " coins = " + numberOfCoins(totalAmount));

    totalAmount = 14;
    System.out.println("totalAmount = " + totalAmount + " coins = " + numberOfCoins(totalAmount));

    totalAmount = 24;
    System.out.println("totalAmount = " + totalAmount + " coins = " + numberOfCoins(totalAmount));

    totalAmount = 25;
    System.out.println("totalAmount = " + totalAmount + " coins = " + numberOfCoins(totalAmount));

    totalAmount = 31;
    System.out.println("totalAmount = " + totalAmount + " coins = " + numberOfCoins(totalAmount));

    totalAmount = 34;
    System.out.println("totalAmount = " + totalAmount + " coins = " + numberOfCoins(totalAmount));

    totalAmount = 49;
    System.out.println("totalAmount = " + totalAmount + " coins = " + numberOfCoins(totalAmount));

    totalAmount = 50;
    System.out.println("totalAmount = " + totalAmount + " coins = " + numberOfCoins(totalAmount));
  }
}
