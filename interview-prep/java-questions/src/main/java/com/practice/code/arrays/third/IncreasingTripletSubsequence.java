/*Given an integer array nums, return true if there exists a
triple of indices (i, j, k) such that i < j < k
and nums[i] < nums[j] < nums[k]. If no such indices exists, return false.

Example 1:
Input: nums = [1,2,3,4,5]
Output: true
Explanation: Any triplet where i < j < k is valid.

Example 2:
Input: nums = [5,4,3,2,1]
Output: false
Explanation: No triplet exists.

Example 3:
Input: nums = [2,1,5,0,4,6]
Output: true
Explanation: The triplet (3, 4, 5) is valid because nums[3] == 0 < nums[4] == 4 < nums[5] == 6.*/

package com.practice.code.arrays.third;

import com.practice.code.runner.CodeRunner;

public class IncreasingTripletSubsequence implements CodeRunner {
    @Override
    public void run() {

    }

//    first_num = second_num = some very big number
//    for n in nums:
//                if n is smallest number:
//        first_num = n
//        else if n is second smallest number:
//        second_num = n
//        else n is bigger than both first_num and second_num:
//                # We have found our triplet, return True
//
//    # After loop has terminated
//    # If we have reached this point, there is no increasing triplet, return False

    public boolean increasingTriplet(int[] nums) {
        int first_num = Integer.MAX_VALUE;
        int second_num = Integer.MAX_VALUE;
        for (int n : nums) {
            if (n <= first_num) {
                first_num = n;
            } else if (n <= second_num) {
                second_num = n;
            } else {
                return true;
            }
        }
        return false;
    }
}
