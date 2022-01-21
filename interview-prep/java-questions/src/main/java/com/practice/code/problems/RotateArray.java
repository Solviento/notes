/*Given an array, rotate the array to the right by k steps, where k is non-negative.

Example 1:

Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]*/

package com.practice.code.problems;

public class RotateArray {
    // Time: O(n) Space: O(n)
    public void rotate(int[] nums, int k) {
        int[] result = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            int cpy = nums[i];
            int rot = (i+k)%nums.length;        // (current iteration + k skips) % arr.length = new location for arr[i]
            result[rot] = cpy;
        }
        for(int i = 0; i < nums.length; i++){
            nums[i] = result[i];
        }
    }
}