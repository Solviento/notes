/*You are climbing a staircase. It takes n steps to reach the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?*/

package com.practice.code.problems;

import com.practice.code.runner.CodeRunner;

public class ClimbingStairs implements CodeRunner {
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

    @Override
    public void run() {
        int s = 3;
        System.out.println("How many step combos (1 step/2 step) can be with a staircase of " + s + " steps?");
        System.out.println(climbStairs(s));
        ;
    }
}