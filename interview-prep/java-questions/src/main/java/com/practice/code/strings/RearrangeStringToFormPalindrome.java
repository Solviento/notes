/*Given a string, convert the string to palindrome without any
modifications like adding a character, removing a character, replacing a character etc.

Examples:
Input : "mdaam"
Output : "madam" or "amdma"

Input : "abb"
Output : "bab"

Input : "geeksforgeeks"
Output : "No Palindrome"*/

package com.practice.code.strings;

import com.practice.code.runner.CodeRunner;

import java.util.HashMap;
import java.util.Map;

public class RearrangeStringToFormPalindrome implements CodeRunner {
    @Override
    public void run() {

    }

    // create frequency map
    // do linear scan of freq map, use oddCount to count number of odds are equal to 1
    // go through each characters, half each frequency group to form two stringbuilder halves (1st half, 2nd half)
    // return first half + odd char + second half
    public static String getPalindrome(String str) {

        // Store counts of characters
        HashMap<Character,
                Integer> counting = new HashMap<>();
        for (char ch : str.toCharArray()) {
            if (counting.containsKey(ch)) {
                counting.put(ch, counting.get(ch) + 1);
            } else {
                counting.put(ch, 1);
            }
        }

    /* Find the number of odd elements.
    Takes O(n) */
        int oddCount = 0;
        char oddChar = 0;

        for (Map.Entry<Character,
                Integer> itr : counting.entrySet()) {
            if (itr.getValue() % 2 != 0) {
                oddCount++;
                oddChar = itr.getKey();
            }
        }

    /* odd_cnt = 1 only if the length of
    str is odd */
        if (oddCount > 1 || oddCount == 1 &&
                str.length() % 2 == 0) {
            return "NO PALINDROME";
        }

        /* Generate first halh of palindrome */
        StringBuilder firstHalf = new StringBuilder();
        StringBuilder lastHalf = new StringBuilder();
        for (Map.Entry<Character, Integer> itr : counting.entrySet()) {

            // Build a string of floor(count/2)
            // occurrences of current character
            StringBuilder ss = new StringBuilder();
            for (int i = 0; i < itr.getValue() / 2; i++) {
                ss.append(itr.getKey());
            }

            // Attach the built string to end of
            // and begin of second half
            firstHalf.append(ss);
            lastHalf.insert(0, ss);
        }

        // Insert odd character if there
        // is any
        return (oddCount == 1) ?
                (firstHalf.toString() + oddChar + lastHalf) :
                (firstHalf + lastHalf.toString());
    }
}
