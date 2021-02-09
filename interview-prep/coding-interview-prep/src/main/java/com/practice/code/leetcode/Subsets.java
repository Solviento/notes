// Given a set of distinct integers, nums, return all possible subsets (the power set).
// Note: The solution set must not contain duplicate subsets.
// Example:
// Input: nums = [1,2,3]
// Output:
// [
//   [3],
//   [1],
//   [2],
//   [1,2,3],
//   [1,3],
//   [2,3],
//   [1,2],
//   []
// ]
class Subsets {
  public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    backtrack(result, new ArrayList<>(), nums, 0);
    return result;
  }

  void backtrack(List<List<Integer>> result, List<Integer> tmpList, int[] arr, int start) {
    result.add(new ArrayList<>(tmpList));
    for (int i = start; i < arr.length; i++) {
      tmpList.add(arr[i]);
      backtrack(result, tmpList, arr, i + 1);
      tmpList.remove(tmpList.size() - 1);
    }
  }
}