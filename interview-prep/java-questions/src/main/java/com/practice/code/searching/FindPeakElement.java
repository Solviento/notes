/*A peak element is an element that is strictly greater than its neighbors.
Given an integer array nums, find a peak element, and return
its index. If the array contains multiple peaks, return the index to ANY of the peaks.
Key word is ANY
You may imagine that nums[-1] = nums[n] = -∞.
You must write an algorithm that runs in O(log n) time.

Example 1:
Input: nums = [1,2,3,1]
Output: 2
Explanation: 3 is a peak element and your function should return the index number 2.

Example 2:
Input: nums = [1,2,1,3,5,6,4]
Output: 5
Explanation: Your function can return either index number 1 where the peak element is 2, or index number 5 where the peak element is 6. */
package com.practice.code.searching;

import com.practice.code.runner.CodeRunner;

public class FindPeakElement implements CodeRunner {
    @Override
    public void run() {

    }

    // linear scan (n)
    public int findPeakElement(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1])
                return i;
        }
        return nums.length - 1;
    }

    // binary search (logn)
    public int findPeakElementBinarySearch(int[] nums) {
        return search(nums, 0, nums.length - 1);
    }
    public int search(int[] nums, int l, int r) {
        if (l == r)
            return l;
        int mid = (l + r) / 2;
        if (nums[mid] > nums[mid + 1])
            return search(nums, l, mid);
        return search(nums, mid + 1, r);
    }
}
