package com.shabs.greedy;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * GREEDY - Pick activity that ENDs first
 *
 * NonOverlappingIntervals
 * select activities, that overlap in time, so that you can do most activities.
 * <p>
 * Greedily select activity that ends/finishes first,
 * then loop thru next activities in similar way
 * if activity starts before the previous end, ignore it
 */
public class ActivitySchedulingIntervalSelection {

  private class Interval implements Comparable<Interval> {
    public Integer start;
    public Integer end;

    public Interval(int s, int e) {
      start = s;
      end = e;
    }

    public void print() {
      System.out.println("[" + start + ", " + end + "]");
    }

    @Override
    public int compareTo(Interval o) {
      return end.compareTo(o.end);
    }
  }

  public int findMaxActivity(List<Interval> intervals) {
    Collections.sort(intervals);

    int countValidIntervals = 0;
    int previousEnd = 0;

    for (Interval i : intervals) {
      if (i.start >= previousEnd) {
        // we will use this interval
        i.print();
        countValidIntervals++;
        previousEnd = i.end;
      }
    }

    return countValidIntervals;
  }

  @Test
  public void test1() {
    Interval interval1 = new Interval(3, 4);
    Interval interval2 = new Interval(1, 2);
    Interval interval3 = new Interval(2, 3);
    Interval interval4 = new Interval(1, 3);

    List<Interval> intervals = new ArrayList<>();
    intervals.add(interval1);
    intervals.add(interval2);
    intervals.add(interval3);
    intervals.add(interval4);

    int expected = 3;
    int actual = findMaxActivity(intervals);

    Assert.assertEquals(actual, expected);
  }
}
