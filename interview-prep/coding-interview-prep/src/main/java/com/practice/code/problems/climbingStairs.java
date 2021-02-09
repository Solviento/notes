package com.practice.code.problems;
// You are climbing a stair case. It takes n steps to reach to the top.
// Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
// Note: Given n will be a positive integer.
// Input: 3
// Output: 3
// Explanation: There are three ways to climb to the top.
// 1. 1 step + 1 step + 1 step
// 2. 1 step + 2 steps
// 3. 2 steps + 1 step
class climbingStairs {
  public int climbStairs(int n) {
    // n=0, distinct=0, n=1, distinct=1, n=2, distinct=2
    if (n < 3) {
      return n;
    }
    // memoize[n] is the sum of the previous two indexes, prevents repeating work
    // [1] [2] [3] [5] [8] [13] [21]
    // its very helpful to draw out the recursive tree on how memoization would help in this scenario
    // ALSO HELPS TO DRAW OUT THE POSSIBLE STEP COMBINATIONS FOR 1, 2, 3, 4, 5 TO SEE THE PATTERNS OF WHY WE DO MEMOIZE[I] = MEM[I-2] + MEM[I-1]
    int[] memoize = new int[n];
    memoize[0] = 1;
    memoize[1] = 2;
    for (int i = 2; i < n; i++) {
      memoize[i] = memoize[i - 2] + memoize[i - 1];
    }
    return memoize[n - 1];
  }
}