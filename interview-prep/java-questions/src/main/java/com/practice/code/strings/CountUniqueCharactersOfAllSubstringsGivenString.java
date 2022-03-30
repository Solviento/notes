/*Let's define a function countUniqueChars(s) that returns the number of unique characters on s.
For example, calling countUniqueChars(s) if s = "LEETCODE" then "L", "T", "C", "O", "D"
are the unique characters since they appear only once in s, therefore countUniqueChars(s) = 5.
Given a string s, return the sum of countUniqueChars(t) where t is a substring of s.
Notice that some substrings can be repeated so in this case you have to count the repeated ones too.

Example 1:
Input: s = "ABC"
Output: 10
Explanation: All possible substrings are: "A","B","C","AB","BC" and "ABC".
Evey substring is composed with only unique letters.
Sum of lengths of all substring is 1 + 1 + 1 + 2 + 2 + 3 = 10

Example 2:
Input: s = "ABA"
Output: 8
Explanation: The same as example 1, except countUniqueChars("ABA") = 1.

Example 3:
Input: s = "LEETCODE"
Output: 92*/

package com.practice.code.strings;

import com.practice.code.runner.CodeRunner;

import java.util.Arrays;

public class CountUniqueCharactersOfAllSubstringsGivenString implements CodeRunner {
    @Override
    public void run() {

    }

    public int uniqueLetterString(String s) {
        int[] lastSeenCharIndexPositions = new int[26]; // cover all the letters of the alphabet
        Arrays.fill(lastSeenCharIndexPositions, -1);
        int[] preLastCharOccurrencePos = new int[26]; // Store the previous last index position where a character appeared, with a minus -1 value
        Arrays.fill(preLastCharOccurrencePos, -1);

        int count = 0;
        int res = 0;

        for (int i = 0; i < s.length(); i++) {
            int charValue = s.charAt(i) - 'A'; // All characters are uppercase
            int lastVisitedCharValue = lastSeenCharIndexPositions[charValue];
            int preLastVisitedCharValue = preLastCharOccurrencePos[charValue];

            // -- double minus becomes a plus.  Ingenious code
            count += i - lastVisitedCharValue - lastVisitedCharValue + preLastVisitedCharValue;
            preLastCharOccurrencePos[charValue] = lastSeenCharIndexPositions[charValue];
            lastSeenCharIndexPositions[charValue] = i;
            res += count;
        }
        return res;
    }

    public int uniqueLetterStringOther(String S) {
        int res = 0;
        if (S == null || S.length() == 0) {
            return res;
        }
        int[] showLastPosition = new int[128];
        int[] contribution = new int[128];
        int cur = 0;
        for (int i = 0; i < S.length(); i++) {
            char x = S.charAt(i);
            cur -= contribution[x];
            contribution[x] = (i - (showLastPosition[x] - 1));
            cur += contribution[x];
            showLastPosition[x] = i + 1;
            res += cur;
        }
        return res;
    }
}
