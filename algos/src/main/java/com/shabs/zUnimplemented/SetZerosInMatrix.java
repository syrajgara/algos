package com.shabs.zUnimplemented;

/**
 * Zero out a column or row if there is zero in that column or row.
 * <p>
 * - go thru the matrix and keep track of which columns/rows to zero out, and then do a second pass to zero it out.
 * - instead of using separate array, (since you dont want zero out element that hasnt been looked at yet)
 * - use the first row to keep track of which cols to zero out (when you are going thru [2 - n] rows)
 * - use the first col to keep track of which rows to zero out (when you are going thru [2 - m] cols)
 * - since first row and col are already correctly zeroed out - do not redo these on 2nd pass.
 * - if element (0, 0) is zero, do a 3rd pass to zero out the 1st row and 1st col
 */
public class SetZerosInMatrix {
}
