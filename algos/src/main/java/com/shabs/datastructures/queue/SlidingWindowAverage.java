package com.shabs.datastructures.queue;

/**
 * Sliding window of size N, which keeps numbers from an input stream.
 * standing query to output the current average of the sliding window.
 * when a newElement arrives, the oldestElement is dropped to keep N elements in the window.
 * to compute the current average - you could take average of all elements now in the window.
 * window could be so large that you cannot store the elements in the memory/disk
 *
 * So more efficient, keep average A in memory and then just compute [A = A + (newElement - oldestElement)/N]
 * — so you don’t have to recompute sum of all elements in N.
 */
public class SlidingWindowAverage {
}
