package com.shabs.datastructures.string;

/**
 * A dictionary of words is given. When a word is given, find all dict words that are its anagram.
 * (you can pre-compute and store)
 *
 * SOLN
 * for every word in dictionary, sort all chars of that word, use that as key and value as word itself
 * and put in a hashmap.
 * for every key you will end up with all valid dictionary words that are of than anagram
 *
 * when word is given, sort its chars and lookup as key on the pre-computed hashmap
 */
public class AnagramDictionary {
}
