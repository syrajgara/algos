https://people.cs.clemson.edu/~bcdean/dp_practice/

http://stackoverflow.com/questions/3592943/difference-between-back-tracking-and-dynamic-programming

There are two typical implementations of Dynamic Programming approach: bottom-to-top and top-to-bottom.

Top-to-bottom Dynamic Programming is nothing else than ordinary recursion, enhanced with memorizing the solutions for intermediate sub-problems. When a given sub-problem arises second (third, fourth...) time, it is not solved from scratch, but instead the previously memorized solution is used right away. This technique is known under the name memoization (no 'r' before 'i').

This is actually what your example with Fibonacci sequence is supposed to illustrate. Just use the recursive formula for Fibonacci sequence, but build the table of fib(i) values along the way, and you get a Top-to-bottom DP algorithm for this problem (so that, for example, if you need to calculate  fib(5) second time, you get it from the table instead of calculating it again).

In Bottom-to-top Dynamic Programming the approach is also based on storing sub-solutions in memory, but they are solved in a different order (from smaller to bigger), and the resultant general structure of the algorithm is not recursive. LCS algorithm is a classic Bottom-to-top DP example.

Bottom-to-top DP algorithms are usually more efficient, but they are generally harder (and sometimes impossible) to build, since it is not always easy to predict which primitive sub-problems you are going to need to solve the whole original problem, and which path you have to take from small sub-problems to get to the final solution in the most efficient way.

========
Dynamic programming is a method of solving complex problems by breaking them down into simpler steps. It is applicable to problems that exhibit the properties of overlapping subproblems which are only slightly smaller and optimal substructure.

========
- sum of this or that
- min/max of this or that

MEMOIZE -- Map<KEY_TYPE, Integer>