/*You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n,
representing the number of elements in nums1 and nums2 respectively.

Merge nums1 and nums2 into a single array sorted in non-decreasing order.

The final sorted array should not be returned by the function, but instead be stored inside the array nums1.
To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be merged,
and the last n elements are set to 0 and should be ignored. nums2 has a length of n.

Example 1:

Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
Output: [1,2,2,3,5,6]
Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.*/

package com.practice.code.arrays.second;

import com.practice.code.runner.CodeRunner;

public class MergeSortedArray implements CodeRunner {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // 1 2 3 0 0 0
        //     lP    rP
        // 2 5 6
        //     tP
        int lPointer = m - 1;
        int rPointer = m + n - 1;
        int thirdPointer = n - 1;
        while (thirdPointer >= 0 && lPointer >= 0) {
            int n2 = nums2[thirdPointer];
            int n1 = nums1[lPointer];
            if (n2 >= n1) {
                nums1[rPointer] = n2;
                thirdPointer--;
                rPointer--;
            } else {
                nums1[rPointer] = n1;
                nums1[lPointer] = n2;
                rPointer--;
                lPointer--;
            }
        }
        // if nums1 array is empty (no elements), we just fill in with nums2 arr
        while (thirdPointer >= 0) {
            nums1[thirdPointer] = nums2[thirdPointer];
            thirdPointer--;
        }
    }

    @Override
    public void run() {

    }
}