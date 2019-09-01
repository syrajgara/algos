package com.shabs.zUnimplemented;

/**
 Describe  a  stack  data structure  that  supports  "push",  "pop",  and  "find minimum" 
 operations.  

 have to keep a separate stack for minima, push to it if current node smaller than top.
 when poping, if top is same as current pop from minima stack as well.

 "Find minimum" returns the smallest element in the stack.
 Good Answer: Store two stacks, one of which contains all of the items in the stack 
 and one of which is a stack of minima.  To push an element, push it onto the first 
 stack.  Check whether it is smaller than the top item on the second stack; if so, push 
 it onto the second stack.  To pop an item, pop it from the first stack.  If it is the top 
 element of the second stack, pop it from the second stack.  To find the minimum 
 element, simply return the element on the top of the second stack.  Each operation 
 takes O(1) time
 */
public class MinimumStack {
}
