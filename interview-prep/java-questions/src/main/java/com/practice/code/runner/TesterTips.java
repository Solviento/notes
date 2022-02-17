package com.practice.code.runner;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class TesterTips implements CodeRunner{
    @Override
    public void run() {

        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
        // insertion order is not maintained in HashMap, but it is in LinkedHashMap
        linkedHashMap.put(10, "abc");
        linkedHashMap.put(3, "xyz");
        linkedHashMap.put(1, "abc");
        System.out.println("Mappings of LinkedHashMap: " + linkedHashMap);

        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(10, "abc");
        hashMap.put(3, "xyz");
        hashMap.put(1, "abc");
        System.out.println("Mappings of HashMap: " + hashMap);

        // Iterating through a map (linked/hashmap)
        for(Map.Entry<Integer, String> entry : hashMap.entrySet()) {
            System.out.println("Mapping entry: " + entry);
        }

        // additional methods for map
        hashMap.get(10);
        String ret = hashMap.getOrDefault(4, "defaultValue");
        System.out.println("GetOrDefault Mapping value: " + ret);

        // splitting a string by formatting and delimiting
        String splitMe = "This is a long sentence. Defined by periods. And commas.";
        // IMPORTANT, REPLACEALL USES REGEX SO TO REPLACE PERIODS, ETC MUST PREFIX WITH \\
        splitMe = splitMe.replaceAll("\\.", "");
        splitMe = splitMe.toLowerCase();
        System.out.println("Formatted string: " + splitMe);
        String[] stringTokens = splitMe.split(" ");
        for(String s: stringTokens) {
            System.out.print(s + " | ");
        }
    }
}
