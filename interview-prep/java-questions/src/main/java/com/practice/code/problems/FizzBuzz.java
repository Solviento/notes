/*Given an integer n, return a string array answer (1-indexed) where:

answer[i] == "FizzBuzz" if i is divisible by 3 and 5.
answer[i] == "Fizz" if i is divisible by 3.
answer[i] == "Buzz" if i is divisible by 5.
answer[i] == i (as a string) if none of the above conditions are true.
Example 1:

Input: n = 3
Output: ["1","2","Fizz"]
*/

package com.practice.code.problems;

import com.practice.code.runner.CodeRunner;

import java.util.HashMap;

public class FizzBuzz implements CodeRunner {
    // straightforward if conditions
    public static String[] fizzBuzz(int n) {
        String[] fizz = new String[n];
        for (int j = 0; j < n; j++) {
            int i = j + 1;
            if (i % 5 == 0 && i % 3 == 0) {
                fizz[j] = "FizzBuzz";
            } else if (i % 5 == 0) {
                fizz[j] = "Buzz";
            } else if (i % 3 == 0) {
                fizz[j] = "Fizz";
            } else {
                fizz[j] = String.valueOf(i);
            }
        }
        return fizz;
    }

    // simplifying work by using concatenations
    static String[] fizzBuzzStr(int n) {
        String[] fizz = new String[n];
        for (int i = 1; i < n; i++) {
            String tmp = "";
            if (i % 3 == 0) {
                tmp += "Fizz";
            }
            if (i % 5 == 0) {
                tmp += "Buzz";
            }
            if (tmp.equals("")) {
                tmp += String.valueOf(i);
            }
            fizz[i - 1] = tmp;
        }
        return fizz;
    }

    // using a hash map to further future mappings
    static String[] fizzBuzzHash(int n) {
        String[] fizz = new String[n];
        HashMap<Integer, String> map =
                new HashMap<Integer, String>() {
                    {
                        put(3, "Fizz");
                        put(5, "Buzz");
                        put(7, "Zarr");
                    }
                };
        for (int i = 1; i < n; i++) {
            StringBuilder tmp = new StringBuilder();
            // this may not work for hashmap due to non-ordering, need to use hashSet for ordering
            for (Integer key : map.keySet()) {
                if (i % key == 0) {
                    tmp.append(map.get(key));
                }
            }
            if (tmp.equals("")) {
                tmp.append((i));
            }
            fizz[i - 1] = tmp.toString();
        }
        return fizz;
    }

    public void run() {
        System.out.println("Write a program that outputs the string representation of numbers from 1 to n.\n" +
                "But for multiples of three it should output \"Fizz\" instead of the number and for the multiples of five output \"Buzz\". \n" +
                "For numbers which are multiples of both three and five output \"FizzBuzz\".");
        System.out.println("15 -> " + fizzBuzz(15));
    }
}