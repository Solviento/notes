/*You are given an integer array arr. From some starting index, you can make a series of jumps. The (1st, 3rd, 5th, ...) jumps in the series are called odd-numbered jumps, and the (2nd, 4th, 6th, ...) jumps in the series are called even-numbered jumps. Note that the jumps are numbered, not the indices.

You may jump forward from index i to index j (with i < j) in the following way:

During odd-numbered jumps (i.e., jumps 1, 3, 5, ...), you jump to the index j such that arr[i] <= arr[j] and arr[j] is the smallest possible value. If there are multiple such indices j, you can only jump to the smallest such index j.
During even-numbered jumps (i.e., jumps 2, 4, 6, ...), you jump to the index j such that arr[i] >= arr[j] and arr[j] is the largest possible value. If there are multiple such indices j, you can only jump to the smallest such index j.
It may be the case that for some index i, there are no legal jumps.
A starting index is good if, starting from that index, you can reach the end of the array (index arr.length - 1) by jumping some number of times (possibly 0 or more than once).

Return the number of good starting indices.

Example 1:

Input: arr = [10,13,12,14,15]
Output: 2
Explanation:
From starting index i = 0, we can make our 1st jump to i = 2 (since arr[2] is the smallest among arr[1], arr[2], arr[3], arr[4] that is greater or equal to arr[0]), then we cannot jump any more.
From starting index i = 1 and i = 2, we can make our 1st jump to i = 3, then we cannot jump any more.
From starting index i = 3, we can make our 1st jump to i = 4, so we have reached the end.
From starting index i = 4, we have reached the end already.
In total, there are 2 different starting indices i = 3 and i = 4, where we can reach the end with some number of
jumps.*/

package com.practice.code.arrays.second;

import com.practice.code.runner.CodeRunner;

import java.util.Map;
import java.util.TreeMap;

public class OddEvenJump implements CodeRunner {

    public int oddEvenJumps(int[] arr) {
        int good_starting_index_count = 1;
        int n = arr.length;

        boolean[] higher = new boolean[n];
        boolean[] lower = new boolean[n];

        higher[n - 1] = true;
        lower[n - 1] = true;

        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        treeMap.put(arr[n - 1], n - 1);

        for (int i = n - 2; i >= 0; i--) {
            Map.Entry<Integer, Integer> higher_key_val_pair = treeMap.ceilingEntry(arr[i]);
            Map.Entry<Integer, Integer> lower_key_val_pair = treeMap.floorEntry(arr[i]);

            if (higher_key_val_pair != null) {
                higher[i] = lower[higher_key_val_pair.getValue()];
            }
            if (lower_key_val_pair != null) {
                lower[i] = higher[lower_key_val_pair.getValue()];
            }
            if (higher[i]) {
                good_starting_index_count++;
            }
            treeMap.put(arr[i], i);
        }

        return good_starting_index_count;
    }

    public void run() {
        int[] arr = {10, 13, 12, 14, 15};
        int jumps = oddEvenJumps(arr);
        System.out.println("Number of jumps: " + jumps);
    }
}
