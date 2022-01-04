/*You are given an integer array arr. From some starting index, you can make a series of jumps. The (1st, 3rd, 5th, ...) jumps in the series are called odd-numbered jumps, and the (2nd, 4th, 6th, ...) jumps in the series are called even-numbered jumps. Note that the jumps are numbered, not the indices.

You may jump forward from index i to index j (with i < j) in the following way:

During odd-numbered jumps (i.e., jumps 1, 3, 5, ...), you jump to the index j such that arr[i] <= arr[j] and arr[j] is the smallest possible value. If there are multiple such indices j, you can only jump to the smallest such index j.
During even-numbered jumps (i.e., jumps 2, 4, 6, ...), you jump to the index j such that arr[i] >= arr[j] and arr[j] is the largest possible value. If there are multiple such indices j, you can only jump to the smallest such index j.
It may be the case that for some index i, there are no legal jumps.
A starting index is good if, starting from that index, you can reach the end of the array (index arr.length - 1) by jumping some number of times (possibly 0 or more than once).

Return the number of good starting indices.*/

package com.practice.code.problems;

import com.practice.code.runner.CodeRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class OddEvenJump implements CodeRunner {

    // idea is to use dynamic programming to store which values can you jump higher or lower to save time from making the
    // same calculations repeatedly
    public int oddEvenJumps(int[] arr) {
        int good_starting_index_count = 1;
        int n = arr.length;

        boolean[] higher = new boolean[n];
        boolean[] lower = new boolean[n];

        higher[n-1] = true;
        lower[n-1] = true;

        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        treeMap.put(arr[n - 1], n-1);

        for (int i = n-2; i>=0; i--) {
            Map.Entry higher_key_val_pair = treeMap.ceilingEntry(arr[i]);
            Map.Entry lower_key_val_pair = treeMap.floorEntry(arr[i]);

            if (higher_key_val_pair != null) {
                higher[i] = lower[(int) higher_key_val_pair.getValue()];
            }
            if (lower_key_val_pair != null) {
                lower[i] = higher[(int) lower_key_val_pair.getValue()];
            }
            if (higher[i]) {
                good_starting_index_count++;
            }
            treeMap.put(arr[i], i);
        }

        return good_starting_index_count;
    }

    public void run() {
        int[] arr = {10,13,12,14,15};
        int jumps = oddEvenJumps(arr);
        System.out.println("Number of jumps: " + jumps);
    }
}
