package com.practice.code.problems;
class PowerOfThree {
    public boolean isPowerOfThree(int n) {
        if (n<=0){
            return false;
        }
        // if y = b^x then x=log_b(y)
        double rem = Math.log10(n)/Math.log10(3);
        // if rem is a whole number, then n is a power of 3
        // else, if not whole number then n is not a power of 3
        System.out.println(rem);
        if(rem%1== 0){
            return true;
        }
        else{
            return false;
        }
    }
}