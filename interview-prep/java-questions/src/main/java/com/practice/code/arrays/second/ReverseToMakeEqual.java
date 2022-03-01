/*Given two arrays A and B of length N, determine if there
is a way to make A equal to B by reversing any subarrays from array B any number of times.
Input
All integers in array are in the range [0, 1,000,000,000].

Output
Return true if B can be made equal to A, return false otherwise.
Example
A = [1, 2, 3, 4]
B = [1, 4, 3, 2]
output = true*/
package com.practice.code.arrays.second;

import com.practice.code.runner.CodeRunner;

import java.util.HashMap;
import java.util.Map;

public class ReverseToMakeEqual implements CodeRunner {

    public void run() {
        int[] array_a_1 = {1, 2, 3, 4};
        int[] array_b_1 = {1, 4, 3, 2};
        boolean expected_1 = true;
        boolean output_1 = areTheyEqual(array_a_1, array_b_1);

        int[] array_a_2 = {1, 2, 3, 4};
        int[] array_b_2 = {1, 4, 3, 3};
        boolean expected_2 = false;
        boolean output_2 = areTheyEqual(array_a_2, array_b_2);
        // Add your own test cases here
        System.out.println("Outputs: " + output_1 + " " + output_2);
    }

    boolean areTheyEqual(int[] array_a, int[] array_b) {
        // Write your code here
        int m = array_a.length, n = array_b.length;
        if (m != n) return false;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            map.put(array_a[i], map.getOrDefault(array_a[i], 0) + 1);
            map.put(array_b[i], map.getOrDefault(array_b[i], 0) - 1);
        }
        for (int i : map.keySet()) {
            if (map.get(i) != 0) return false;
        }
        return true;
    }
}
