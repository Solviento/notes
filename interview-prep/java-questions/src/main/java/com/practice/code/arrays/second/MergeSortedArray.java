/*You are given two integer arrays nums1 and nums2,
sorted in non-decreasing order, and two integers m and n,
representing the number of elements in nums1 and nums2 respectively.

Merge nums1 and nums2 into a single array sorted in non-decreasing order.

The final sorted array should not be returned by the function,
but instead be stored inside the array nums1.
To accommodate this, nums1 has a length of m + n, where the
first m elements denote the elements that should be merged,
and the last n elements are set to 0 and should
be ignored. nums2 has a length of n.

Example 1:
Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
Output: [1,2,2,3,5,6]
Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.*/

package com.practice.code.arrays.second;

import com.practice.code.runner.CodeRunner;

import java.util.Arrays;

public class MergeSortedArray implements CodeRunner {

    @Override
    public void run() {
        int[] nums1 = new int[]{1,2,3,0,0,0};
        int[] nums2 = new int[]{2,5,6};
        merge(nums1, 3, nums2, 3);
        System.out.println("Merged num array: " + Arrays.toString(nums1));
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int leftPointer = m - 1;
        int rightPointer = m + n - 1;
        int thirdPointer = n - 1;
        // bubble largest elements to the right
        while (thirdPointer >= 0 && leftPointer >= 0) {
            int n1 = nums1[leftPointer];
            int n2 = nums2[thirdPointer];
            if (n2 >= n1) {
                nums1[rightPointer] = n2;
                thirdPointer--;
                rightPointer--;
            } else {
                nums1[rightPointer] = n1;
                nums1[leftPointer] = n2;
                rightPointer--;
                leftPointer--;
            }
        }
        // if nums1 array is empty (no elements), we just fill in with nums2 arr
        while (thirdPointer >= 0) {
            nums1[thirdPointer] = nums2[thirdPointer];
            thirdPointer--;
        }
    }

    // 1 2 3 0 0 0
    //     l     r
    // 2 5 6
    //     t
    // 1 2 3 0 0 6
    //     l   r
    // 2 5 6
    //   t
    // 1 2 3 0 5 6
    //     l r
    // 2 5 6
    // t
    // 1 2 2 3 5 6
    //   l r
    // 2 5 6
    // t
    // 1 2 2 3 5 6
    //   lr
    // 2 5 6
    //
}