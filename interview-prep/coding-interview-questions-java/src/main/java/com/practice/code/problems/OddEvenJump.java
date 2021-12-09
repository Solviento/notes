package com.practice.code.problems;

import com.practice.code.runner.CodeRunner;

import java.util.HashMap;

public class OddEvenJump implements CodeRunner {

    private class Tuple {
        Integer indexValue;
        Integer index;
        Tuple(int inValue, int in) {
            indexValue = inValue;
            index = in;
        }
    }
    public int oddEvenJumps(int[] arr) {

        int index;
        int pointer;
        int jumpNumber;
        int jumps = 0;
        HashMap<Integer, Integer> smallestIndexJump = new HashMap<>();
        for(index = 0, pointer = index +1; index < arr.length; index++) {
//            jumpNumber = 1;
//            pointer = index + 1;
            Tuple tuple = new Tuple(Integer.MAX_VALUE, 0);
            for (jumpNumber = 1; jumpNumber > 0; jumpNumber++){
                // even jump
                if (jumpNumber % 2 == 0){ // && arr[index] >= arr[pointer]) {
                    if (arr[index] >= arr[pointer]) {
                        if (arr[index] < tuple.indexValue){
                            tuple.indexValue = arr[index];
                            tuple.index = index;
                        }
                    }
                }
                // odd jump
                else if (jumpNumber % 2 != 0) {
                    if (arr[index] <= arr[pointer]) {
                        if (arr[index] < tuple.indexValue){
                            tuple.indexValue = arr[index];
                            tuple.index = index;
                        }
                    }
                }
            }
        }

        return jumps;
    }

    public void run() {
        int[] arr = {10,13,12,14,15};
        int jumps = oddEvenJumps(arr);
        System.out.println("Number of jumps: " + jumps);
    }
}
