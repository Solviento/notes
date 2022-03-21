/*Given two integer arrays nums1 and nums2, return an array
of their intersection. Each element in the result must appear as
many times as it shows in both arrays and you may return the result in any order.

Example 1:
Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2,2]

Example 2:
Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [4,9]
Explanation: [9,4] is also accepted.*/

package com.practice.code.sorting;

import com.practice.code.runner.CodeRunner;

import java.util.Arrays;
import java.util.HashMap;

public class IntersectionOfTwoArraysII implements CodeRunner {
    @Override
    public void run() {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        int[] res = intersect(nums1, nums2);
        System.out.println(Arrays.toString(res));
    }

    // two linear scans with hashmap
    // t: n s: n
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }
        // int frequency map
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num1 : nums1) {
            frequencyMap.merge(num1, 1, (x,y) -> x+y);
        }

        // using larger array, decrement frequency map to determine how many times the duplicate element appears in the larger array
        int index = 0;
        for (int n : nums2) {
            int frequency = frequencyMap.getOrDefault(n, 0);
            if (frequency > 0) {
                nums1[index] = n;
                index++;
                frequencyMap.put(n, frequency - 1);
            }
        }
//        not sure about below line, maybe for certain input this works
//        return Arrays.copyOfRange(nums1, 0, index);
        return nums1;
    }

    // linear scan using pre-sorting
    // t: nlogn s: 1
    public int[] intersectSorting(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int nums1Index = 0;
        int nums2Index = 0;
        int k = 0;
        while (nums1Index < nums1.length && nums2Index < nums2.length) {
            if (nums1[nums1Index] < nums2[nums2Index]) {
                nums1Index++;
            } else if (nums1[nums1Index] > nums2[nums2Index]) {
                nums2Index++;
            } else {
                nums1[k++] = nums1[nums1Index++];
                nums2Index++;
            }
        }
        return Arrays.copyOfRange(nums1, 0, k);
    }

}
