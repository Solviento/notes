// Implement int sqrt(int x).
// Compute and return the square root of x, where x is guaranteed to be a non-negative integer.
// Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.
class SquareRoot{
  int mySqrt(int x) {
    if (x==0 || x==1) return x;
    // start=1 to avoid overflow, if start=0 and x=1,end=1 then mid=0 which leads to division by 0
    int start = 1, end = x;
    int ans = 0;
    while(start<=end)
    {
      // (start+end)/2 might result in overflow (x being largest int) so we use this operation instead
      int mid = start + (end - start)/2;
      // mid*mid == x might also result in overflow, so we use this robust operation to calculate mid
      if (mid <= x/mid) 
      {
        start = mid + 1;
        ans = mid;
      }
      else 
        end = mid - 1;
    }
    return ans;
  }
}