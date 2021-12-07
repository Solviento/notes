package com.practice.code.problems;

import com.practice.code.runner.CodeRunner;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class CommonChars implements CodeRunner {

    public List<String> commonChars(String[] words) {
        int wordsNum = words.length;
        HashMap<Character, Integer> letterMap = new HashMap<>();
        for(String word: words) {
            for(int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                letterMap.merge(c, 1, Integer::sum);
            }
        }
        List<String> out = new ArrayList<>();
        for(Character c: letterMap.keySet()) {
            int count = letterMap.get(c);
            if(count % wordsNum == 0) {
                int multiplier = count/wordsNum;
                for(int i = 0; i < multiplier; i++) {
                    out.add(String.valueOf(c));
                }
            }
        }
        // NOT A VALID SOLUTION!!!!!!!!!!!!!!!
        return out;
    }

    public void run(){
        String[] words = {"bella", "label", "roller"};
        List<String> com = commonChars(words);
        String res = "";
        for (String s: com) {
            res += " " + s;
        }
        System.out.println("Common letters: " + res);
    }
}
