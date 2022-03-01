/*You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).

Find two lines that together with the x-axis form a container, such that the container contains the most water.

Return the maximum amount of water a container can store.
The aim is to maximize the area formed between the vertical lines. The area of any container is calculated using the
shorter line as length and the distance between the lines as the width of the rectangle.
Area = length of shorter vertical line * distance between lines
*/

package com.practice.code.arrays.first;

import com.practice.code.runner.CodeRunner;

public class MaxArea implements CodeRunner {
    public int maxArea(int[] height) {
        int max = Integer.MIN_VALUE;
        int left_pointer = 0;
        int right_pointer = height.length - 1;

        while (left_pointer < right_pointer) {
            int shortest_length = Math.min(height[left_pointer], height[right_pointer]);
            int local_max = shortest_length * (right_pointer - left_pointer); // area formula
            max = Math.max(max, local_max);
            if (height[left_pointer] < height[right_pointer]) {
                left_pointer++;
            } else {
                right_pointer--;
            }
        }
        return max;
    }

    @Override
    public void run() {

        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int max = maxArea(height);
        System.out.println("Max area : " + max);
    }
}
