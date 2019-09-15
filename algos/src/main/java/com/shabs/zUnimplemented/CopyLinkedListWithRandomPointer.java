package com.shabs.zUnimplemented;

/**
 * Linked list with node having additional pointer to a random node on the linked list
 * Do a deep copy in O(n) time
 *
 * In O(n) space
 * use hashmap to keep original node to clone node mappings
 *
 * In O(1) space
 * 1st pass, clone each node, stitch original node to its clone and the clone to the next of orginal
 * so that next() of each node is its new clone
 * 2nd pass, fix random pointer on all the clones
 * 3rd pass, re-stitch so that the 2 linked lists and now separate
 */
public class CopyLinkedListWithRandomPointer {
}
