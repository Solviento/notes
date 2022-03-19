/*There are n students, numbered from 1 to n, each with their own yearbook.
They would like to pass their yearbooks around and get them signed by other students.
You're given a list of n integers arr[1..n], which is guaranteed
to be a permutation of 1..n (in other words, it includes the integers
from 1 to n exactly once each, in some order).

Example 1:
n = 2
arr = [2, 1]
output = [2, 2]*/
package com.practice.code.arrays.second;

import com.practice.code.runner.CodeRunner;

import java.util.Arrays;

public class PassingYearbooks implements CodeRunner {
    @Override
    public void run() {
        int[] yrbook = {2, 1};
        int[] res = findSignatureCounts(yrbook);
        System.out.println(Arrays.toString(res));
    }

    int[] findSignatureCounts(int[] arr) {
        int[] result = new int[arr.length];
        for (int student = 1; student <= arr.length; student++) {
            int currentStudent = student;
            result[student - 1] += 1;
            currentStudent = arr[currentStudent - 1];
            while (currentStudent != student) {
                result[student - 1] += 1;
                currentStudent = arr[currentStudent - 1];
            }

        }
        return result;
    }
}
