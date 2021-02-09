// Given a non-empty array of integers, return the k most frequent elements.
// Example 1:
// Input: nums = [1,1,1,2,2,3], k = 2
// Output: [1,2]
class TopKFreqElements {
    public List<Integer> topKFrequent(int[] nums, int k) {
        // build hash map, integer: frequency
        HashMap<Integer, Integer> map = new HashMap<>();
        for(Integer e: nums){
            map.put(e, map.getOrDefault(e, 0)+1);
        }
        // initialize the heap
        PriorityQueue<Integer> heap = new PriorityQueue<>((x, y) -> map.get(x) - map.get(y));
        for(Integer e: map.keySet()){
            heap.add(e);    // max heapify
            // remove extra elements once heap has size greater than k elements
            if(heap.size() > k){
                int n = heap.poll();
            }
        }
        List<Integer> topKElements = new LinkedList<>();
        while(!heap.isEmpty()){
            int e = heap.poll();
            topKElements.add(e);
        }
        Collections.reverse(topKElements);
        return topKElements;
    }
}