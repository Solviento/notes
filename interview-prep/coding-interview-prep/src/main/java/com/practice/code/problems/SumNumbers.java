package com.practice.code.problems;

class SumNumbers{
  static int sumNumbers(String str) {
    int sum = 0;
    String numStr = "";
    boolean currNum = false;
    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      if(Character.isDigit(c)){
        // System.out.println(c);
        if(currNum == false){
          currNum = true;
          numStr += String.valueOf(c);
          continue;
        }
        else{
          numStr += String.valueOf(c);
          continue;
        }
      }
      if(!Character.isDigit(c)){
        if(currNum == true){
          sum += Integer.valueOf(numStr);
          // System.out.println(numStr);
          numStr = "";
          currNum = false;
          continue;
        }
        else{
          continue;
        }
      }
    }
    // any left over numStr values are added, ie. when a number is at the end of the string: "abc123z11"
    if(numStr.length() > 0)
      sum += Integer.valueOf(numStr);
    return sum;
  }
  public static void main(String... args){
    String s = "aa11b33";
    int sum = sumNumbers(s);
    System.out.println("Sum: " + sum);
  }
}