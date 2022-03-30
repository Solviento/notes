/*Given n non-negative integers representing an elevation map where the width of each bar is 1,
compute how much water it can trap after raining.
Example 1:
Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
In this case, 6 units of rain water (blue section) are being trapped.*/
package com.practice.code.arrays.third;

import com.practice.code.runner.CodeRunner;

public class TrappingRainWater implements CodeRunner {

    @Override
    public void run() {

    }

    /*So, we can say that if there is a larger bar at one end (say right),
    we are assured that the water trapped would be dependant on height of bar in current direction
     from left to right). As soon as we find the bar at other end (right) is smaller, we start iterating in
     opposite direction (from right to left). We must maintain \text{left\_max}left_max and \text{right\_max}right_max
     during the iteration, but now we can do it in one iteration using 2 pointers, switching between the two.*/
    // two pointers
    // [          ]
    // L          R
    int trap(int[] height) {
        int left = 0, right = height.length - 1;
        int ans = 0;
        int left_max = 0, right_max = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= left_max) {
                    left_max = height[left];
                } else {
                    ans += (left_max - height[left]);
                }
                ++left;
            } else {
                if (height[right] >= right_max) {
                    right_max = height[right];
                } else {
                    ans += (right_max - height[right]);
                }
                --right;
            }
        }
        return ans;
    }

}
