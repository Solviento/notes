/*Given an integer array nums, design an algorithm to
randomly shuffle the array. All permutations of the
array should be equally likely as a result of the shuffling.

Implement the Solution class:
Solution(int[] nums) Initializes the object with the integer array nums.
int[] reset() Resets the array to its original configuration and returns it.
int[] shuffle() Returns a random shuffling of the array.

Example 1:
Input
["Solution", "shuffle", "reset", "shuffle"]
[[[1, 2, 3]], [], [], []]
Output
[null, [3, 1, 2], [1, 2, 3], [1, 3, 2]]
*/
package com.practice.code.arrays.second;

import java.util.Random;

// Shuffle a set of numbers without duplicates.
public class ShuffleArray {
    private int[] shuffleArray;
    private int[] original;
    Random rand = new Random();

    // returns random index value between min and max
    public int randomIndex(int min, int max) {
        // 50-100 range (100-50=50 .. random int from 0 to 50, then + min to get random int in range)
        // add 1 to make it exclusive, DO NOT ADD 1 FOR THIS PROBLEM, WILL CAUSE INDEX OUT OF BOUNDS
        // FOR ARRAY.LENGTH
        return rand.nextInt((max - min)) + min;
    }

    public ShuffleArray(int[] nums) {
        shuffleArray = nums;
        original = nums.clone();
    }

    /**
     * Resets the array to its original configuration and return it.
     */
    public int[] reset() {
        shuffleArray = original;
        original = original.clone();
        return shuffleArray;
    }

    /**
     * Returns a random shuffling of the array.
     */
    public int[] shuffle() {
        // uses yates-fisher algorithm
        for (int i = 0; i < shuffleArray.length; i++) {
            int randomizedIndex = randomIndex(i, shuffleArray.length);
            swap(i, randomizedIndex);
        }
        return shuffleArray;
    }

    public void swap(int x, int y) {
        int tmp = shuffleArray[x];
        shuffleArray[x] = shuffleArray[y];
        shuffleArray[y] = tmp;
    }
}