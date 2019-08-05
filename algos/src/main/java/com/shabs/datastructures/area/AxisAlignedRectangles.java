package com.shabs.datastructures.area;

/**

 Describe an algorithm that takes an unsorted array of axis‐aligned rectangles and 
 returns any pair of rectangles that overlaps, if there is such a pair.  Axis‐aligned 
 means that all the rectangle sides are either parallel or perpendicular to the x‐ and 
 y‐axis.  You can assume that each rectangle object has two variables in it: the x‐y 
 coordinates of the upper‐left corner and the bottom‐right corner.
 Good Answer: Create a sorted array of the x coordinates of the left and right edges of 
 the rectangles.  Then, use a "scanline" to move from left to right through the 
 rectangles.  Keep a binary search tree containing the y coordinates of the top and 
 bottom edges of the rectangles that overlap the scanline.  For each element of the 
 array, check whether it is a left or right edge.  If it is a right edge, remove the 
 corresponding top and bottom edges from the BST.  If it is a left edge, search the BST 
 for rectangles that overlap the current rectangle; if there is one, return the overlap.  
 Then, add the y coordinates of the top and bottom edges of the rectangle to the BST.  
 The search takes O(n log n) time, since it takes O(n log n) time to sort the rectangles 
 and each of the 2n iterations takes O(log n) time.

 */
public class AxisAlignedRectangles {
}
