package com.shabs.datastructures.search;

/**
 * Binary search for a value in an array
 * Variation: array has duplicate values, get the largest index for the value
 * <p>
 * - binary search for the value
 * - once found - store its index
 * - continue binary searching for the value in the upper array to find the largest index of that value
 * <p>
 * ex: {1,1,2,2,2,2,5} - binary search for value 2
 * normal binary search will find 2 at index 3 - then continue to binary search it again till you reach index 5
 */
public class BinarySearchWithLargestIndex {
}
