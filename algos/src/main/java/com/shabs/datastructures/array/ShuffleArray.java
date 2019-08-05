package com.shabs.datastructures.array;

import org.testng.annotations.Test;

import java.util.Random;

/**
 * Shuffle given array of numbers.
 * <p>
 * loop thru each number in the list
 * - at each position swap that number with randomly picked other number
 * - continue till end of the list
 */
public class ShuffleArray {

  private void shuffle(int[] numbers) {
    int n = numbers.length - 1;
    for (int i = 0; i < n; i++) // no swapping last element
    {
      // swap i`th value with randomly selected value from i to n-1 `th index
      Random rand = new Random();
      int swapWithIndex = i + rand.nextInt(n - i);

      int temp = numbers[swapWithIndex];
      numbers[swapWithIndex] = numbers[i];
      numbers[i] = temp;

      System.out.print(numbers[i] + ", ");
    }

    System.out.println(numbers[n]);
  }

  @Test
  public void shuffle() {
    int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8};
    shuffle(numbers);
  }
}
