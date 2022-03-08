/*Given a string array words, return an array of all characters
that show up in all strings within the words
(including duplicates). You may return the answer in any order.

Example 1:
Input: words = ["bella","label","roller"]
Output: ["e","l","l"]

Example 2:
Input: words = ["cool","lock","cook"]
Output: ["c","o"]*/

package com.practice.code.arrays.first;

import com.practice.code.runner.CodeRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommonChars implements CodeRunner {

    public void run() {
        String[] words = {"bella", "label", "roller"};
        List<String> com = commonChars(words);
        System.out.println("Common letters: " + com);
    }

    // t: o(n) s: o(n)
    // create char frequency map
    // using each value in map, mod by number of words, if equal to 0 then add to common characters list
    public List<String> commonChars(String[] words) {
        int numberOfWords = words.length;
        HashMap<Character, Integer> letterFrequency = new HashMap<>();
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                letterFrequency.merge(c, 1, (x, y) -> x + y);
            }
        }
        List<String> commonCharacters = new ArrayList<>();
        for (Character c : letterFrequency.keySet()) {
            int count = letterFrequency.get(c);
            if (count % numberOfWords == 0) {
                // 6 "l" found across 3 words, 6/3 = 2 "l" found as common characters in entire list
                int multiplier = count / numberOfWords;
                // this adds duplicates to common characters list
                for (int i = 0; i < multiplier; i++) {
                    commonCharacters.add(String.valueOf(c));
                }
            }
        }
        return commonCharacters;
    }
}
