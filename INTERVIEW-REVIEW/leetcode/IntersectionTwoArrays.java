class IntersectionTwoArrays {
    // use two hash sets to detect intersection
    // Time: O(n) Space: O(n)
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> setA = new HashSet<>();
        Set<Integer> setB = new HashSet<>();
        for(int i = 0; i < nums1.length; i++){
            setA.add(nums1[i]);
        }
        for(int i = 0; i < nums2.length; i++){
            if(setA.contains(nums2[i])){
                setB.add(nums2[i]);
            }
        }
        int[] ret = new int[setB.size()];
        int i = 0;
        for(Integer e: setB){
            ret[i++] = e;
        }
        return ret;
    }
}