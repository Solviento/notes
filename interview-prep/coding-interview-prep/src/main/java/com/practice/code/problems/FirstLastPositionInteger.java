package com.practice.code.problems;
// Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
// Your algorithm's runtime complexity must be in the order of O(log n).
// If the target is not found in the array, return [-1, -1].
// Input: nums = [5,7,7,8,8,10], target = 8
// Output: [3,4]
class FirstLastPositionInteger {
    public int[] searchRange(int[] nums, int target) {
        // do binary searches for first appearance of target int and final appearance of target int (final i- first i + 1 = number of target element occurences)
        int firstIndex = firstIndex_(nums, 0, nums.length-1, target);
        if(firstIndex < 0){
            return new int[]{-1,-1};
        }
        int secondIndex = finalIndex(nums, firstIndex, nums.length-1, target);
        // if first index has been found, find last appearance of target int
        return new int[]{firstIndex,secondIndex};
    }
    int firstIndex_(int[] nums, int left, int right, int x){
        if(right < left){
            return -1;
        }
        int mid = left+(right-left)/2;
        // int mid = (left+right)/2;
        // THIS WILL RETURN FIRST IF THERE IS A MATCH & MID=0 OR NUMS[MID-1] IS LESS THAN X
        if(nums[mid] == x && (mid == 0 || nums[mid-1] < x)){
            return mid;
        }
        else if(nums[mid] < x){
            return firstIndex_(nums, mid+1, right, x);
        }
        // MAY RETURN SEPARATE INTS BUT WILL NOT BE SAVED INTO THE VARIABLE OF FIRSTINDEX
        else{
            return firstIndex_(nums, left, mid-1, x);
        }
    }
    int finalIndex(int[] nums, int left, int right, int x){
        if (right < left){
            return -1;      // no separate last appearance of x in the array after firstIndex
        }
        int mid = left+(right-left)/2;
        // int mid = (right+left)/2;
        if(nums[mid] == x && (mid == nums.length-1 || nums[mid+1] > x)){
            return mid;
        }
        else if (nums[mid] < x){
            return finalIndex(nums, mid+1, right, x);
        }
        // takes care of certain edge cases where nums = [2,2] or [3,3,3]
        // mid is BIASED TO THE LEFT, if it lands on index 0 then you must forcibly move it to the right IF THERE IS STILL REMAINING elements to the right
        else if (nums[mid] == x && mid < nums.length){
            return finalIndex(nums, mid+1, right, x);
        }
        else{
            return finalIndex(nums, left, mid-1, x);
        }
    }
}