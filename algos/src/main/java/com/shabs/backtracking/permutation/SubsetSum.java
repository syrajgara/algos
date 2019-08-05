package com.shabs.backtracking.permutation;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Subset sum problem is to find subset of elements that are selected from a given set
 * whose sum adds up to a given number K.
 * input is sorted
 * <p>
 * Subset (non-contiguous) sum = K - with set containing non-negative, unique and sorted numbers.
 * <p>
 * 1, 3, 4, 6, 10, 12 => 3 + 4 + 12 = 19
 * <p>
 * loop from current element to end of list
 * on every loop, either
 * - current element completes the sum
 * - current element is too big - and since list is sorted all elements after it is too big
 * - subtracting current element has some balance sum
 * - recurse for next element with balance sum, and if true we can confirm current element
 * - if balance sum not achievable by next elements, we cannot use current element as part of solution
 * - ignore it and move to next element and repeat the above.
 * <p>
 * Complexity:
 * loop thru n elements
 * for every loop, loop thru rest of the elements
 * == O(n^2)
 * <p>
 * http://www.geeksforgeeks.org/backttracking-set-4-subset-sum/
 */
public class SubsetSum {
  public SubsetSum() {
  }

  private static boolean solveSubset(int[] list, int index, int sum, List<Integer> solutionList) {
    if (index == list.length) {
      return false; // ran out of elements
    }

    for (int currIndex = index; currIndex < list.length; currIndex++) {
      // we are assuming that we will use this index, and then try out everything after this index
      int remainingSum = sum - list[currIndex];

      if (remainingSum < 0) {
        // adding this element is going to make sum negative,
        // and everyone after this element is larger than this element so they will also make the sum negative

        // backtrack and find another combination
        return false;
      }

      // we have found the sub-set that sums up to zero, if remainingSum == 0
      // OR lets try adding other elements
      if (remainingSum == 0 || solveSubset(list, currIndex + 1, remainingSum, solutionList)) {
        recordElement(list[currIndex], solutionList);

        return true;
      }

      // neither of the above worked - ignore current element, continue to next index
    }

    // we were not able to find the combination
    return false;
  }

  private static void recordElement(int element, List<Integer> solutionList) {
    solutionList.add(element);
  }

  @Test
  public void test() {
    int[] set = {4, 5, 7, 10, 12, 15, 18, 20, 35};
    int sum = 35;

    // SORT it to optimize the solution,
    // for example we can stop recurring once we cant fit in a specific number,
    // since every other number after that is bigger and wont fit.
    Arrays.sort(set);

    int index = 0; // start index
    List<Integer> solutionList = new ArrayList<>();
    solveSubset(set, index, sum, solutionList);

    System.out.print("SOLUTION : ");
    for (Integer solution : solutionList) {
      System.out.print(solution + " ");
    }
  }
}
