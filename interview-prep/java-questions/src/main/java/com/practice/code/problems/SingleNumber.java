package com.practice.code.problems;
/*Given a non-empty array of integers, every element appears twice except for one. Find that single one.
Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory? */
class SingleNumber {
  static int singleNumber(int[] nums) {
    // a XOR 0 = a    DO THE BIT MATH CALCULATIONS BY HAND TO VERIFY
    // a XOR a = 0
    // a XOR b XOR a = a XOR a XOR b = 0 XOR b = b
    int rem = 0;
    for (Integer e : nums) {
      rem = rem ^ e;  // ALSO DO THESE BIT CALCULATIONS TO SEE WHY XOR WORKS AND & + | DONT
    }
    return rem;
  }
  // Input:[4,1,2,1,2] Output:4
  public static void main(String... args){
    // int[] nums = new int[]{1,1,2,3,3};
    int[] nums = new int[] {1,2};
    int uni = singleNumber(nums);
    System.out.println(uni); 
  }
}