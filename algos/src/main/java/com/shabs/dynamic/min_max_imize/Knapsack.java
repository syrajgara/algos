package com.shabs.dynamic.min_max_imize;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * list of items available - need to fill sack of particular size
 * item has size and value
 * optimize the value in the sack
 *
 * Cannot MEMOIZE - on sackSize, since it also depends on what is in the list of iterms at a particular time
 * <p>
 * limited sack size, take each item from list - either use it or throw it
 * if using increase value, but reduce sack size
 * if not using, no change in value or sack size
 * maximize value
 */
public class Knapsack {
  public static int knapsack(List<Item> items, int knapsackSize, int index) {
    int totalValue = 0;

    if (knapsackSize == 0 || index == items.size()) {
      return totalValue;
    }

    Item currentItem = items.get(index);

    int valueIfUsingItem = 0;
    if (knapsackSize >= currentItem.size) {
      // taking this item, increase value and decrease size of remaining bag
      valueIfUsingItem = currentItem.value + knapsack(items,knapsackSize - currentItem.size, index + 1);
    }

    // ignoring this item, no increase in value from this item, or decrease in size
    int valueIfNotUsingItem = knapsack(items, knapsackSize,index + 1);

    return Math.max(valueIfUsingItem, valueIfNotUsingItem);
  }

  public static class Item {
    public int size;
    public int value;

    public Item(int size, int value) {
      this.size = size;
      this.value = value;
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
    int actual = knapsack(items, knapsackSize,0);

    Assert.assertEquals(actual, expected);
  }
}
