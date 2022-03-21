/*Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals,
and return an array of the non-overlapping intervals that cover all the intervals in the input.

Example 1:
Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].

Example 2:
Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.*/
package com.practice.code.sorting;

import com.practice.code.runner.CodeRunner;

import java.util.Arrays;
import java.util.LinkedList;

public class MergeIntervals implements CodeRunner {
    @Override
    public void run() {
        int[][] arr = {{1,3},{2,6},{8,10},{15,18}};
        int[][] m = merge(arr);
        System.out.println(Arrays.deepToString(m));
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (x, y) -> x[0] - y[0]);
        LinkedList<int[]> merged = new LinkedList<>();
        for (int[] interval : intervals) {
            // if the list of merged intervals is empty or if the current
            // interval does not overlap with the previous, simply append it.
            if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {
                merged.add(interval);
            }
            // otherwise, there is overlap, so we merge the current and previous intervals.
            else {
                merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
            }
        }
        int[][] output = new int[merged.size()][2];
        for(int i = 0; i < output.length; i++) {
            output[i] = merged.get(i);
        }
        return output;
    }
}
