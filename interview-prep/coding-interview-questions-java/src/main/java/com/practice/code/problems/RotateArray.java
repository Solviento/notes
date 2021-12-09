package com.practice.code.problems;
// Given an array, rotate the array to the right by k steps, where k is non-negative.

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