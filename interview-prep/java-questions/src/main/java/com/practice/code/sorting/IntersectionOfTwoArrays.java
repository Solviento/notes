/*Given two integer arrays nums1 and nums2, return an array
of their intersection. Each element in the result must
be unique and you may return the result in any order.

Example 1:
Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]

Example 2:
Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4]
Explanation: [4,9] is also accepted.*/
package com.practice.code.sorting;

import com.practice.code.runner.CodeRunner;

import java.util.Arrays;
import java.util.HashSet;

public class IntersectionOfTwoArrays implements CodeRunner {
    @Override
    public void run() {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        int[] res = intersection(nums1, nums2);
        System.out.println(Arrays.toString(res));
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        for (Integer n : nums1) {
            set1.add(n);
        }
        HashSet<Integer> set2 = new HashSet<>();
        for (Integer n : nums2) {
            set2.add(n);
        }

        if (set1.size() < set2.size()) {
            return setIntersection(set1, set2);
        } else {
            return setIntersection(set2, set1);
        }
    }

    // use two sets, use smaller set to perform the linear scan for creating the intersection set
    public int[] setIntersection(HashSet<Integer> set1, HashSet<Integer> set2) {
        int[] output = new int[set1.size()];
        int index = 0;
        for (Integer s : set1) {
            if (set2.contains(s)) {
                output[index] = s;
                index++;
            }
        }
        return output;
    }

    // built in intersection
    public int[] intersectionBuiltIn(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        for (Integer n : nums1) {
            set1.add(n);
        }
        HashSet<Integer> set2 = new HashSet<>();
        for (Integer n : nums2) {
            set2.add(n);
        }
        // exclusive union method
        set1.retainAll(set2);

        int[] output = new int[set1.size()];
        int index = 0;
        for (int s : set1) {
            output[index] = s;
            index++;
        }
        return output;
    }
}
