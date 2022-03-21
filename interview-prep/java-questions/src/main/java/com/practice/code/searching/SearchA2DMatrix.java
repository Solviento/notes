/*Write an efficient algorithm that searches for a value
target in an m x n integer matrix. This matrix has the following properties:
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
        int[][] matrix = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        boolean search = searchMatrix(matrix, 3);
        System.out.println("Result: " + search);
    }

    // treat matrix below
    // [[1, ..., 7], [10, ..., 20] ... [23, ..., 60]]
    // as a sorted array list
    // [1, 3, 5, 7, 10, 11, 16, 20 .... , 60]
    // and then do normal binary search on this array
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        if (m == 0) {
            return false;
        }

        // binary search
        int startIndex = 0;
        int endIndex = m * n - 1;
        int midIndex;
        while (startIndex <= endIndex) {
            midIndex = startIndex + (endIndex - startIndex) / 2;
            // important**
            // matrix[midIndex / n][midIndex % n]
            int element = matrix[midIndex / n][midIndex % n];
            if (target == element) {
                return true;
            } else if (target < element) {
                endIndex = midIndex - 1;
            } else {
                startIndex = midIndex + 1;
            }
        }
        return false;
    }

    // dry run:
    // 12 elements, n = 4
    // [1, 3, 5, 7, 10, 11, 16, 20, 23, 30, 34, 60]
    // |           |               |              |
    //  0  1  2  3  0   1   2   3   0   1   2   3
    // mid = 11 / 2 = 5 -> 5 / n = 1
    // mid[mid] = 5 % n = 1
}
