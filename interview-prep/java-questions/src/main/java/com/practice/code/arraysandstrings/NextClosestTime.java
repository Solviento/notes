/*Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits.
There is no limit on how many times a digit can be reused.

You may assume the given input string is always valid. For example, "01:34", "12:09" are all valid. "1:34", "12:9" are all invalid.*/

package com.practice.code.arraysandstrings;

import com.practice.code.runner.CodeRunner;

import java.util.HashSet;

public class NextClosestTime implements CodeRunner {

    public String nextClosestTime(String time) {
        // convert time to minutes
        int minutes = Integer.parseInt(time.substring(0, 2)) * 60;
        minutes += Integer.parseInt(time.substring(3));
        // store time string into set
        HashSet<Integer> digits = new HashSet<>();
        for(char c: time.toCharArray()) {
            digits.add(c - '0');
        }
        // simulate a clock and then check if generated time is valid
        while(true) {
            // simulate next minute on the clock (+1 min), in case of 23:59 -> 00:00
            minutes = (minutes + 1) % (24 * 60);
            // 19:35 = 1175 minutes -> convert back to HH:MM format
            int[] nextTime = {minutes/60/10, minutes/60%10,minutes%60/10,minutes%60%10};

            boolean isValid = true;
            for(int digit: nextTime) {
                if (!digits.contains(digit)) {
                    isValid = false;
                }
            }
            if (isValid) {
                return String.format("%02d:%02d", minutes/60, minutes%60);
            }
        }
    }

    @Override
    public void run() {
        String time = "19:34";
        String nextTime = nextClosestTime(time);
        System.out.println("Next closest time: " + nextTime);
    }
}
