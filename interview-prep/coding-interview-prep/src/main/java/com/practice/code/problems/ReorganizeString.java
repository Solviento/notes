package com.practice.code.problems;

import com.practice.code.runner.CodeRunner;

public class ReorganizeString implements CodeRunner {

    public String reorganizeString(String s) {

        String m = "aab";
        // aba - good
        // aab - bad
        // baa - bad
        // a = 2, b = 1, difference 1

        String n = "aaab";
        // aaab - bad
        // abaa - bad
        // baaa - bad
        // aaba - bad
        // a = 3, b = 1, difference 2

        String n2 = "aaabb";
        // aaabb - bad
        // abaab - bad
        // aabab - bad
        // baaab - bad
        // ababa - good
        // a = 3, b = 2, difference 1

        String n3 = "aaabbb";

        return null;
    }

    // will take m time, m space
    public boolean isReoccurringLetterString(String m){
        char[] wholeString = m.toCharArray();
        if (wholeString.length == 1) {
            return true;
        }
        char previousLetter = wholeString[0];
        for(int i = 1; i < wholeString.length; i++){
            if (previousLetter == wholeString[i]) {
                return false;
            } else {
                previousLetter = wholeString[i];
            }
        }
        return true;
    }

    @Override
    public void run() {

    }
}
