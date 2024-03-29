/*Given a string, return the sum of the numbers appearing in the string, ignoring all other characters.
A number is a series of 1 or more digit chars in a row. (Note: Character.isDigit(char) tests if a char is one of the
chars '0', '1', .. '9'. Integer.parseInt(string) converts a string to an int.)

sumNumbers("abc123xyz") → 123
sumNumbers("aa11b33") → 44
sumNumbers("7 11") → 18
————————————————
*/

package com.practice.code.problems;

import com.practice.code.runner.CodeRunner;

public class SumNumbers implements CodeRunner {
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

  @Override
  public void run() {
    String s = "aa11b33";
    int sum = sumNumbers(s);
    System.out.println("Sum: " + sum);
  }
}