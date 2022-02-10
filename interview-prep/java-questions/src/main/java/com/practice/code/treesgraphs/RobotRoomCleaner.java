/*/ou are controlling a robot that is located somewhere in a room. The room is modeled as an m x n binary grid where 0
represents a wall and 1 represents an empty slot.

The robot starts at an unknown location in the room that is guaranteed to be empty, and you do not have access to the
grid, but you can move the robot using the given API Robot.

You are tasked to use the robot to clean the entire room (i.e., clean every empty cell in the room).
The robot with the four given APIs can move forward, turn left, or turn right. Each turn is 90 degrees.

When the robot tries to move into a wall cell, its bumper sensor detects the obstacle, and it stays on the current cell.

Design an algorithm to clean the entire room using the following APIs:

Note that the initial direction of the robot will be facing up. You can assume all four edges of the grid are all surrounded by a wall.*/

package com.practice.code.treesgraphs;

import com.practice.code.runner.CodeRunner;

import java.util.HashSet;
import java.util.Set;

public class RobotRoomCleaner implements CodeRunner {
    @Override
    public void run() {

    }
     // This is the robot's control interface.
     // You should not implement it, or speculate about its implementation
     interface Robot {
         // Returns true if the cell in front is open and robot moves into the cell.
         // Returns false if the cell in front is blocked and robot stays in the current cell.
         public boolean move();
              // Robot will stay in the same cell after calling turnLeft/turnRight.
         // Each turn will be 90 degrees.
         public void turnLeft();
         public void turnRight();
              // Clean the current cell.
         public void clean();
     }

    private static final int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public void cleanRoom(Robot robot) {
        clean(robot, 0, 0, 0, new HashSet<>());
    }

    private void clean(Robot robot, int x, int y, int curDirection, Set<String> visited) {
        // Cleans current cell.
        robot.clean();
        visited.add(x + " " + y);

        for (int nDirection = curDirection;
             nDirection < curDirection + 4;
             nDirection++) {
            int nx = directions[nDirection % 4][0] + x;
            int ny = directions[nDirection % 4][1] + y;
            if (!visited.contains(nx + " " + ny) && robot.move()) {
                clean(robot, nx, ny, nDirection % 4, visited);
            }
            // Changed orientation.
            robot.turnRight();
        }

        // Moves backward one step while maintaining the orientation.
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }
}
