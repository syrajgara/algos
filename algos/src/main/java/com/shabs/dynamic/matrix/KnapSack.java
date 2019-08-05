package com.shabs.dynamic.matrix;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * create maxWeight[size from 0 to knapsackSize][num of items (weight)]
 * with data in this matrix being max weight for that size and upto that item.
 *
 * maxWeight[s][i] = max(maxWeight[s][i - 1], currWeight + maxWeight[s - currSize][i - 1])
 *
 * notice here that to calc a weight you just need weight data for previous items.
 * - which means you dont need a complete matrix, just 2 columns of matrix at any given time
 * - this reduces space complexity
 */
public class KnapSack {

  private int knapsack(List<Item> items, int knapsackSize, int i) {
    // row => weights
    // col => item size
    // data => weight

    // zero size, psuedo item with weight and size zero
    // weight[i][0] = 0; // column zero always 0
    // weight[0][j] = 0; // row zero always 0
    int[][] values = new int[knapsackSize + 1][items.size() + 1];

    // row = size, col = item, data = weight
    for (int row = 1; row < values.length; row++) {
      for (int col = 1; col < values[0].length; col++) {
        int valueWithoutUsingItem = values[row][col - 1];
        int valueWithUsingItem = 0;

        int itemIndex = col - 1;
        Item currItem = items.get(itemIndex);

        if (col - 1 >= 0 && row - currItem.size >= 0) {
          valueWithUsingItem = currItem.weight + values[row - currItem.size][col - 1];
        }

        values[row][col] = Math.max(valueWithoutUsingItem, valueWithUsingItem);
      }
    }

    return values[values.length - 1][values[0].length - 1];
  }

  public static class Item {
    public int size;
    public int weight;

    public Item(int size, int weight) {
      this.size = size;
      this.weight = weight;
    }
  }

  @Test
  public void test() {
    int knapsackSize = 30;
    List<Item> items = new ArrayList<>();

    items.add(new Item(20, 5));
    items.add(new Item(10, 2)); // should ignore
    items.add(new Item(8, 2));
    items.add(new Item(2, 1));

    int expected = 8;
    int actual = knapsack(items, knapsackSize, 0);

    Assert.assertEquals(actual, expected);
  }
}
