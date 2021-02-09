package com.practice.code.problems;
// Given a column title as appear in an Excel sheet, return its corresponding column number.
// A->1, B->2, AA->27, ... ZY->701
// using charAt retrieves ASCII values
// A->65, Z->90
// a->97, z->122
class columnNumber {
  static int titleToNumber(String s) {
        int sum = 0;
        for(int i = 0; i<s.length(); i++){
          sum *= 26;  // for each successive letter, multiply by 26 to fill up sum total and then add below
          sum += (s.charAt(i) - 'A') + 1; // converts A->1, .. Z->26
        }
        return sum;
    }
  public static void main(String... args){
    String s = "AB";
    int n = titleToNumber(s);
    System.out.println(n);
  }
}