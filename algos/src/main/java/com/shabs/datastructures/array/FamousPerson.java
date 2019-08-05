package com.shabs.datastructures.array;

/**
 Find Famous Person
 N people in the room
 0 -> N-1

 1) everyone else knows this person
 2) this person knows no one else in the room

 bool knows(int id1, int id2);  return true if id1 knows id2, return false if id1 doesn't know id2; O(1)

 2 Pointers - start/end - on every comparision eliminate one person.
 */
public class FamousPerson {

  int findFamousPerson2(int N) {
    int i = 0;
    int j = N-1;

    // 2Pointers - eliminate *not* famous
    while (i < j) {
      if (knows(i, j)) {
        i++; // i is not famous
      } else {
        j--;
      }
    }

    return i;
  }

  public int findFamousPerson(int N) {
    for (int i = 0; i < N-1; i++) {
      // person we are checking

      boolean thisPersonKnowsOther = false;
      boolean otherPersonKnowsThis = true;

      for (int j = 0; j < N-1; j++) {
        // friend
        if (i == j) {
          continue;
        }

        thisPersonKnowsOther = knows(i, j);
        if (thisPersonKnowsOther) {
          break;
        }

        otherPersonKnowsThis = knows(j, i);
        if (!otherPersonKnowsThis) {
          break;
        }
      }

      if (!thisPersonKnowsOther && otherPersonKnowsThis) {
        return i; // found famous
      }
    }

    return -1;
  }

  private boolean knows(int i, int j) {
    return false;
  }
}
