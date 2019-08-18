package com.shabs.datastructures.array;

import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * Three Sum
 * 2 Pointers
 * <p>
 * Find all combination of three elements that add upto zero.
 * <p>
 * - Sort array
 * - for each element
 * - do two sum on the rest to get the sum equal to the element
 *
 * {-5, ... , -2, m, ..., 7, ..., n}
 * when testing for -5, we would have found {-2, 7},'
 * so when testing -2, no need to test {-5 ... 7},
 * just need to test {m ... n}
 */
public class SumThree {

  private void calc(int[] input) {

    Arrays.sort(input);

    // dont consider last 2 elements in this loop??
    for (int i = 0; i < input.length - 2; i++) {
      int lowIndex = i + 1;
      int highIndex = input.length - 1;

      while (lowIndex < highIndex) {
        int sum = input[lowIndex] + input[highIndex];

        if (sum == -input[i]) {
          //found match
          System.out.println(input[i] + " + " + input[lowIndex] + " + " + input[highIndex]);
          break;
        }

        if (sum < -input[i]) {
          lowIndex++;
        } else {
          highIndex--;
        }
      }
    }
  }

  @Test
  public void test() {
    int[] input = {-1, 0, 1, 2, -1, -4};

    // output
    // -1 + -1 + 2
    // -1 + 0 + 1

    calc(input);
  }
}
