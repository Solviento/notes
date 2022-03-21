/*Write an efficient algorithm that searches for a
value target in an m x n integer matrix matrix. This
matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.

Example 1:
Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
Output: true

Example 2:
Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
Output: false*/
package com.practice.code.searching;

import com.practice.code.runner.CodeRunner;

public class SearchA2DMatrixII implements CodeRunner {
    @Override
    public void run() {

    }

    // approach: start at top right corner of matrix, then look diagonally
    // treat matrix like a binary search tree
    // t: n+m s: 1
    public boolean searchMatrixN(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int n = matrix.length;
        int m = matrix[0].length;
        int i = 0;
        int j = m - 1;

        while (i < n && j >= 0) {
            int num = matrix[i][j];
            if (num == target) {
                return true;
            } else if (num > target) {
                j--;
            } else {
                i++;
            }
        }

        return false;
    }

    // binary search
    // t: log(n!) s: 1
    public boolean searchMatrix(int[][] matrix, int target) {
        // an empty matrix obviously does not contain `target`
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        // iterate over matrix diagonals
        int shorterDim = Math.min(matrix.length, matrix[0].length);
        for (int i = 0; i < shorterDim; i++) {
            boolean verticalFound = binarySearch(matrix, target, i, true);
            boolean horizontalFound = binarySearch(matrix, target, i, false);
            if (verticalFound || horizontalFound) {
                return true;
            }
        }
        return false;
    }

    private boolean binarySearch(int[][] matrix, int target, int start, boolean vertical) {
        int startIndex = start;
        int endIndex;
        if (vertical) {
            endIndex = matrix[0].length - 1;
        } else {
            endIndex = matrix.length - 1;
        }

        while (startIndex <= endIndex) {
            int midIndex = (startIndex + endIndex) / 2;
            if (vertical) { // searching a column
                if (matrix[start][midIndex] < target) {
                    startIndex = midIndex + 1;
                } else if (matrix[start][midIndex] > target) {
                    endIndex = midIndex - 1;
                } else {
                    return true;
                }
            } else { // searching a row
                if (matrix[midIndex][start] < target) {
                    startIndex = midIndex + 1;
                } else if (matrix[midIndex][start] > target) {
                    endIndex = midIndex - 1;
                } else {
                    return true;
                }
            }
        }

        return false;
    }
}
