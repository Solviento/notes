/*Given two sorted arrays nums1 and nums2 of size m
and n respectively, return the median of the two sorted arrays.
The overall run time complexity should be O(log (m+n)).

Example 1:
Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000
Explanation: merged array = [1,2,3] and median is 2.

Example 2:
Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.50000
Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.

https://redquark.org/leetcode/0004-median-of-two-sorted-arrays/
*/

package com.practice.code.sorting;

import com.practice.code.runner.CodeRunner;

public class MedianOfTwoSortedArrays implements CodeRunner {

    @Override
    public void run() {

    }

    public double findMedianSortedArraysRecursive(int[] array1, int[] array2) {
        int length1 = array1.length;
        int length2 = array2.length;
        int mid1 = (length1 + length2 + 1) / 2;
        int mid2 = (length1 + length2 + 2) / 2;
        // [      ] m
        // [      ] n
        // mid1 = [       ] + [       ] = [           ] + 1 / 2
        return (findKth(array1, 0, length1, array2, 0, length2, mid1) + findKth(array1, 0, length1, array2, 0, length2, mid2)) / 2;
    }

    private double findKth(int[] array1, int s1, int m, int[] array2, int s2, int n, int k) {
        if (m > n) {
            return findKth(array2, s2, n, array1, s1, m, k);
        }
        if (m == 0) {
            return array2[s2 + k - 1];
        }
        if (k == 1) {
            return Math.min(array1[s1], array2[s2]);
        }

        int i = Math.min(m, k / 2);
        int j = Math.min(n, k / 2);
        if (array1[s1 + i - 1] < array2[s2 + j - 1]) {
            return findKth(array1, s1 + i, m - i, array2, s2, n, k - i);
        } else {
            return findKth(array1, s1, m, array2, s2 + j, n - j, k - j);
        }
    }

    // second approach
    public double findMedianSortedArraysIterative(int[] nums1, int[] nums2) {
        // Check if num1 is smaller than num2
        // If not, then we will swap num1 with num2
        if (nums1.length > nums2.length) {
            return findMedianSortedArraysIterative(nums2, nums1);
        }
        // Lengths of two arrays
        int m = nums1.length;
        int n = nums2.length;
        // Pointers for binary search
        int start = 0;
        int end = m;
        // Binary search starts from here
        while (start <= end) {
            // Partitions of both the array
            int partitionNums1 = (start + end) / 2;
            int partitionNums2 = (m + n + 1) / 2 - partitionNums1;
            // Edge cases
            // If there are no elements left on the left side after partition
            int maxLeftNums1 = partitionNums1 == 0 ? Integer.MIN_VALUE : nums1[partitionNums1 - 1];
            // If there are no elements left on the right side after partition
            int minRightNums1 = partitionNums1 == m ? Integer.MAX_VALUE : nums1[partitionNums1];
            // Similarly for nums2
            int maxLeftNums2 = partitionNums2 == 0 ? Integer.MIN_VALUE : nums2[partitionNums2 - 1];
            int minRightNums2 = partitionNums2 == n ? Integer.MAX_VALUE : nums2[partitionNums2];
            // Check if we have found the match
            if (maxLeftNums1 <= minRightNums2 && maxLeftNums2 <= minRightNums1) {
                // Check if the combined array is of even/odd length
                if ((m + n) % 2 == 0) {
                    return (Math.max(maxLeftNums1, maxLeftNums2) + Math.min(minRightNums1, minRightNums2)) / 2.0;
                } else {
                    return Math.max(maxLeftNums1, maxLeftNums2);
                }
            }
            // If we are too far on the right, we need to go to left side
            else if (maxLeftNums1 > minRightNums2) {
                end = partitionNums1 - 1;
            }
            // If we are too far on the left, we need to go to right side
            else {
                start = partitionNums1 + 1;
            }
        }
        // If we reach here, it means the arrays are not sorted
        throw new IllegalArgumentException();
    }
}
