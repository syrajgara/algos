package com.shabs.datastructures.array;

/**
 * Data Structure which can tell you if an element
 * 1. is definitely not in the set
 * 2. is maybe in the set
 * - fronts the actual storage (database/file)
 * - uses multiple hash functions
 * - (FNV - https://en.wikipedia.org/wiki/Fowler%E2%80%93Noll%E2%80%93Vo_hash_function)
 * - (Murmur - https://en.wikipedia.org/wiki/MurmurHash)
 *
 * Example using a bit map
 * - set bits corresponding to couple of different hash functions on the input
 * - if all these bits are set when asking for the input, you know that the set *might* have the input
 * - if these bits not set, you definitely know that input not part of the set
 *
 * https://llimllib.github.io/bloomfilter-tutorial/
 */
public class BloomFilter {
}
