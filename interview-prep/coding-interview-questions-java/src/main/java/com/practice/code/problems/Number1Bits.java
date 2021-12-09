package com.practice.code.problems;
// Write a function that takes an unsigned integer and return the number of '1' bits it has (also known as the Hamming weight).
// an integer in java has 32 bits
// to count 1's in the bit representation of integer n, we do n AND 1, if the operation result is 1 then that means the last bit of the input integer is 1. We then add this to the 1's count
// shift the integer n by on to the right and check for the next bit
// use >>> bit shifting unsigned operation due to Integer.Max_val +1 = Integer.Min_val which forces us to not use n > 0 and use n!=0 instead

public class Number1Bits {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int ones = 0;
        while(n!=0){
            ones = ones + (n&1);
            n = n>>>1;
        }
        return ones;
    }
}