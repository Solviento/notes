// You are climbing a stair case. It takes n steps to reach to the top.
// Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
// Note: Given n will be a positive integer.
class climbingStairs {
  public int climbStairs(int n) {
    // n=0, distinct=0, n=1, distinct=1, n=2, distinct=2
    if (n < 3) {
      return n;
    }
    // memoize[n] is the sum of the previous two indexes
    // [1] [2] [3] [5] [8] [13] [21]
    // its very helpful to draw out the recursive tree on how memoization would help in this scenario
    int[] memoize = new int[n];
    memoize[0] = 1;
    memoize[1] = 2;
    for (int i = 2; i < n; i++) {
      memoize[i] = memoize[i - 2] + memoize[i - 1];
    }
    return memoize[n - 1];
  }
}