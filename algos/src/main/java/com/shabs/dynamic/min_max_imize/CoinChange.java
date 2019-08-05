package com.shabs.dynamic.min_max_imize;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * given denominations, find the least amount of coins needed to get an amount
 * <p>
 * for denominations 1, 5, 10, 25 - get amount 27
 * for denominations 1, 10, 25 - get amount 31
 * <p>
 * for amount < 25 do a greedy solution, using mod and balance
 * for higher amount do MIN of either -25 or -10 or -1
 */

public class CoinChange {
  //save data for use by next iteration - memoization
  private static Map<Integer, Integer> coinsForAmounts = new HashMap<>();

  // coins 1, 10, 25
  public static int numberOfCoins(int totalAmount) {
    if (!coinsForAmounts.containsKey(totalAmount)) {
      int coins;

      if (totalAmount < 25) {
        // Greedy approach for less than 25
        coins = (totalAmount / 10) + (totalAmount % 10);
      } else {
        coins = 1 + // taking one coin - either 25 or 10 or 1
                Math.min(Math.min(numberOfCoins(totalAmount - 25),
                                  numberOfCoins(totalAmount - 10)),
                                  numberOfCoins(totalAmount - 1));
      }

      // totalAmount = 103 coins = 7 -- totalTime =    837ms -- with memoization
      // totalAmount = 103 coins = 7 -- totalTime = 219524ms -- without memoization
      coinsForAmounts.put(totalAmount, coins); // with memoization
      //return coins; // without memoization
    }

    return coinsForAmounts.get(totalAmount);
  }

  @Test
  public void test() {
    int totalAmount;
    long startTime = System.nanoTime();

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

    totalAmount = 103;
    System.out.println("totalAmount = " + totalAmount + " coins = " + numberOfCoins(totalAmount));

    System.out.println("totalTime = " + (System.nanoTime() - startTime));
  }
}
