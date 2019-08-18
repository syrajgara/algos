package com.shabs.datastructures.array;

/**
 * In a room of n people, find celebrity
 *
 * celebrity doesnt know anyone
 * everyone else knows celebrity
 */
public class Celebrity {

  public boolean knows(int a, int b) {
    return true; // return based on some values {0:1->true, 0:2->false ...}
  }

  public int findCelebrity(int numberOfPeople) {

    int assumedCelebrity = 0;
    for (int i = 1; i < numberOfPeople; i++) {
      if (knows(assumedCelebrity, i)) {
        // then we had the wrong assumption, since celebrity cannot know anyone
        // consider i to be the celebrity
        assumedCelebrity = i;
      } else {
        // assumedCelebrity doesnt know i -- so i himself cannot be a celebrity, skip him
      }
    }

    // above loop will eliminate everyone other than one person.

    // confirm the assumption, previous person not known by assumedCelebrity
    for (int i = 0; i < assumedCelebrity; i++) {
      // we already checked assumedCelebrity doesnt know anyone after him
      // need to do the same for before him
      if (knows(assumedCelebrity, i)) {
        return -1; // assumedCelebrity knows i -- so he cannot be the celebrity, hence no one is celebrity
      }
    }

    // so far we confirmed that assumedCelebrity doesnt know anyone
    // need to confirm if everyone knows him

    for (int i = 0; i < numberOfPeople; i++) {
      if (i == assumedCelebrity) {
        continue;
      }
      if (!knows(i, assumedCelebrity)) {
        return -1; // i doesnt know assumedCelebrity
      }
    }

    return assumedCelebrity;
  }
}
