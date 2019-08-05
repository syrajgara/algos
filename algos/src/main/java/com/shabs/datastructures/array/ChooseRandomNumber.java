package com.shabs.datastructures.array;

import org.testng.annotations.Test;

import java.util.Random;

public class ChooseRandomNumber {

  public static int[] chooseRandom(int[] input, int m) {
    int[] random = new int[m];

    for (int i = 0; i < m; i++) {
      //chose a random number between i and input.length-1
      Random rand = new Random();
      int target = rand.nextInt(input.length - 1 - i + 1);
      //pick and save the random number
      random[i] = input[target];

      // move (n-1-i)th element to target location - wiping out the target since we dont need it again
      input[target] = input[input.length - 1 - i];
    }

    return random;
  }

  @Test
  public void test() {
    int[] input = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    for (int i : chooseRandom(input, 5)) {
      System.out.println(i);
    }
  }
}
