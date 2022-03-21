/*Suppose an array of length n sorted in ascending
order is rotated between 1 and n times. For example,
the array nums = [0,1,4,4,5,6,7] might become:
[4,5,6,7,0,1,4] if it was rotated 4 times.
[0,1,4,4,5,6,7] if it was rotated 7 times.
Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1
time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
Given the sorted rotated array nums that may contain duplicates, return the minimum element of this array.
You must decrease the overall operation steps as much as possible.

Example 1:
Input: nums = [1,3,5]
Output: 1

Example 2:
Input: nums = [2,2,2,0,1]
Output: 0*/

package com.practice.code.sorting;

import com.practice.code.runner.CodeRunner;

public class FindMinimumInRotatedSortedArrayII implements CodeRunner {
    @Override
    public void run() {

    }

//    We keep two pointers, i.e. low, high which point to the lowest and highest boundary of our search scope.
//    We then reduce the search scope by moving either of pointers, according to various situations.
//    Usually we shift one of pointers to the mid point between low and high, (i.e. pivot = (low+high)/2),
//    which reduces the search scope down to half. This is also where the name of the algorithm comes from.
//    The reduction of the search scope would stop, either we find the desired element or the two pointers converge (i.e. low == high).
//    binary search variant
    public int findMin(int[] nums) {
        int startIndex = 0;
        int endIndex = nums.length - 1;

        while (startIndex < endIndex) {
            int midIndex = startIndex + (endIndex - startIndex) / 2;
            if (nums[midIndex] < nums[endIndex])
                endIndex = midIndex;
            else if (nums[midIndex] > nums[endIndex])
                startIndex = midIndex + 1;
            else
                endIndex -= 1;
        }
        return nums[startIndex];
    }
}
