package com.practice.code.problems;
class ReverseInteger {
    public int reverse(int x) {
        // use long instead of int
        long result = 0;
        // could also do Math.abs to take care of negative cases and then return the negative int result
        while(x != 0){
            int lastDig = x % 10;
            x = x/10;
            // NOTE: this can cause overflow!!
            result = result * 10 + lastDig;
        }
        // this checks for overflow result operations
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE)
            return 0;
        return (int)result;
    }
}