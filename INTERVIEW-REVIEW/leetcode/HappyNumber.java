/*
Write an algorithm to determine if a number is "happy".
A happy number is a number defined by the following process: 
Starting with any positive integer, replace the number by the 
sum of the squares of its digits, and repeat the process until 
the number equals 1 (where it will stay), or it loops endlessly 
in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers. 
Input: 19
Output: true
Explanation: 
1^2 + 9^2 = 82
8^2 + 2^2 = 68
6^2 + 8^2 = 100
1^2 + 0^2 + 0^2 = 1 */
class HappyNumber {
  public boolean isHappy(int n) {
    boolean hun = false;
    while (!hun) {
      int sumSquared = 0;
      while (n > 0) {
        int e = (n % 10);
        sumSquared += e * e;
        n = n / 10;
      }
      n = sumSquared;
      if (sumSquared == 1) {
        hun = true;
      }
      // can add a condition where after +1000 loops, this ends and returns false
    }
    return hun;
  }
}