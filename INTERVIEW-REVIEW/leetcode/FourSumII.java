class FourSumII {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0 ; i < A.length; i++){
            for(int j = 0; j < B.length; j++){
                int sum = A[i]+B[j];
                if(map.containsKey(sum)){
                    // needs to store how frequent this sum value comes up in A[] and B[]
                    map.put(sum, map.get(sum)+1);
                }
                else{
                    map.put(sum, 1);
                }
            }
        }
        int count = 0;
        for(int i = 0; i < C.length; i++){
            for(int j = 0; j < D.length; j++){
                int counterSum = C[i]+D[j];
                counterSum *= -1;
                if(map.containsKey(counterSum)){
                    // frequency of counterSum in map added to overall count of zero sum tuples
                    count += map.get(counterSum);
                }
            }
        }
        return count;
    }
}