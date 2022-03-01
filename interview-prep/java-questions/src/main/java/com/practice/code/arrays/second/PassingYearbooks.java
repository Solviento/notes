/*There are n students, numbered from 1 to n, each with their own yearbook.
They would like to pass their yearbooks around and get them signed by other students.
You're given a list of n integers arr[1..n], which is guaranteed
to be a permutation of 1..n (in other words, it includes the integers
from 1 to n exactly once each, in some order).

Example 1
n = 2
arr = [2, 1]
output = [2, 2]*/
package com.practice.code.arrays.second;

import com.practice.code.runner.CodeRunner;

public class PassingYearbooks implements CodeRunner {
    @Override
    public void run() {

    }

    int[] findSignatureCounts(int[] arr) {
        int[] output = new int[arr.length];

        for(int student =1; student <= arr.length;student++){
            int currentHolder = student;

            do{
                output[student-1] +=1;
                currentHolder = arr[currentHolder-1];
            }while(currentHolder != student);

        }
        return output;
    }
}
