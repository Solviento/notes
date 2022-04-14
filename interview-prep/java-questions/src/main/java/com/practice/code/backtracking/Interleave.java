// Print all interleavings of given two strings
// Assume: AB MN, B comes after A and N comes after M and s1 len == s2 len
// AB MN
// AB MN  interleave = ""
// B MN   interleave = "A"
// MN     interleave = "AB"
// N      interleave = "ABM"
//        interleave = "ABMN" *
// B MN   interleave = "A"
// B N    interleave = "AM"
// N      interleave = "AMB"
//        interleave = "AMBN" *
// B

package com.practice.code.backtracking;

import com.practice.code.runner.CodeRunner;

public class Interleave implements CodeRunner {

    @Override
    public void run() {
        String s1 = "abc";
        String s2 = "def";

        int m = s1.length();
        int n = s2.length();
        String interleave = "";
        printInterleave(s1, s2, interleave, m, n);
    }

    // backtracking
    // if no more remaining letters count, return
    // else if remaining left letter count is greater than 0, append character and decrease remaining left letter count
    // else if remaining right letter count is greater than 0, append character and decrease remaining right letter count
    void printInterleave(String remainingLeftString,
                         String remainingRightstring,
                         String interleave,
                         int remainingLeftLettersCount,
                         int remainingRightLettersCount) {
        if (remainingLeftLettersCount == 0 && remainingRightLettersCount == 0) {
            System.out.println(interleave);
        }
        if (remainingLeftLettersCount != 0) {
            printInterleave(remainingLeftString.substring(1),
                            remainingRightstring,
                    interleave + remainingLeftString.charAt(0),
        remainingLeftLettersCount - 1,
                            remainingRightLettersCount);
        }
        if (remainingRightLettersCount != 0) {
            printInterleave(remainingLeftString,
                            remainingRightstring.substring(1),
                    interleave + remainingRightstring.charAt(0),
                            remainingLeftLettersCount,
        remainingRightLettersCount - 1);
        }
    }
}