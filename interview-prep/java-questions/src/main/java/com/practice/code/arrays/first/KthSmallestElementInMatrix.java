package com.practice.code.arrays.first;

import com.practice.code.runner.CodeRunner;

import java.util.PriorityQueue;

public class KthSmallestElementInMatrix implements CodeRunner {
    @Override
    public void run() {

    }

    // t: n^2 s: n^2
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                pq.offer(matrix[i][j]);
            }
        }
        while (--k > 0) {
            pq.poll();
        }
        return pq.peek();
    }

    // t: nlogn s: 1
    public int kthSmallestLog(int[][] matrix, int k) {
        int n = matrix.length;
        int lo = matrix[0][0], hi = matrix[n - 1][n - 1], mid;
        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;
            int count = getCountLessOrEqual(matrix, mid, n);
            if (count < k) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return lo;
    }

    private int getCountLessOrEqual(int[][] matrix, int val, int n) {
        int i = 0, j = n - 1;
        int res = 0;
        while (i < n) {
            while (j >= 0 && matrix[i][j] > val) {
                j--;
            }
            res += j + 1;
            i++;
        }
        return res;
    }
}
