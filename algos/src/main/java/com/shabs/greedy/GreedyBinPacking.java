package com.shabs.greedy;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Greedy Solution - Not Optimal - BinPacking is NP Hard to get optimal solution.
 * <p>
 * fit all the items given, in bins of size S.
 * figure out the min number of bins needed. Since using greedy, solution might not be optimal.
 * <p>
 * - item list with sizes given, sizes could be duplicate
 * - bin size is given
 * <p>
 * - keep list of bins, add new bins as necessary
 * - to get better assignments, *SORT* items in desc size
 * <p>
 * - loop thru the items
 * - greedily select a bin that will fit the item, or create a new bin
 * - reduce available size of the bin as you add items to it.
 */
public class GreedyBinPacking {
  public GreedyBinPacking() {
  }

  public static int binPack(int[] itemSizes, int binSize) {
    List<Integer> bins = new ArrayList<>();

    for (int itemSize : itemSizes) {
      boolean ableToFit = false;

      // checkout all the bins in order - and greedily fit into the first bin possible.
      for (int i = 0; i < bins.size(); i++) {
        int newBinSize = bins.get(i) + itemSize;
        if (newBinSize <= binSize) {
          // able to fit it ... so put it in
          System.out.println("FIT " + itemSize + " in OLD BIN " + i + " Size=" + newBinSize);
          bins.set(i, newBinSize);
          ableToFit = true;
          break;
        }
      }

      // could not fit in any existing bin, create a new bin
      if (!ableToFit) {
        bins.add(itemSize);
        System.out.println("FIT " + itemSize + " in * NEW BIN " + (bins.size() - 1) + " Size=" + itemSize);
      }
    }

    return bins.size();
  }

  @Test
  public void test() {
    // weights for bin packing
    // sort it in decending order
    int[] itemSizes = {22, 18, 15, 13, 11, 10, 7, 4, 2, 1, 1};
    int binSize = 25; // assuming this is greater that the largest itemSize

    // return number of bins needed to fit all these objects.
    // start with one bin, add additional bins only if already available bin cannot fit the new object.
    binPack(itemSizes, binSize);
  }
}
