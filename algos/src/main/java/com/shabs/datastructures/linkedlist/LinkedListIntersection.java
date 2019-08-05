package com.shabs.datastructures.linkedlist;

/**
 * 2 POINTER
 * Given 2 Linked list determine if they intersect at some node.
 * <p>
 * A:          a1 → a2
 * .                   ↘
 * .                     c1 → c2 → c3
 * .                   ↗
 * B:     b1 → b2 → b3
 * <p>
 * - approach#1: count the length of both linked lists
 *   and move pointer on each till they are at same node from the end
 * - better: start 2 pointers A and B, once one reaches end (suppose A) -
 *   start B' at beginning of the other list, continue till B reaches end.
 *   now, start A' - B' already moved to location at equal length of listA.
 * - rest same for both of above
 * - advance each by one and check if they match
 * - if you reach end (null) they didnt intersect
 */
public class LinkedListIntersection {

}
