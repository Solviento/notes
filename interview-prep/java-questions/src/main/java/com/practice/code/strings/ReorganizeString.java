/*Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.

Return any possible rearrangement of s or return "" if not possible.

Example 1:

Input: s = "aab"
Output: "aba"*/

package com.practice.code.strings;

import com.practice.code.runner.CodeRunner;

public class ReorganizeString implements CodeRunner {

    // good to remember
    // char a - 'a' = int 0
    // char b - 'a' = int 1
    // ...
    // char z - 'a' = 25
    public String reorganizeString(String S) {
        int[] letterFrequencyArray = new int[26];
        for (int i = 0; i < S.length(); i++) {
            letterFrequencyArray[S.charAt(i) - 'a']++;
        }
        int max = 0, letter = 0;
        for (int i = 0; i < letterFrequencyArray.length; i++) {
            if (letterFrequencyArray[i] > max) {
                max = letterFrequencyArray[i];
                letter = i;
            }
        }
        if (max > (S.length() + 1) / 2) {
            return "";
        }
        char[] reorganizedString = new char[S.length()];
        int indexPointer = 0;
        while (letterFrequencyArray[letter] > 0) {
            reorganizedString[indexPointer] = (char) (letter + 'a');
            indexPointer += 2;
            letterFrequencyArray[letter]--;
        }
        for (int i = 0; i < letterFrequencyArray.length; i++) {
            while (letterFrequencyArray[i] > 0) {
                if (indexPointer >= reorganizedString.length) {
                    indexPointer = 1;
                }
                reorganizedString[indexPointer] = (char) (i + 'a');
                indexPointer += 2;
                letterFrequencyArray[i]--;
            }
        }
        return String.valueOf(reorganizedString);
    }

    // helper method to verify if string is a reoccurring letter string
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
        reorganizeString("aabza");
        System.out.println("Does this string have consecutive reoccurring letters? : " + reorganizeString("aabza"));
    }
}
