/*Write an efficient algorithm that searches for a value
target in an m x n integer matrix matrix. This matrix has the following properties:
Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.

Example 1:
Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
Output: true

Example 2:
Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
Output: false*/

package com.practice.code.searching;

import com.practice.code.runner.CodeRunner;

public class SearchA2DMatrix implements CodeRunner {
    @Override
    public void run() {

    }

    // treat matrix like so
    // [[1, ..., 7], [10, ..., 20] ... [23, ..., 60]]
    // as a sorted array
    // [1, 3, 5, 7, 10, 11, 16, 20 .... , 60]
    // and then do normal binary search on this array
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0)
            return false;
        int n = matrix[0].length;

        // binary search
        int left = 0, right = m * n - 1;
        int pivotIdx, pivotElement;
        while (left <= right) {
            pivotIdx = (left + right) / 2;
            pivotElement = matrix[pivotIdx / n][pivotIdx % n];
            if (target == pivotElement)
                return true;
            else {
                if (target < pivotElement)
                    right = pivotIdx - 1;
                else
                    left = pivotIdx + 1;
            }
        }
        return false;
    }
}
