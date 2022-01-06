/*You are visiting a farm that has a single row of fruit trees arranged from left to right. The trees are represented by an integer array fruits where fruits[i] is the type of fruit the ith tree produces.

You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:

You only have two baskets, and each basket can only hold a single type of fruit. There is no limit on the amount of fruit each basket can hold.
Starting from any tree of your choice, you must pick exactly one fruit from every tree (including the start tree) while moving to the right. The picked fruits must fit in one of your baskets.
Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
Given the integer array fruits, return the maximum number of fruits you can pick.*/

package com.practice.code.problems;

import com.practice.code.runner.CodeRunner;

public class TotalFruit implements CodeRunner {

    public int totalFruit(int[] fruits) {

        // keep track of the last fruit, at this point there is no last fruit
        int last_fruit = - 1;
        int second_last_fruit = -1;
        int last_fruit_count = 0;
        int current_max = 0;
        int max = 0;

        for (Integer fruit : fruits) {
            if (fruit == last_fruit || fruit == second_last_fruit) {
                current_max += 1;
            }
            else {
                current_max = last_fruit_count + 1;
            }
            if (fruit == last_fruit) {
                last_fruit_count += 1;
            }
            else {
                last_fruit_count = 1;
            }
            if (fruit != last_fruit) {
                second_last_fruit = last_fruit;
                last_fruit = fruit;
            }
            max = Math.max(current_max, max);
        }
        return max;
    }

    public void run() {
        int[] arr = {10,13,12,14,15};
        int jumps = totalFruit(arr);
        System.out.println("Total fruit: " + jumps);
    }
}
