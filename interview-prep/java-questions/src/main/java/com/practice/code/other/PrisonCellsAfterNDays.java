/*There are 8 prison cells in a row and each cell is either occupied or vacant.
Each day, whether the cell is occupied or vacant changes according to the following rules:
If a cell has two adjacent neighbors that are both occupied or both vacant, then the cell becomes occupied.
Otherwise, it becomes vacant.
Note that because the prison is a row, the first and
the last cells in the row can't have two adjacent neighbors.
You are given an integer array cells where cells[i] == 1 if
the ith cell is occupied and cells[i] == 0 if the ith cell is vacant, and you are given an integer n.
Return the state of the prison after n days (i.e., n such changes described above).

Example 1:
Input: cells = [0,1,0,1,1,0,0,1], n = 7
Output: [0,0,1,1,0,0,0,0]
Explanation: The following table summarizes the state of the prison on each day:
Day 0: [0, 1, 0, 1, 1, 0, 0, 1]
Day 1: [0, 1, 1, 0, 0, 0, 0, 0]
Day 2: [0, 0, 0, 0, 1, 1, 1, 0]
Day 3: [0, 1, 1, 0, 0, 1, 0, 0]
Day 4: [0, 0, 0, 0, 0, 1, 0, 0]
Day 5: [0, 1, 1, 1, 0, 1, 0, 0]
Day 6: [0, 0, 1, 0, 1, 1, 0, 0]
Day 7: [0, 0, 1, 1, 0, 0, 0, 0]
Example 2:

Input: cells = [1,0,0,1,0,0,1,0], n = 1000000000
Output: [0,0,1,1,1,1,1,0]
*/

package com.practice.code.other;

import com.practice.code.runner.CodeRunner;

import java.util.HashMap;

public class PrisonCellsAfterNDays implements CodeRunner {
    @Override
    public void run() {

    }

//    First of all, we record the state at each step, with the index of the current step, i.e. state -> step_index.
//    Once we discover a repetitive state, we can then determine the length (denoted as CC) of the cycle, with the help of hashmap that we recorded.
//    Starting from this repetitive state, the prison cells would play out the states within the cycle over and over, until we run out of steps.
//    In other words, if the remaining steps is NN, at least we could fast-forward to the step of N \mod CNmodC.
//    And then from the step of N \mod CNmodC, we continue the simulation step by step.
//    We could reuse the bulk of the previous implementations, and simply rewrite the nextDay function with the bit operations as we discussed.
//
//    Additionally, at the end of the simulation, we should decode the states of the cells from the final bitmap.

    public int[] prisonAfterNDays(int[] cells, int N) {

        HashMap<Integer, Integer> seen = new HashMap<>();
        boolean isFastForwarded = false;

        // step 1). convert the cells to bitmap
        int stateBitmap = 0x0;
        for (int cell : cells) {
            stateBitmap <<= 1;
            stateBitmap = (stateBitmap | cell);
        }

        // step 2). run the simulation with hashmap
        while (N > 0) {
            if (!isFastForwarded) {
                if (seen.containsKey(stateBitmap)) {
                    // the length of the cycle is seen[state_key] - N
                    N %= seen.get(stateBitmap) - N;
                    isFastForwarded = true;
                } else
                    seen.put(stateBitmap, N);
            }
            // check if there is still some steps remained,
            // with or without the fast forwarding.
            if (N > 0) {
                N -= 1;
                stateBitmap = this.nextDay(stateBitmap);
            }
        }

        // step 3). convert the bitmap back to the state cells
        int ret[] = new int[cells.length];
        for (int i = cells.length - 1; i >= 0; i--) {
            ret[i] = (stateBitmap & 0x1);
            stateBitmap = stateBitmap >> 1;
        }
        return ret;
    }

    protected int nextDay(int stateBitmap) {
        stateBitmap = ~(stateBitmap << 1) ^ (stateBitmap >> 1);
        // set the head and tail to zero
        stateBitmap = stateBitmap & 0x7e;
        return stateBitmap;
    }
}
