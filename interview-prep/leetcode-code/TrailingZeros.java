// Given an integer n, return the number of trailing zeroes in n!.
// n = 5
// 5, 20, 60, 120 -> 1 trailing zero
// n = 8
// 8, 56, 336, 1680, 6720, 20160, 40320 -> 1 trailing zero
class TrailingZeros {
    public int trailingZeroes(int n) {
        int count = 0;
        for (long i = 5; n / i > 0; i *= 5)
            count += n / i;
        return count;
        // OR
        /*
        int zeros = 0;
        while(n){
            zeros += n/= 5;
        }
        return zeros;
        */
    }
}