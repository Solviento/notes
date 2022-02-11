/*In a string composed of 'L', 'R', and 'X' characters, like "RXXLRXRXL", a move consists of either
replacing one occurrence of "XL" with "LX", or replacing one occurrence of "RX" with "XR". Given the
 tarting string start and the ending string end, return True if and only if there exists a sequence of moves to transform one string to the other.

Example 1:

Input: start = "RXXLRXRXL", end = "XRLXXRRLX"
Output: true
Explanation: We can transform start to end following these steps:
RXXLRXRXL ->
XRXLRXRXL ->
XRLXRXRXL ->
XRLXXRRXL ->
XRLXXRRLX
Example 2:
Input: start = "X", end = "L"
Output: false*/

package com.practice.code.other;

import com.practice.code.runner.CodeRunner;

import java.util.ArrayList;
import java.util.List;

public class SwapAdjacentInLRString implements CodeRunner {
    @Override
    public void run() {

    }

    public boolean canTransform(String start, String end) {
        //check for string without X, it should be the same
        String startStr = start.replace("X", "");
        String endStr = end.replace("X", "");

        if (!(startStr.equals(endStr))) return false;
        List<Integer> startL = new ArrayList<>();
        List<Integer> startR = new ArrayList<>();
        List<Integer> endL = new ArrayList<>();
        List<Integer> endR = new ArrayList<>();


        for (int i = 0; i < start.length(); i++) {
            if (start.charAt(i) == 'L')
                startL.add(i);
            else if (start.charAt(i) == 'R')
                startR.add(i);
        }

        for (int i = 0; i < end.length(); i++) {
            if (end.charAt(i) == 'L')
                endL.add(i);
            else if (end.charAt(i) == 'R')
                endR.add(i);
        }

        // check L positions are correct
        for (int i = 0; i < startL.size(); i++) {
            if (startL.get(i) < endL.get(i))
                return false;
        }

        // check R positions are correct
        for (int i = 0; i < startR.size(); i++) {
            if (startR.get(i) > endR.get(i))
                return false;
        }

        return true;
    }
}
