/*Given an integer n, return a string array answer (1-indexed) where:

answer[i] == "FizzBuzz" if i is divisible by 3 and 5.
answer[i] == "Fizz" if i is divisible by 3.
answer[i] == "Buzz" if i is divisible by 5.
answer[i] == i (as a string) if none of the above conditions are true.
Example 1:

Input: n = 3
Output: ["1","2","Fizz"]
*/

package com.practice.code.arrays.second;

import com.practice.code.runner.CodeRunner;

import java.util.Arrays;
import java.util.LinkedHashMap;

public class FizzBuzz implements CodeRunner {

    public void run() {
        System.out.println("15 -> " + Arrays.toString(fizzBuzzHash(15)));
    }

    // better solution for maintainability
    // t: o(n) s: o(1)
    static String[] fizzBuzzHash(int n) {
        String[] fizz = new String[n];
        LinkedHashMap<Integer, String> map = new LinkedHashMap<>();
        map.put(3, "Fizz");
        map.put(5, "Buzz");
        map.put(7, "Zarr");
        for (int i = 1; i < n; i++) {
            StringBuilder tmp = new StringBuilder();
            for (Integer key : map.keySet()) {
                if (i % key == 0) {
                    tmp.append(map.get(key));
                }
            }
            if (tmp.toString().equals("")) {
                tmp.append((i));
            }
            fizz[i - 1] = tmp.toString();
        }
        return fizz;
    }

    // t: o(n) s: o(1)
    public String[] fizzBuzz(int n) {
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
}