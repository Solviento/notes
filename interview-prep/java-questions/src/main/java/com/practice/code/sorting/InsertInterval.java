/*You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi]
represent the start and the end of the ith interval and intervals is sorted in ascending order by starti.
You are also given an interval newInterval = [start, end] that represents the start and end of another interval.

Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and
intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).

Return intervals after the insertion.
Example 1:
Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
Example 2:
Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
*/
package com.practice.code.sorting;

import com.practice.code.runner.CodeRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class InsertInterval implements CodeRunner {
    @Override
    public void run() {
        int[][] arr = {{1,3},{6,9}};
        int[] newInterval = {2, 5};
        int[][] res = insert(arr, newInterval);
        System.out.println(Arrays.deepToString(res));
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new LinkedList<>();
        int currentIndex = 0;
        // add all the intervals ending before newInterval starts
        while (currentIndex < intervals.length && intervals[currentIndex][1] < newInterval[0]) {
            result.add(intervals[currentIndex]);
            currentIndex++;
        }
        // merge all overlapping intervals to one considering newInterval
        while (currentIndex < intervals.length && intervals[currentIndex][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[currentIndex][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[currentIndex][1]);
            currentIndex++;
        }
        // add the union of intervals we got
        result.add(newInterval);
        // add all the rest
        while (currentIndex < intervals.length) {
            result.add(intervals[currentIndex]);
            currentIndex++;
        }
        int[][] output = new int[result.size()][2];
        for(int i = 0; i < output.length; i++) {
            output[i] = result.get(i);
        }
        return output;
    }
}
