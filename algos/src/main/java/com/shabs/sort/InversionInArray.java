package com.shabs.sort;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * MERGE SORT - Divide And Conquer
 *
 * count number of inversions in a given array of ints.
 * <p>
 * Use merge sort and keep track of inversions.
 * count inversion on the left, inversions on the right, inversions between left and right
 * <p>
 * for inversion between left and right
 * - do a sort of the arrays during the merge
 * - if left element is < right element - no increment of inversion
 * - else, increment inversions based on number of elements left in left side
 * <p>
 * <p>
 * ex - {1,6,2,4,8,3}
 * output 5  - {6,2}, {6,4},{6,3}, {8,3} and {4,3}
 */
public class InversionInArray {
  private final Logger logger = LoggerFactory.getLogger(InversionInArray.class);

  private int inversions(int[] A, int l, int r) {
    if (l == r) {
      return 0;
    }

    int m = l + (r - l) / 2;

    int leftInversions = inversions(A, l, m);
    int rightInversions = inversions(A, m + 1, r);
    int splitInversions = splitInversions(A, l, m, r);

    return leftInversions + rightInversions + splitInversions;
  }

  private int splitInversions(int[] A, int l, int m, int r) {
    // doing a merge sort, when counting inversions, so that next level up, some iterations can be saved.

    int inversions = 0;
    int[] B = Arrays.copyOfRange(A, l, r + 1); // right index exclusive in copy
    int Aindex = l;

    int BLeftFirstIndex = 0;
    int BLeftLastIndex = m - l;

    int BRightFirstIndex = BLeftLastIndex + 1;
    int BRightLastIndex = r - l;

    while (BLeftFirstIndex <= BLeftLastIndex && BRightFirstIndex <= BRightLastIndex) {
      if (B[BLeftFirstIndex] <= B[BRightFirstIndex]) {
        A[Aindex] = B[BLeftFirstIndex];
        BLeftFirstIndex++;
      } else {
        // this is where inversion happened, BLeft was greater than BRight.
        // NOTE: all number in BLeft from this point onwards is more than that in BRight
        // - so add for all of them
        // - basically adding inversion for all number in BLeft, corresponding to BRightFirstIndex
        int increaseInInversion = BLeftLastIndex - BLeftFirstIndex + 1;
        inversions += increaseInInversion;

        logger.info("inversion for " + B[BLeftFirstIndex] + " " + B[BRightFirstIndex] + " increaseInInversion by " + increaseInInversion);

        A[Aindex] = B[BRightFirstIndex];
        BRightFirstIndex++;
      }
      Aindex++;
    }

    // copy leftovers from BLeft
    // if there is leftovers from BRight they are already in the right place
    // - and no inversions with them
    while (BLeftFirstIndex <= BLeftLastIndex) {
      A[Aindex] = B[BLeftFirstIndex];
      BLeftFirstIndex++;
      Aindex++;
    }

    return inversions;
  }

  @Test
  public void inversions() {
    int[] A = {1, 6, 2, 4, 8, 3};
    int expected = 5;

    int actual = inversions(A, 0, A.length - 1);

    Assert.assertEquals(actual, expected);
  }
}
