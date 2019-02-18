class ReverseInteger {
    public int reverse(int x) {
        // use long instead of int
        long result = 0;
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