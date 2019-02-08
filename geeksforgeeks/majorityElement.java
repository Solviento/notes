import java.util.HashMap;
// Given an array of size n, find the majority element. The majority element is the elment
// that appears MORE THAN n/2 times, may assume array is non empty and majority element DOES exist
class majorityElement {
  static int majorityElement_(int[] nums) {
    HashMap<Integer, Integer> maj = new HashMap<>();
    for (Integer e : nums) {
      if (!maj.containsKey(e)) {
        maj.put(e, 1);
      } else {
        int c = maj.get(e);
        maj.put(e, c + 1);
        // threshold for majority
        if (maj.get(e) > (nums.length / 2)) {
          return e;
        }
      }
    }
    // if array is one element, return element
    return nums[0];
  }
  public static void main(String[] args){
    int[] arr = new int[]{3, 2, 3};
    int m = majorityElement_(arr);
    System.out.println(m);
  }
}