/*Given a string num which represents an integer, return true if num is a strobogrammatic number.
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Example 1:
Input: num = "69"
Output: true
Example 2:
Input: num = "88"
Output: true
Example 3:
Input: num = "962"
Output: false*/

package com.practice.code.other;

import com.practice.code.runner.CodeRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class StrobogrammaticNumber implements CodeRunner {
    @Override
    public void run() {

    }

    // hashmap + stringbuilder approach
    // time: o(n) space: o(n)
    public boolean isStrobogrammatic(String num) {

        // Initialise a map with the five-digit rotation rules
        Map<Character, Character> rotatedDigits = new HashMap<Character, Character>() {
            {
                put('0', '0');
                put('1', '1');
                put('6', '9');
                put('8', '8');
                put('9', '6');
            }
        };

        StringBuilder rotatedStringBuilder = new StringBuilder();

        // Remember that we want to loop backwards through the string
        for (int i = num.length() - 1; i >= 0; i--) {
            char c = num.charAt(i);
            if (!rotatedDigits.containsKey(c)) {
                return false; // This must be an invalid digit.
            }
            rotatedStringBuilder.append(rotatedDigits.get(c));
        }

        String rotatedString = rotatedStringBuilder.toString();
        return num.equals(rotatedString);
    }


    public boolean isStrobogrammaticO1(String num) {

        Map<Character, Character> rotatedDigits = new HashMap<Character, Character>() {
            {
                put('0', '0');
                put('1', '1');
                put('6', '9');
                put('8', '8');
                put('9', '6');
            }
        };

        // Java allows us to have more than one iteration variable.
        for (int left = 0, right = num.length() - 1; left <= right; left++, right--) {
            char leftChar = num.charAt(left);
            char rightChar = num.charAt(right);
            if (!rotatedDigits.containsKey(leftChar) || rotatedDigits.get(leftChar) != rightChar) {
                return false;
            }
        }
        return true;

    }
}
