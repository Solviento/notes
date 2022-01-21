// Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.
// In Pascal's triangle, each number is the sum of the two numbers directly above it.
// Input: 5
// Output:
// [
//      [1],
//     [1,1],
//    [1,2,1],
//   [1,3,3,1],
//  [1,4,6,4,1]
// ]

package com.practice.code.problems;

import com.practice.code.runner.CodeRunner;

import java.util.ArrayList;
import java.util.List;

class PascalsTriangle implements CodeRunner {
    static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> pascal = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> subRow = new ArrayList<>();
            subRow.add(1);
            for (int j = 1; j < i; j++) {
                // get above elements
                int aboveLeft = pascal.get(i - 1).get(j - 1);
                int aboveRight = pascal.get(i - 1).get(j);
                // set index j to the sum of above elements
                subRow.add(j, aboveLeft + aboveRight);
            }
            // adds final 1 to level
            if (i > 0) {
                subRow.add(1);
            }
            pascal.add(subRow);
        }
        return pascal;
    }

    @Override
    public void run() {
        List<List<Integer>> pTriangle = generate(6);
        for (List<Integer> e : pTriangle) {
            for (Integer e_ : e) {
                System.out.print(e_ + " ");
            }
            System.out.println("");
        }
    }
}