/*Implement strStr().

Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Clarification:

What should we return when needle is an empty string? This is a great question to ask during an interview.

For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().

Example 1:

Input: haystack = "hello", needle = "ll"
Output: 2*/

package com.practice.code.problems;

public class StrStr {
  // KMP is Time: O(m+n) Space: O(n), n = haystack, m = needle
  static int strStr(String haystack, String needle) {
    if(needle.length() == 0){
      return 0;
    }
    // first pre process string, find Longest Prefix Suffix (LPS)
    int[] lps = preProcess(needle);
    // now iterate through haystack and use KMP algorithm skip/checks
    int haystackLen = haystack.length();
    int needleLen = needle.length();
    int i = 0;    // index ptr for haystack
    int j = 0;    // index ptr for needle pattern
    while(i < haystackLen){
      // a char match is found, increment haystack and needle pointers
      if(haystack.charAt(i) == needle.charAt(j)){
        i++;
        j++;
      }
      // once enough char matches are found, needle pointer will reach end of needle pattern string
      if(j == needleLen){
        return (i-j);
      }
      // if j (needle ptr) mismatches, use lps array to determine longest prefix suffix to move haystack ptr by
      else if(i < haystackLen && haystack.charAt(i) != needle.charAt(j)){
        // there was prefix-suffix pattern so we move j (needle ptr) accordingly
        if(j!=0){
          j = lps[j-1];
        }
        // no char match nor prefix-suffix pattern found whatsoever, move i (haystack ptr) by one
        else{
          i = i +1;
        }
      }
    }
    return -1;
  }
  static int[] preProcess(String n){
    int[] lps = new int[n.length()];
    lps[0] = 0;
    int i = 1;
    // length of previous longest prefix suffix
    int j = 0;
    while(i < n.length()){
      if (n.charAt(i) == n.charAt(j)){
        j++;
        lps[i] = j;
        i++;
      }
      else{
        // tricky case: needle="AACA", need to add condition to reset lcs
        // possible pre-suffixes: [AA], [A  A], first one is just AA (two chars), second one is A  A (four chars)
        if(j!=0){
          j = lps[j-1];
        }
        else{
          lps[i] = j;
          i++;
        }
      }
    }
    return lps;
  }
  public static void main(String... args){
    String hay = "blakinpockiyabkinkmkinkibiko";
    String n = "kinki";
    int index = strStr(hay, n);
    System.out.println(index);
  }
}
