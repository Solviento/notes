/*You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.

Return true if you can reach the last index, or false otherwise.

https://www.youtube.com/watch?v=Zb4eRjuPHbM
*/

package com.practice.code.arraysandstrings;

import com.practice.code.runner.CodeRunner;

public class JumpGame implements CodeRunner {

    public boolean canJump(int[] nums) {

        int lastGoodIndexPosition = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= lastGoodIndexPosition) {
                lastGoodIndexPosition = i;
            }
        }

        return lastGoodIndexPosition == 0;
    }

    @Override
    public void run() {

    }
}
