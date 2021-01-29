class MoveZeros {
  public void moveZeroes(int[] nums) {
    int lastNonZero = 0;
    // "bubble" non-zero elements to front of list (list is already presorted)
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != 0) {
        int tmp = nums[i];
        nums[i] = nums[lastNonZero];
        nums[lastNonZero] = tmp;
        lastNonZero++;
      }
    }
  }
}