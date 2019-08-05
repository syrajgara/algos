package com.shabs.dynamic.array;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The Largest Product Continuous Substring(LPCS) problem
 * <p>
 * find contiguous subset of an array that will give largest product
 * <p>
 * loop thru the elements, with every element do a product with previous product.
 * save the max and min values of this calculation.
 * reason for min (negative) value is since a new negative number
 * could make the product positive and bigger than just doing a product with previous max
 */
public class MaximumSubArrayProduct {
  private int maxProduct(int[] input) {
    int[] max = new int[input.length];
    int[] min = new int[input.length];

    int maxProduct = max[0] = min[0] = input[0];

    for (int i = 1; i < input.length; i++) {
      int currentValue = input[i];

      max[i] = Math.max(Math.max( currentValue,
                                  currentValue * max[i - 1]),
                                  currentValue * min[i - 1]);

      min[i] = Math.min(Math.min( currentValue,
                                  currentValue * max[i - 1]),
                                  currentValue * min[i - 1]);

      maxProduct = Math.max(maxProduct, max[i]);
    }

    return maxProduct;
  }

  @Test
  public void maxProduct1() {
    int[] input = {3, -2, 4, -2, 0};
    int expected = 48; // 3 * -2 * 4 * -2

    int actual = maxProduct(input);
    Assert.assertEquals(actual, expected);
  }

  @Test
  public void maxProduct2() {
    int[] input = {0, 3, -2, 1, -2, -4, 0};
    int expected = 12; // 3 * -2 * 1 * -2

    int actual = maxProduct(input);
    Assert.assertEquals(actual, expected);
  }
}
