package com.shabs.backtracking;

import java.util.Calendar;
import java.util.List;

/**
 * Given a list of users, find the first 3 timeslots when all of them are available
 * so that you can schedule a meeting for them.
 *
 * a method is given which will return open slots for user
 *
 * assume each slot is of an hour.
 *
 */
public class SchedulingMeeting {

  public static List<Integer> getSlot(Integer userId, Calendar date, int numSlots) {
    // assume implemented
    return null; // List<slotStartTime>
  }

  public List<Integer> getCommonSlots(List<Integer> userIds) {
    // for each user id, build a Set of open slots, by calling getSlot
    // Map<userId, Set<slots>>

    // loop thru all slots of the first user
    // call checkSlotForUser(userId, slotStartTime) for 2nd user
    // recursively call for other users
    // if all users ok - commit to that slot
    // else try again from 1st user's, next slot.

    return null;
  }
}
