// Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
// input: [3, 0, 1] output: 2
class MissingNumber {
  public int missingNumber(int[] nums) {
    // sum of 0+1+2+..+n = n(n+1) / 2
    int n = nums.length;
    int seriesSum = n * (n + 1) / 2;
    for (int i = 0; i < nums.length; i++) {
      seriesSum -= nums[i];
    }
    return seriesSum;
  }
}