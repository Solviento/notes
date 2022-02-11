/*This is an interactive problem.
You are given an array of unique strings wordlist where wordlist[i] is 6 letters
long, and one word in this list is chosen as secret.
You may call Master.guess(word) to guess a word. The guessed word should have type
string and must be from the original list with 6 lowercase letters.
This function returns an integer type, representing the number of exact matches
(value and position) of your guess to the secret word. Also, if your guess is not in the given wordlist, it will return -1 instead.
For each test case, you have exactly 10 guesses to guess the word. At the end of
any number of calls, if you have made 10 or fewer calls to Master.guess and at least one of these guesses was secret, then you pass the test case.

Example 1:

Input: secret = "acckzz", wordlist = ["acckzz","ccbazz","eiowzz","abcczz"], numguesses = 10
Output: You guessed the secret word correctly.
Explanation:
master.guess("aaaaaa") returns -1, because "aaaaaa" is not in wordlist.
master.guess("acckzz") returns 6, because "acckzz" is secret and has all 6 matches.
master.guess("ccbazz") returns 3, because "ccbazz" has 3 matches.
master.guess("eiowzz") returns 2, because "eiowzz" has 2 matches.
master.guess("abcczz") returns 4, because "abcczz" has 4 matches.
We made 5 calls to master.guess and one of them was the secret, so we pass the test case.
Example 2:
Input: secret = "hamada", wordlist = ["hamada","khaled"], numguesses = 10
Output: You guessed the secret word correctly.*/

package com.practice.code.other;

import com.practice.code.runner.CodeRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GuessTheWord implements CodeRunner {
    @Override
    public void run() {

    }

    interface Master {
        public int guess(String word);
    }

    public void findSecretWord(String[] wordlist, Master master) {
        Random random = new Random();
        for (int i = 0, matches = 0; i < 10 && matches != 6; i++) {
            String guess = wordlist[random.nextInt(wordlist.length)];
            matches = master.guess(guess);
            List<String> candidates = new ArrayList<>();
            for (String word : wordlist) {
                if (matches == getMatches(guess, word)) {
                    candidates.add(word);
                }
            }

            wordlist = candidates.toArray(new String[0]);
        }
    }

    private int getMatches(String word1, String word2) {
        int matches = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) == word2.charAt(i)) {
                matches++;
            }
        }

        return matches;
    }

}
