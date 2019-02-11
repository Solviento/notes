// https://www.youtube.com/watch?v=2MmGzdiKR9Y
// Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
class maximumSubarray{
  public int maxSubArray(int[] nums) {
    int localMax = nums[0];
    int globalMax = nums[0];
    for (int i = 1; i < nums.length; i++) {
      localMax = Math.max(localMax + nums[i], nums[i]);
      globalMax = Math.max(localMax, globalMax);
    }
    return globalMax;
  }
  public static void main(String... args){

  }
}