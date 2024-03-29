/*Suppose an array of length n sorted in ascending order is
rotated between 1 and n times. For example, the array
nums = [0,1,4,4,5,6,7] might become:
[4,5,6,7,0,1,4] if it was rotated 4 times.
[0,1,4,4,5,6,7] if it was rotated 7 times.
Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1
time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
Given the sorted rotated array nums that may contain duplicates, return the
minimum element of this array.
You must decrease the overall operation steps as much as possible.

Example 1:
Input: nums = [1,3,5]
Output: 1

Example 2:
Input: nums = [2,2,2,0,1]
Output: 0*/
package com.practice.code.searching;

import com.practice.code.runner.CodeRunner;

public class FindMinimumInRotatedSortedArrayII implements CodeRunner {
    @Override
    public void run() {

    }

    // t: o(logn) s: o(1)
    public int findMin(int[] nums) {
        int startIndex = 0;
        int endIndex = nums.length - 1;
        while (startIndex < endIndex) {
            int pivotElement = startIndex + (endIndex - startIndex) / 2;
            if (nums[pivotElement] < nums[endIndex]) {
                endIndex = pivotElement;
            } else if (nums[pivotElement] > nums[endIndex]) {
                startIndex = pivotElement + 1;
            }
            // we found a match, end search now (due to duplicates in array)
            else {
                endIndex -= 1;
            }
        }
        return nums[startIndex];
    }
}
