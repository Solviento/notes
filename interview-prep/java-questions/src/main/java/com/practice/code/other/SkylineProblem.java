/*A city's skyline is the outer contour of the silhouette formed
by all the buildings in that city when viewed from a distance.
Given the locations and heights of all the buildings, return
the skyline formed by these buildings collectively.
The geometric information of each building is given in the array
buildings where buildings[i] = [lefti, righti, heighti]:
lefti is the x coordinate of the left edge of the ith building.
righti is the x coordinate of the right edge of the ith building.
heighti is the height of the ith building.
You may assume all buildings are perfect rectangles grounded
on an absolutely flat surface at height 0.
The skyline should be represented as a list of "key points"
sorted by their x-coordinate in the form [[x1,y1],[x2,y2],...].
Each key point is the left endpoint of some horizontal segment
in the skyline except the last point in the list, which always
has a y-coordinate 0 and is used to mark the skyline's termination
where the rightmost building ends. Any ground between the
leftmost and rightmost buildings should be part of the skyline's contour.
Note: There must be no consecutive horizontal lines of equal
height in the output skyline. For instance,
[...,[2 3],[4 5],[7 5],[11 5],[12 7],...] is not acceptable;
the three lines of height 5 should be merged into one in the
final output as such: [...,[2 3],[4 5],[12 7],...]

Example 1:
Input: buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
Output: [[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
Explanation:
Figure A shows the buildings of the input.
Figure B shows the skyline formed by those buildings. The red points in figure B represent the key points in the output list.

Example 2:
Input: buildings = [[0,2,3],[2,5,3]]
Output: [[0,3],[5,0]]*/
package com.practice.code.other;

import com.practice.code.runner.CodeRunner;

import java.util.*;

public class SkylineProblem implements CodeRunner {
    @Override
    public void run() {

    }

    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> result = new ArrayList<>();
        List<int[]> height = new ArrayList<>();
        for(int[] b:buildings) {
            height.add(new int[]{b[0], -b[2]});
            height.add(new int[]{b[1], b[2]});
        }
        Collections.sort(height, (a, b) -> {
            if(a[0] != b[0])
                return a[0] - b[0];
            return a[1] - b[1];
        });
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
        pq.offer(0);
        int prev = 0;
        for(int[] h:height) {
            if(h[1] < 0) {
                pq.offer(-h[1]);
            } else {
                pq.remove(h[1]);
            }
            int cur = pq.peek();
            if(prev != cur) {
                result.add(new int[]{h[0], cur});
                prev = cur;
            }
        }
        return result;
    }
}
