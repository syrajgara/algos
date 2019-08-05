package com.shabs.dynamic.min_max_imize;

/**
 * Dynamic - Memoize
 * Each house has certain amount of cash
 * If you rob a house, you cannot rob the next one in sequence - have to skip atleast one house
 * <p>
 * - if you rob a house - your cash goes up with that house's amount - but now you can rob only this+2 house
 * - if not - you can rob the this+1 house
 * - take max of these 2 amounts
 * - memoize the cash - else wont be able to run for large number of house
 */
public class MaxWeightIndependentSet_HouseRobber {
}
