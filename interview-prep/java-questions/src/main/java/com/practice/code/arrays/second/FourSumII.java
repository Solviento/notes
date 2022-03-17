/*Given four integer arrays nums1, nums2, nums3,
and nums4 all of length n, return the number of tuples (i, j, k, l) such that:

0 <= i, j, k, l < n
nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0

Example 1:
Input: nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
Output: 2
Explanation:
The two tuples are:
1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0

Example 2:
Input: nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]
Output: 1
*/

package com.practice.code.arrays.second;

import com.practice.code.runner.CodeRunner;

import java.util.HashMap;
import java.util.Map;

public class FourSumII implements CodeRunner {

    public void run() {
        int[] n1 = new int[]{1, 2};
        int[] n2 = new int[]{-2,-1};
        int[] n3 = new int[]{-1,2};
        int[] n4 = new int[]{0,1};

        int res = fourSumCount(n1, n2, n3, n4);
        System.out.println("4Sum Count: " + res);
    }

    // since len(a) == len(b) == len(c) == len(d)
    // t: o(n^2) s: o(n^2)
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        HashMap<Integer, Integer> map = new HashMap<>();
        // go through i, j arrays and calculate pair sum then store this sum and its respective frequency
        for (int i : A) {
            for (int j : B) {
                int sum = i + j;
                map.merge(sum, 1, (x, y) -> x + y);
            }
        }
        int count = 0;
        // go through k, l arrays and calculate pair counter sum and then add its recorded frequency
        for (int k : C) {
            for (int l : D) {
                int oppositeSum = k + l;
                oppositeSum *= -1;
                // frequency of counterSum in map added to overall count of zero sum tuples
                // why? oppositeSum can applied as many times its found for the sum found in map
                // 1 oppositeSum -> 3 sum -> 3 different counts
                count += map.getOrDefault(oppositeSum, 0);
            }
        }
        return count;
    }

    // follow up, what if we have 5sum, 6sum, ksum, etc?
    // t: o(n^k/2) s: o(n^k/2)
    public int fourSumCountK(int[] A, int[] B, int[] C, int[] D) {
        return kSumCount(new int[][]{A, B, C, D});
    }

    public int kSumCount(int[][] lists) {
        Map<Integer, Integer> m = new HashMap<>();
        addToHash(lists, m, 0, 0);
        return countComplements(lists, m, lists.length / 2, 0);
    }

    void addToHash(int[][] lists, Map<Integer, Integer> m, int i, int sum) {
        if (i == lists.length / 2) {
            m.put(sum, m.getOrDefault(sum, 0) + 1);
        } else {
            for (int a : lists[i]) {
                addToHash(lists, m, i + 1, sum + a);
            }
        }
    }

    int countComplements(int[][] lists, Map<Integer, Integer> m, int i, int complement) {
        if (i == lists.length) {
            return m.getOrDefault(complement, 0);
        }
        int cnt = 0;
        for (int a : lists[i]) {
            cnt += countComplements(lists, m, i + 1, complement - a);
        }
        return cnt;
    }
}