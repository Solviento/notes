/*Given four integer arrays nums1, nums2, nums3, and nums4 all of length n, return the number of tuples (i, j, k, l) such that:

0 <= i, j, k, l < n
nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0

Example 1:
Input: nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
Output: 2
Explanation:
The two tuples are:
1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0
*/

package com.practice.code.arraysandstrings;

import com.practice.code.runner.CodeRunner;

import java.util.HashMap;

public class FourSumII implements CodeRunner {

    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        HashMap<Integer, Integer> map = new HashMap<>();
        // go through i, j arrays and calculate pair sum then store this sum and its respective frequency
        for (int i : A) {
            for (int j : B) {
                int sum = i + j;
                if (map.containsKey(sum)) {
                    // needs to store how frequent this sum value comes up in A[] and B[]
                    map.put(sum, map.get(sum) + 1);
                } else {
                    map.put(sum, 1);
                }
            }
        }
        int count = 0;
        // go through k, l arrays and calculate pair counter sum and then add its recorded frequency
        for (int k : C) {
            for (int l : D) {
                int counterSum = k + l;
                counterSum *= -1;
                if (map.containsKey(counterSum)) {
                    // frequency of counterSum in map added to overall count of zero sum tuples
                    count += map.get(counterSum);
                }
            }
        }
        return count;
    }

    public void run() {
        System.out.println("Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l)\n" +
                "there are such that A[i] + B[j] + C[k] + D[l] is zero.\n" +
                "To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500. \n" +
                "All integers are in the range of -2^28 to 2^28 - 1 and the result is guaranteed to be at most 2^31 - 1.\n" +
                "Example:\n" +
                "Input:\n" +
                "A = [ 1, 2]\n" +
                "B = [-2,-1]\n" +
                "C = [-1, 2]\n" +
                "D = [ 0, 2]\n" +
                "Output:\n" +
                "2\n" +
                "Explanation:\n" +
                "The two tuples are:\n" +
                "1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0\n" +
                "2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0");
        System.out.println("Solution is in class file");
    }
}