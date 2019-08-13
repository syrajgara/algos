package com.shabs.backtracking.permutation;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * given a Set of numbers, create multiple sets with subsets/permutations of the original set.
 * <p>
 * if inputs = [1,2,3]
 * outputs = [
 * set =>
 * set => 1,
 * set => 1, 2,
 * set => 1, 2, 3,
 * set => 1, 3,
 * set => 2,
 * set => 2, 3,
 * set => 3,
 * ]
 * <p>
 * - take one element, recurse thru the others
 * - each call needs to give input, index from where to start, previous state, collection of all results
 */
public class Subsets {

  private List<List<Integer>> subsets(List<Integer> input) {
    List<List<Integer>> output = new ArrayList<>();

    createSubset(input, 0, new ArrayList<>(), output);

    return output;
  }

  private void createSubset(List<Integer> input, int startIndex, List<Integer> previousList, List<List<Integer>> output) {

    // save previous list as one of the output subset
    output.add(new ArrayList<>(previousList));

    for (int i = startIndex; i < input.size(); i++) {
      // add current element to previous list - augment for this iteration
      previousList.add(input.get(i));

      // create subset for rest of the elements - always work on next elements,
      // previous already taken care of
      createSubset(input, i + 1, previousList, output);

      // backtrack - undo adding current element
      previousList.remove(previousList.size() - 1);
    }
  }

  private void print(List<List<Integer>> lists) {
    for (List<Integer> list : lists) {
      System.out.print("set => ");
      for (Integer i : list) {
        System.out.print(i + ", ");
      }
      System.out.println();
    }
  }

  @Test
  public void test() {
    List<Integer> input = Arrays.asList(1, 2, 3);

    List<List<Integer>> output = subsets(input);
    print(output);
  }
}
