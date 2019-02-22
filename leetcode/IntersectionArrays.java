// Given two arrays, write a function to compute their intersection.
// Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
// Output: [4,9]
// If given arrays are already sorted, we can use two pointers for (Time: O(n))
// and Space: O(1) to iterate through pointers to verify if selected numbers are
// the same, to iterate compare the two numbers
// nums1: [4, 5, 5, 7, 9] nums2 = [4, 5, 5, 5, 8, 10, 12]
class IntersectionArrays {
  public int[] intersect(int[] nums1, int[] nums2) {
    HashMap<Integer, Integer> fMap = new HashMap<>();
    ArrayList<Integer> arr = new ArrayList<>();
    for (Integer e : nums1) {
      if (fMap.containsKey(e)) {
        int val = fMap.get(e);
        fMap.put(e, val + 1);
      } else {
        fMap.put(e, 1);
      }
    }
    for (Integer e : nums2) {
      if (fMap.containsKey(e) && fMap.get(e) > 0) {
        fMap.put(e, fMap.get(e) - 1);
        arr.add(e);
      }
    }
    int[] intx = new int[arr.size()];
    for (int i = 0; i < intx.length; i++) {
      intx[i] = arr.get(i);
    }
    return intx;
  }
}