package com.shabs.greedy;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Game - Teemo Attacking
 *
 * GREEDY - sort interval by start time
 * given list of intervals, merge intervals if they overlap, no merge if it doesnt overlap
 * <p>
 * [1,3],[2,6],[8,10],[15,18] should return [1,6],[8,10],[15,18]
 * <p>
 * - sort intervals based on their starting time
 * - iterate and if the next start is before the previous end - merge the intervals
 * - else close previous interval and start a new one
 */
public class MergeIntervals {

  private List<Interval<Integer>> merge(List<Interval<Integer>> list) {
    Collections.sort(list);
    print(list);

    List<Interval<Integer>> mergedList = new ArrayList<>();

    if (list.isEmpty()) {
      return mergedList;
    }

    Integer previousStart = -1;
    Integer previousEnd = -1;

    for (Interval<Integer> i : list) {
      if (i.start > previousEnd) {
        if (previousStart != -1) {
          mergedList.add(new Interval<>(previousStart, previousEnd));
        }

        // start new interval
        previousStart = i.start;
        previousEnd = i.end;
      } else {
        // merging
        previousEnd = Math.max(previousEnd, i.end);
      }
    }

    // final interval
    mergedList.add(new Interval<>(previousStart, previousEnd));

    return mergedList;
  }

  private void print(List<Interval<Integer>> list) {
    for (Interval<Integer> i : list) {
      i.print();
    }
    System.out.println("-----------------");
  }

  private class Interval<T extends Comparable<T>> implements Comparable<Interval<T>> {
    public T start;
    public T end;

    public Interval(T start, T end) {
      this.start = start;
      this.end = end;
    }

    @Override
    public int compareTo(Interval<T> o) {
      return start.compareTo(o.start);
    }

    public void print() {
      System.out.println("[" + start + ", " + end + "]");
    }
  }

  @Test
  public void test() {
    Interval<Integer> interval1 = new Interval<>(1, 4);
    Interval<Integer> interval2 = new Interval<>(2, 2);
    Interval<Integer> interval3 = new Interval<>(8, 10);
    Interval<Integer> interval4 = new Interval<>(15, 18);

    List<Interval<Integer>> list = new ArrayList<>();
    list.add(interval3);
    list.add(interval4);
    list.add(interval1);
    list.add(interval2);

    List<Interval<Integer>> mergedList = merge(list);
    print(mergedList);
  }

  @Test
  public void testEmpty() {
    List<Interval<Integer>> list = new ArrayList<>();

    List<Interval<Integer>> mergedList = merge(list);
    print(mergedList);
  }
}
