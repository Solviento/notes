/*Given a non-empty array of integers, every element appears twice except for one. Find that single one.
Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory? */
class SingleNumber {
  public int singleNumber(int[] nums) {
    // a XOR 0 = a
    // a XOR a = 0
    // a XOR b XOR a = a XOR a XOR b = 0 XOR b = b
    int rem = 0;
    for (Integer e : nums) {
      rem = rem ^ e;
    }
    return rem;
  }
  // Input:[4,1,2,1,2] Output:4
}