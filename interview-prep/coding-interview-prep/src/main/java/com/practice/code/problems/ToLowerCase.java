package com.practice.code.problems;

class ToLowerCase {
  public static String toLowerCase_(String str) {
    char[] c = str.toCharArray();
    for (int i = 0; i < str.length(); i++) {
      Character ch = str.charAt(i);
      if (ch >= 'A' && ch <= 'Z'){
        c[i] = (char) (ch + 32);
      }
    }
    return String.valueOf(c);
  }
  public static void main(String... args){
    String h = "HelloABXZz";
    String h_ = toLowerCase_(h);
    System.out.println(h_);
  }
}