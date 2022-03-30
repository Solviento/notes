/*You are given an integer length and an array updates
where updates[i] = [startIdxi, endIdxi, inci].
You have an array arr of length length with all zeros,
and you have some operation to apply on arr. In the ith
operation, you should increment all the elements
arr[startIdxi], arr[startIdxi + 1], ..., arr[endIdxi] by inci.

Return arr after applying all the updates.

Example 1:
Input: length = 5, updates = [[1,3,2],[2,4,3],[0,2,-2]]
Output: [-2,0,3,5,3]

Example 2:
Input: length = 10, updates = [[2,4,6],[5,6,8],[1,9,-4]]
Output: [0,-4,2,2,2,4,4,-4,-4,-4]*/

package com.practice.code.arrays.ufourth;

import com.practice.code.runner.CodeRunner;

import java.util.Arrays;

public class RangeAddition implements CodeRunner {
    @Override
    public void run() {

    }

    // t: nk s: 1
    int[] getModifiedArray(int length, int[][] updates) {
        int[] result = new int[length];
        Arrays.fill(result, 0);

        for (int[] tuple : updates) {
            int start = tuple[0];
            int end = tuple[1];
            int val = tuple[2];

            for (int i = start; i <= end; i++) {
                result[i] += val;
            }
        }

        return result;
    }

    // t: n + k s: 1
    int[] getModifiedArrayNK(int length, int[][] updates)
    {
        int[] result = new int[length];
        Arrays.fill(result, 0);

        for (int[] tuple : updates) {
        int start = tuple[0], end = tuple[1], val = tuple[2];

        result[start] += val;
        if (end < length - 1)
            result[end + 1] -= val;
    }

        // partial_sum applies the following operation (by default) for the parameters {x[0], x[n], y[0]}:
        // y[0] = x[0]
        // y[1] = y[0] + x[1]
        // y[2] = y[1] + x[2]
        // ...  ...  ...
        // y[n] = y[n-1] + x[n]

//        partial_sum(result.begin(), result.end(), result.begin());

        return result;
    }
}
