/*Given an array of integers nums sorted in non-decreasing order (increasing),
find the starting and ending position of a given target value.
If target is not found in the array, return [-1, -1].
You must write an algorithm with O(log n) runtime complexity.

Example 1:
Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]

Example 2:
Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]

Example 3:
Input: nums = [], target = 0
Output: [-1,-1]
*/

package com.practice.code.arrays.first;

import com.practice.code.runner.CodeRunner;


public class FirstLastPositionInteger implements CodeRunner {

    public void run() {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 10;
        int[] range = searchRange(nums, target);
        System.out.println("Array : " + "5,7,7,8,8,10 and target : " + target + " is found at positions: " +
                range[0] + " and " + range[1]);
    }

    // do two binary searches
    // initial binary search is run over the entire array as done in normal binary search
    // if first index is found, use that index to start the next binary search
    public int[] searchRange(int[] nums, int target) {
        // do binary searches for first appearance of target int and final appearance of target int (final i- first i + 1 = number of target element occurences)
        int firstIndex = firstIndex(nums, 0, nums.length - 1, target);
        if (firstIndex < 0) {
            return new int[]{-1, -1};
        }
        int secondIndex = finalIndex(nums, firstIndex, nums.length - 1, target);
        // if first index has been found, find last appearance of target int
        return new int[]{firstIndex, secondIndex};
    }

    // binary search with slight twist to find left most target element
    int firstIndex(int[] nums, int left, int right, int target) {
        if (right < left) {
            return -1;
        }
        int mid = left + (right - left) / 2; // int overflow protection
        // second condition (mid==0 || nums[mid-1]<x) is used to ensure that pointer finds leftmost index of target element
        if (nums[mid] == target && (mid == 0 || nums[mid - 1] < target)) {
            return mid;
        } else if (nums[mid] < target) {
            return firstIndex(nums, mid + 1, right, target);
        }
        else {
            return firstIndex(nums, left, mid - 1, target);
        }
    }

    // binary search with twist to find right most target element in array
    int finalIndex(int[] nums, int left, int right, int x) {
        if (right < left) {
            return -1;
        }
        int mid = left + (right - left) / 2;
        // condition modified to check if matched element is at end of array or right most matched element
        if (nums[mid] == x && (mid == nums.length - 1 || nums[mid + 1] > x)) {
            return mid;
        // current element too small, search right sub array
        } else if (nums[mid] < x) {
            return finalIndex(nums, mid + 1, right, x);
        }
        // current element matches but is not rightmost, search right sub array
        else if (nums[mid] == x) {
            return finalIndex(nums, mid + 1, right, x);
        }
        // current element is greater, search left sub array
        else {
            return finalIndex(nums, left, mid - 1, x);
        }
    }
}