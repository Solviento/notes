/*You are given a license key represented as a string s that consists of only alphanumeric characters and dashes. The string is separated into n + 1 groups by n dashes. You are also given an integer k.

We want to reformat the string s such that each group contains exactly k characters, except for the first group, which could be shorter than k but still must contain at least one character. Furthermore, there must be a dash inserted between two groups, and you should convert all lowercase letters to uppercase.

Return the reformatted license key.*/

package com.practice.code.strings;

import com.practice.code.runner.CodeRunner;

public class LicenseKeyFormatting implements CodeRunner {

    public String licenseKeyFormatting(String s, int k) {
        StringBuilder sb = new StringBuilder();
        s = s.toUpperCase();
        int counter = 0;
        for(int i = s.length() - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            if (ch != '-') {
                if (counter == k) {
                    sb.append('-');
                    counter = 0;
                    sb.append(ch);
                }
                else {
                    sb.append(ch);
                }
                counter++;
            }
        }

        return sb.reverse().toString();
    }

    public void run() {
        String s = "2-5g-3-J";
        int k = 2;
        String format = licenseKeyFormatting(s, k);
        System.out.println("Formatted String : " + format);
    }
}
