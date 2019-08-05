package com.shabs.design;

/**
 Describe  a  technique  to  identify  a  "leader"  among  a  group  of  10  identical  servers 
 that  are  all connected  to  every  other  server.
   There are no prior distinguishing 
 characteristics of any of them and the same program to identify the leader starts 
 running on all of them at the same time.  After an answer is given, ask how much 
 network traffic it requires and, if "ties" are possible, ask how you can break ties.
 Good Answer: Have each server wait a random amount of time and then say "I'm it."  
 The "I'm it" announcement is time‐stamped, and the computer that time‐stamped its 
 announcement first is elected the leader.  This approach requires sending out 9 
 messages.  If there is a tie, the computers can repeat the procedure.
 Note that other answers are possible, but every correct answer will use randomness 
 in some way.

 */
public class LeaderElection {
}
