/*There are n workers. You are given two integer arrays
quality and wage where quality[i] is the quality of the
ith worker and wage[i] is the minimum wage expectation for the ith worker.

We want to hire exactly k workers to form a paid group.
To hire a group of k workers, we must pay them according to the following rules:

Every worker in the paid group should be paid in the
ratio of their quality compared to other workers in the paid group.
Every worker in the paid group must be paid at least
their minimum wage expectation.
Given the integer k, return the least amount of money
needed to form a paid group satisfying the above conditions.
Answers within 10-5 of the actual answer will be accepted.

Example 1:
Input: quality = [10,20,5], wage = [70,50,30], k = 2
Output: 105.00000
Explanation: We pay 70 to 0th worker and 35 to 2nd worker.

Example 2:
Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], k = 3
Output: 30.66667
Explanation: We pay 4 to 0th worker, 13.33333 to 2nd and 3rd workers separately.*/
package com.practice.code.arrays.second;

import com.practice.code.runner.CodeRunner;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MinimumCostToHireKWorkers implements CodeRunner {
    @Override
    public void run() {
        int[] quality = {3, 1, 10, 10, 1};
        int[] wage = {4, 8, 2, 2, 7};
        int k = 3;
        double output = mincostToHireWorkers(quality, wage, k);
        System.out.println(output);
    }

    // t: o(nlogn) s: o(n)
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        Worker[] workers = new Worker[quality.length];
        for (int i = 0; i < quality.length; ++i) {
            workers[i] = new Worker(quality[i], wage[i]);
        }
        Arrays.sort(workers);
        double ans = Double.MAX_VALUE;
        int sumq = 0;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>();
        for (Worker worker : workers) {
            maxHeap.add(-worker.quality);
            sumq += worker.quality;
            if (maxHeap.size() > K)
                sumq += maxHeap.poll();
            if (maxHeap.size() == K)
                ans = Math.min(ans, sumq * worker.ratio());
        }
        return ans;
    }

    static class Worker implements Comparable<Worker> {
        public int quality, wage;

        public Worker(int q, int w) {
            quality = q;
            wage = w;
        }

        public double ratio() {
            return (double) wage / quality;
        }

        public int compareTo(Worker other) {
            return Double.compare(ratio(), other.ratio());
        }
    }

    public double mincostToHireWorkers2(int[] quality, int[] wage, int K) {
        double[][] workers = new double[quality.length][2];
        for (int i = 0; i < quality.length; ++i) {
            workers[i] = new double[]{(double) (wage[i]) / quality[i], (double) quality[i]};
        }
        Arrays.sort(workers, (a, b) -> Double.compare(a[0], b[0]));
        double res = Double.MAX_VALUE;
        double qsum = 0;
        PriorityQueue<Double> pq = new PriorityQueue<>();
        for (double[] worker : workers) {
            qsum += worker[1];
            pq.add(-worker[1]);
            if (pq.size() > K) qsum += pq.poll();
            if (pq.size() == K) res = Math.min(res, qsum * worker[0]);
        }
        return res;
    }
}
