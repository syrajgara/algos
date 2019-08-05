package com.shabs.datastructures.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given a list of movie duration and total length of a flight
 * Figure out if there is any combination of movie durations,
 * which will allow you to watch exactly 2 movies on that flight.
 *
 //{10,30,20,4,5}
 //24 mins

 //{4,5,10,20,30}
 // 4+20 = 24 = true

 //{4,5,10,20,30}
 // 14 -- 20mins false

 //{4,5,10,20,30}
 // 40 => 10+30 -- true

 //{4,5,10,20,30}
 // invalid -- 0
 // 100
 */
public class SumTwoMovieDuration {

  public boolean find2MoviesWithSet(int duration, int[] lm) {
    Set<Integer> delta = new HashSet<>();

    for (int i = 0; i < lm.length; i++) { // O(n)
      if (delta.contains(lm[i])) {
        return true;
      }

      delta.add(duration - lm[i]);
    }

    return false;
  }

  public boolean find2Movies(int duration, int[] lm) {

    Arrays.sort(lm); // O(n log n)

    int l = 0;
    int r = lm.length - 1;

    while (l < r) {
      if ( lm[l] + lm[r] == duration) {
        return true;
      } else if ( lm[l] + lm[r] > duration ) {
        r--;
      } else {
        l++;
      }
    }

    return false;
  }
}

